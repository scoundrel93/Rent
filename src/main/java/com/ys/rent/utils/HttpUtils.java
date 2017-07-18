package com.ys.rent.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * Http请求工具类
 * @author Miracle
 * @data   2017年6月25日
 */
@SuppressWarnings({ "resource", "deprecation" })
public class HttpUtils {
	
	/**
	 * 发送GET请求
	 * @param url   请求URL
	 * @param paramsMap 请求参数
	 * @return 
	 * @throws Exception
	 */
	public static HttpEntity doGet(String url,Map<String,String> paramsMap) throws Exception
	{
		HttpClient httpClient = new DefaultHttpClient();
		url = url + (null == paramsMap ? "":buildParameters(paramsMap));
		HttpGet hp = new HttpGet(url);		
		HttpResponse response = httpClient.execute(hp);

        return response.getEntity();		
	}
	
	/**
	 * 发送POST请求
	 * @param url   请求URL
	 * @param paramsMap 请求参数
	 * @return 
	 * @throws Exception
	 */
	public static HttpEntity doPost(String url,Map<String,String> paramsMap) throws ClientProtocolException, IOException
	{
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpPost hp = new HttpPost(url);	
		
		List<NameValuePair> list  = new ArrayList<NameValuePair>();
	    for (String temp : paramsMap.keySet()) {
	          list.add(new BasicNameValuePair(temp,paramsMap.get(temp)));
	    }
	    hp.setEntity(new UrlEncodedFormEntity(list,"UTF-8"));
		
		HttpResponse response = httpClient.execute(hp);

        return response.getEntity();		
	}
	
	/**
	 * 发送POST请求
	 * @param url   请求URL
	 * @return 
	 * @throws Exception
	 */
	public static HttpEntity doPost(String url) throws ClientProtocolException, IOException
	{
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpPost hp = new HttpPost(url);	
				
		HttpResponse response = httpClient.execute(hp);

        return response.getEntity();		
	}
	
	private static String buildParameters(Map<String,String> params)
	{
		String paramStr = "&";
		for (String  key : params.keySet()) {
			paramStr += key + "=" + params.get(key) + "&";
		}
		return paramStr.substring(0, paramStr.length() - 1);		
	}
	
	
	
}
