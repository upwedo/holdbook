package com.whu.finance.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.whu.finance.dao.BaseDao;
import com.whu.finance.service.BaseService;
import com.whu.finance.util.ConstantUtil;

public abstract class BaseServiceImpl<T>implements BaseService<T> {
	//常量设置
	public static final String CHARSET_GBK="GBK";
	public static final String CHARSET_UTF="UTF-8";

	private Class clazz;
	protected Logger logger;//日志
	protected String seed;//初始化url
	protected HttpClient httpClient;//httpclient实例
	protected URIBuilder uriBuilder;//URI构建器
	private BaseDao<T> baseDao;
	public BaseServiceImpl() {
		ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class) type.getActualTypeArguments()[0];
		logger=Logger.getLogger(clazz.getName());
		logger.debug("======"+clazz.getName()+"Service init start"+"======");
	}
	public void setBaseDao(BaseDao<T> baseDao){
		this.baseDao=baseDao;
	}
	public BaseDao<T> getBaseDao(){
		return this.baseDao;
	}
	@Override
	public void initiaHttpClient(String charSet) {
		httpClient =  new DefaultHttpClient();
		setHttpClientSocketTime(httpClient, charSet, ConstantUtil.HTTP_STOCKET_TIME);
	}
	@Override
	public void initiaHttpClient(String charSet, int time) {
		httpClient = new DefaultHttpClient();
		setHttpClientSocketTime(httpClient, charSet, time);
		
	}
	@SuppressWarnings("deprecation")
	public void setHttpClientSocketTime(HttpClient httpClient, String charSet,
			int time) {
		((DefaultHttpClient) httpClient)
		.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler());
		((DefaultHttpClient) httpClient).getParams().setParameter(
				CoreProtocolPNames.HTTP_CONTENT_CHARSET, charSet);
		((DefaultHttpClient) httpClient).getParams().setParameter(
				"http.socket.timeout", time);
		((DefaultHttpClient) httpClient).getParams().setParameter(
				"http.connection.timeout", time);
		((DefaultHttpClient) httpClient).getParams().setParameter(
				"http.connection-manager.timeout", time);
	}

	public void initHttpGet(HttpGet httpGet, String charSet) {
		httpGet.addHeader("Accept", "*/*");  
		httpGet.addHeader("Accept-Charset", charSet);  
		httpGet.addHeader("Accept-Encoding", "gzip");  
		httpGet.addHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3"); 
		httpGet.addHeader("Connection", "keep-alive");
		httpGet.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) "
		  		+ "AppleWebKit/537.22 (KHTML, like Gecko) "
		  		+ "Chrome/25.0.1364.160 Safari/537.22");  
	}

	public void initHttpPost(HttpPost httpPost, String charSet) {
		httpPost.addHeader("Accept", "*/*");  
		httpPost.addHeader("Accept-Charset", charSet);  
		httpPost.addHeader("Accept-Encoding", "gzip");  
		httpPost.addHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3"); 
		httpPost.addHeader("Connection", "keep-alive");
		httpPost.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) "
		  		+ "AppleWebKit/537.22 (KHTML, like Gecko) "
		  		+ "Chrome/25.0.1364.160 Safari/537.22");  
		//如果参数中包含中文
		httpPost.addHeader("Content-Type","application/x-www-form-urlencoded;charset="+charSet);  

	}
	public HttpEntity retryTimeConnection(HttpClient httpClient,HttpResponse response, int retry) throws ClientProtocolException {
		int status=response.getStatusLine().getStatusCode();
		if(status==HttpStatus.SC_NOT_FOUND){
			logger.error(this.getClass().getSimpleName()+"404 html not found "+status);
			return null;
		}
		return response.getEntity();
	}
	public String GzipDecompressEntityToStr(HttpEntity entity, String charset) {
		String entityStr=null;
		Header header=entity.getContentEncoding();
		try{
			if(header!=null){
				HeaderElement[] codec=header.getElements();
				for(int i=0;i<codec.length;i++){
					if(codec[i].getName().equalsIgnoreCase("gzip")){
						GzipDecompressingEntity decompressingEntity=new GzipDecompressingEntity(entity);
						entityStr=EntityUtils.toString(decompressingEntity,charset);
					}else {
						entityStr=EntityUtils.toString(entity,charset);
					}
				}
			}else {
				entityStr=EntityUtils.toString(entity,charset);
			}
		}catch(ParseException e){
			logger.error(clazz.getName()+"service ParseException "+e);
		} catch (IOException e) {
			logger.error(clazz.getName()+"service IOException"+e);
		}
		return entityStr;
	}
	public String dealStockTimeoutHtmlGizp(HttpClient httpClient,HttpUriRequest httpMethod,int retryTime, String charset) {
		boolean flag=true;
		int count=1;
		String html=null;
		String httpClientCharSet= (String)httpClient.getParams().getParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET);
		int httpClientTime=(Integer)httpClient.getParams().getParameter("http.socket.timeout");
		while(flag&&retryTime>0){
			try {
				setHttpClientSocketTime(httpClient,httpClientCharSet,httpClientTime*count); 
				HttpResponse response = httpClient.execute(httpMethod);
				HttpEntity entity=retryTimeConnection(httpClient, response, 10);
				html=GzipDecompressEntityToStr(entity, charset);
				flag=false;
			} catch (ClientProtocolException e) {
				logger.error(this.getClass().getName()+" ClientProtocolException "+e);
			} catch (IOException e) {
				logger.error(this.getClass().getName()+" IOException "+e);
			}finally{
				logger.info(this.getClass().getName()+" StockTimeOutException rety "+count);
				retryTime--;
				count++;
			}
		}
		return html;
	}

	public String dealStockTimeoutHtml(HttpClient httpClient, HttpUriRequest httpMethod,int retryTime, String charset) {
		boolean flag=true;
		int count=1;
		String html=null;
		String httpClientCharSet= (String)httpClient.getParams().getParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET);
		int httpClientTime=(Integer)httpClient.getParams().getParameter("http.socket.timeout");
		while(flag&&retryTime>0){
			try {
				setHttpClientSocketTime(httpClient,httpClientCharSet,httpClientTime*count); 
				HttpResponse response = httpClient.execute(httpMethod);
				HttpEntity entity=retryTimeConnection(httpClient, response, 10);
				html=EntityUtils.toString(entity, charset);
				flag=false;
			} catch (ClientProtocolException e) {
				logger.error(this.getClass().getName()+" ClientProtocolException "+e);
			} catch (IOException e) {
				logger.error(this.getClass().getName()+" IOException "+e);
			}finally{
				logger.info(this.getClass().getName()+" StockTimeOutException rety "+count);
				retryTime--;
				count++;
			}
		}
		return html;
	}

	@Override
	public String crawerHtmlByUrl(String charset, String url) {
		String html=null;
		HttpGet httpGet=null;
		try {
			httpGet=new HttpGet(url);
			initHttpGet(httpGet, charset);
			HttpResponse response=httpClient.execute(httpGet);
			HttpEntity entity=retryTimeConnection(httpClient, response, 10);
			if(entity!=null)
				html=GzipDecompressEntityToStr(entity, charset);
		}catch(SocketTimeoutException e){
			html=this.dealStockTimeoutHtmlGizp(httpClient, httpGet, 10, charset);
		}catch (ClientProtocolException e) {
			html=this.dealStockTimeoutHtmlGizp(httpClient, httpGet, 10, charset);
		} catch (IOException e) {
			html=this.dealStockTimeoutHtmlGizp(httpClient, httpGet, 10, charset);
		}finally{
			httpGet.releaseConnection();
		}
		return html;
	}
	@Override
	public String crawerHtmlByUrlPost(String charset, String url,Map<String,String> params) {
		String html=null;
		HttpPost httpPost=null;
		try {
			httpPost=new HttpPost(url);
			initHttpPost(httpPost, charset);
			List formParams = new ArrayList();
			for (Object key:params.keySet()) {
				   formParams.add(new BasicNameValuePair((String)key, (String)params.get((String)key)));
		    }
			UrlEncodedFormEntity  paramsEntity = new UrlEncodedFormEntity(formParams,charset);
			httpPost.setEntity(paramsEntity);
			HttpResponse response=httpClient.execute(httpPost);
			HttpEntity entity=retryTimeConnection(httpClient, response, 10);
			if(entity!=null)
				html=GzipDecompressEntityToStr(entity, charset);
		}catch(SocketTimeoutException e){
			html=this.dealStockTimeoutHtmlGizp(httpClient, httpPost, 10, charset);
		}catch (ClientProtocolException e) {
			html=this.dealStockTimeoutHtmlGizp(httpClient, httpPost, 10, charset);
		} catch (IOException e) {
			html=this.dealStockTimeoutHtmlGizp(httpClient, httpPost, 10, charset);
		}finally{
			httpPost.releaseConnection();
		}
		return html;
	}
	@Override
	public void downloadFileByUrl(String charset, String url, String outputPath) {
		InputStream in=null;
		OutputStream out=null;
		HttpGet httpGet=new HttpGet(url);
		initHttpGet(httpGet, charset);
		httpGet.addHeader("Content-Type", "*/*");
		try {
			HttpResponse response=httpClient.execute(httpGet);
			HttpEntity entity=retryTimeConnection(httpClient,response, 10);
			if(entity!=null&&entity.isStreaming()){
				File f=new File(outputPath);
				  if(!f.getParentFile().exists()){
		        	    f.getParentFile().mkdirs();
		        	}
				out=new FileOutputStream(f);
				in=entity.getContent();
				int len=2048;
				byte[] buf=new byte[len];
				while((len=in.read(buf))!=-1){
					out.write(buf, 0, len);
				}
				out.flush();
			}
		}catch(SocketTimeoutException e){
			dealDownloadSockTimeOut(httpClient,httpGet,10,outputPath);
		}catch (ClientProtocolException e) {
			dealDownloadSockTimeOut(httpClient,httpGet,10,outputPath);
		} catch (IOException e) {
			dealDownloadSockTimeOut(httpClient,httpGet,10,outputPath);
		}finally{
			if(out!=null){
				try {
					out.close();
					out=null;
				} catch (IOException e) {
					logger.error(this.getClass().getSimpleName()+"文件关闭失败！！");
					e.printStackTrace();
				}
			}
		}
	}

	private void dealDownloadSockTimeOut(HttpClient httpClient,HttpGet httpGet,int retryTime,String outputPath){
		OutputStream out=null;
		InputStream in=null;
		boolean flag=true;
		int count=1;
		String httpClientCharSet= (String)httpClient.getParams().getParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET);
		int httpClientTime=(Integer)httpClient.getParams().getParameter("http.socket.timeout");
		while(flag&&retryTime>0){
			try {
					setHttpClientSocketTime(httpClient,httpClientCharSet,httpClientTime*count); 
					HttpResponse response = httpClient.execute(httpGet);
					HttpEntity entity=retryTimeConnection(httpClient,response, 10);
					if(entity!=null&&entity.isStreaming()){
						out=new FileOutputStream(new File(outputPath));
						in=entity.getContent();
						int len=2048;
						byte[] buf=new byte[len];
						while((len=in.read(buf))!=-1){
							out.write(buf, 0, len);
						}
						out.flush();
						flag=false;
					}
				} catch (IllegalStateException e) {
					logger.error(this.getClass().getSimpleName()+" IllegalStateException "+e);
					e.printStackTrace();
				} catch (IOException e) {
					logger.error(this.getClass().getSimpleName()+" IOException "+e);
					e.printStackTrace();
				}finally{
					count++;
					retryTime--;
					if(out!=null){
						try {
							out.close();
							out=null;
						} catch (IOException e) {
							logger.error(this.getClass().getSimpleName()+" IOException "+e);
							e.printStackTrace();
						}
					}
				}
			}
				
	}
	@Override
	public void httpClientClose(HttpClient httpClient) {
		if(httpClient!=null){
			httpClient.getConnectionManager().shutdown();
			httpClient=null;
		}
		
	}

	@Override
	public HttpClient getHttpClient() {
		return httpClient;
	}

}
