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
	 * ��ʼ���
	 */
	public void start();
	/**
	 * ��ʼ��httpclient���Ӳ���
	 * @param charSet
	 */
	public void initiaHttpClient(String charSet);
	public void initiaHttpClient(String charSet,int time);
	/**
	 * 
	 * @return ����httpClient
	 */
	public HttpClient getHttpClient();
	/**
	 * get��ʽ�ύ
	 * @param charset ����
	 * @param url ��ַ
	 * @return htmlҳ��
	 */
	public String crawerHtmlByUrl(String charset,String url);
	/**
	 * post��ʽ�ύ
	 * @param charset ����
	 * @param url  ��ַ
	 * @param params ����
	 * @return htmlҳ��
	 */
	public String crawerHtmlByUrlPost(String charset,String url,Map<String,String> params);
	/**
	 * �����ļ�
	 * @param charset
	 * @param url
	 * @param outputPath
	 */
	public void downloadFileByUrl(String charset,String url,String outputPath);
	/**
	 * �ر�httpClient
	 * @param httpClient
	 */
	public void httpClientClose(HttpClient httpClient);

}
