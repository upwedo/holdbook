package com.whu.finance.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

public interface BaseService<T> {
	/**
	 * 开始入口
	 */
	public void start();
	/**
	 * 初始化httpclient链接参数
	 * @param charSet
	 */
	public void initiaHttpClient(String charSet);
	public void initiaHttpClient(String charSet,int time);
	/**
	 * 
	 * @return 返回httpClient
	 */
	public HttpClient getHttpClient();
	/**
	 * get方式提交
	 * @param charset 编码
	 * @param url 网址
	 * @return html页面
	 */
	public String crawerHtmlByUrl(String charset,String url);
	/**
	 * post方式提交
	 * @param charset 编码
	 * @param url  网址
	 * @param params 参数
	 * @return html页面
	 */
	public String crawerHtmlByUrlPost(String charset,String url,Map<String,String> params);
	/**
	 * 下载文件
	 * @param charset
	 * @param url
	 * @param outputPath
	 */
	public void downloadFileByUrl(String charset,String url,String outputPath);
	/**
	 * 关闭httpClient
	 * @param httpClient
	 */
	public void httpClientClose(HttpClient httpClient);

}
