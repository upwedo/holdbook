package com.whu.finance.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whu.finance.dao.BaseDao;
import com.whu.finance.dao.StockIndustryDao;
import com.whu.finance.entity.StockIndustry;
import com.whu.finance.service.StockIndustryService;
import com.whu.finance.util.ValidateUtil;

@Service("stockIndustryService")
public class StockIndustryServiceImpl extends BaseServiceImpl<StockIndustry> implements StockIndustryService{
	private static final String MAX_PAGE_SIZE="2000";//按行业查询暂时认为每个行业最多2000条股票记录
	
	@Autowired
	private StockIndustryDao stockIndutryDao;
	
	@Resource(name="stockIndustryDao")
	public void setBaseDao(BaseDao<StockIndustry> baseDao) {
		super.setBaseDao(baseDao);
	}
	
	public StockIndustryServiceImpl() {
		super();	
		logger.info(this.getClass().getName()+" HttpClient init start....");
		initiaHttpClient(CHARSET_GBK);
	}
	public void jsoupParseHtml(String html, String select, String charset) {
		
		Document document=Jsoup.parse(html);
		Element industryMod=document.select(select).last();
		Elements industries=industryMod.select("div>ul>li>a");
		for (Element industry:industries) {
			String industryStyle=industry.attr("href");
			industryStyle=industryStyle.substring(industryStyle.indexOf("#")+1, industryStyle.indexOf("_"));
			String industryName=industry.text();
			
			if(industryName!=null&&!"".equals(industryName)){
				deepCrawerParase(uriBuilder,industryStyle,industryName,charset);
				//logger.info(this.getClass().getName()+" deep crawer end....");
			}else {
				logger.error(this.getClass().getName()+" 没有爬取到行业字段");
			}
			
		}
		
	}
	
	/**
	 * 深度爬取url：http://hqdigi2.eastmoney.com/EM_Quote2010NumericApplication/index.aspx?type=s&sortType=C&sortRule=-1&pageSize=2000&page=1&jsName=quote_123&style=28002479
	 * @param uriBuilder
	 * @param industryStyle
	 * @param industryName
	 * @param charset
	 */
	private void deepCrawerParase(URIBuilder uriBuilder, String industryStyle,
			String industryName,String charset) {
		//logger.info(this.getClass().getName()+" deep crawer start....");
		uriBuilder.setParameter("type", "s");
		uriBuilder.setParameter("sortType", "C");
		uriBuilder.setParameter("sortRule", "-1");
		uriBuilder.setParameter("pageSize", MAX_PAGE_SIZE);
		uriBuilder.setParameter("page", "1");//每页2000条，默认只有一页
		uriBuilder.setParameter("jsName", "quote_123");
		uriBuilder.setParameter("style", industryStyle);
		
		
		String html=null;
		try {
			html=this.crawerHtmlByUrl(CHARSET_GBK, uriBuilder.build().toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			logger.error(this.getClass().getName()+ " URISyntaxException" +e);
		} 
		if(ValidateUtil.isValidate(html)){
			html=html.substring(html.indexOf("[")+1, html.indexOf("]"));
			String[] stockInfos=html.split("\"");
			for(String info:stockInfos){
				int len=info.trim().length()/2;
				if(len>1){
					info=info.substring(0, len);
					String[] attributes=info.split(",");
					String stockid = attributes[1].trim();
					String hql="from StockIndustry s where s.stockID=?";
					List<StockIndustry>lists=this.getBaseDao().findEntityByHql(hql, stockid);
					if(!ValidateUtil.isValidate(lists)){
						StockIndustry stockIndustry=new StockIndustry();
						stockIndustry.setStockID(attributes[1].trim());
						stockIndustry.setIndustryName(industryName);
						stockIndustry.setStockName(attributes[2].trim());
						stockIndustry.setStockType(0);
						this.getBaseDao().saveEntity(stockIndustry);
						logger.info("存入 新股票  "+ attributes[1].trim() );
					}else{
						if(!lists.get(0).getStockName().equals(attributes[2].trim())){
							lists.get(0).setStockName(attributes[2].trim());
							lists.get(0).setDate(new Date());
							this.getBaseDao().updateEntity(lists.get(0));
							logger.info("修改股票名称  " + stockid + " " + attributes[2].trim());
						}
					}
					
				}
			}
		}
		
	}
	public void start() {
		String select=".node-sub-sub";
		String html=crawerHtmlByUrl(BaseServiceImpl.CHARSET_GBK,"http://quote.eastmoney.com/center/list.html");
		String deepUrl="http://hqdigi2.eastmoney.com/EM_Quote2010NumericApplication/index.aspx?";
		try {
			uriBuilder=new URIBuilder(deepUrl);
		} catch (URISyntaxException e) {
			logger.error(this.getClass().getName()+" URISyntaxException "+e);
		}
		if(ValidateUtil.isValidate(html)){
			jsoupParseHtml(html, select, BaseServiceImpl.CHARSET_GBK);
		}else{
			logger.info("html is null");
		}
		
		logger.info(this.getClass().getName()+" 爬取结束....");
	}
}
