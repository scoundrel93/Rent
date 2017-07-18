package com.ys.rent.apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;

import com.ys.rent.utils.HttpUtils;

/**
 * 微信接口
 * @author 云尚公寓
 *
 */
public class WeixinCRMUtils {
	
	private static final String URL_GETACCESSTOKEN = "";
	//https://open.weixin.qq.com/connect/oauth2/authorize?
	//appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
	@SuppressWarnings("unused")
	private static final String URL_GetCode = "https://open.weixin.qq.com/connect/oauth2/authorize";
	//?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
	private static final String URL_GetAccessToken4UserLogin = "https://api.weixin.qq.com/sns/oauth2/access_token";
	private static final String REDIRECT_URL = "";
	
	private static final String URL_GETUSERINFO = "https://api.weixin.qq.com/sns/userinfo";
	
	/**
	 * 获取微信接口授权
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String GetAccessToken() throws ClientProtocolException, IOException
	{			
		return "";
	}
	
	/**
	 * 微信授权第一步
	 * 		拿到当前用户授权码code的 URL
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String GetCode() throws Exception
	{
		 String appId = getAppId();
		 String state = "";
		 
		 StringBuilder sBuilder = new StringBuilder(URL_GETACCESSTOKEN);
		 sBuilder.append("?appid=" + appId);
		 sBuilder.append("&redirect_url=" + REDIRECT_URL);
		 sBuilder.append("&response_type=code");
		 sBuilder.append("&scope=snsapi_userinfo");
		 if("".equals(state))
		 {
			 sBuilder.append("&state=" + state);
		 }
		 sBuilder.append("#wechat_redirect");

		 return sBuilder.toString();	 	 
	}
	
	/**
	 * 微信授权第二步
	 * 		根据授权码code 拿到accesstoken(这里的accesstoken与上面的不同 只能用于微信授权登陆)
	 * @return
	 * @throws Exception 
	 */
	public static String GetAccessToken4UserLogin(String code) throws Exception
	{
		StringBuilder sBuilder = new StringBuilder(URL_GetAccessToken4UserLogin);
		sBuilder.append("?appid=" + getAppId());
		sBuilder.append("&secret=" + getAppSecret());
		sBuilder.append("&code=" + code);
		sBuilder.append("&grant_type=authorization_code");
		
		HttpEntity entity = HttpUtils.doGet(sBuilder.toString(), null);
		InputStream stream = entity.getContent();
		
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String line = "";
		while((line=br.readLine())!=null){
			sb.append(line);
		}
		return sb.toString();
	}
	
	/**
	 * 微信授权第三步
	 * 		根据accessToken 获取用户信息
	 * 
	 * @param accessToken 授权的AccessToken
	 * @param openid 用户openid
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String GetUserInfo(String accessToken,String openid) throws Exception
	{
		StringBuilder sBuilder = new StringBuilder(URL_GETUSERINFO);
		sBuilder.append("?access_token=" + accessToken);
		sBuilder.append("&openid=" + openid);
		
		HttpEntity entity = HttpUtils.doPost(sBuilder.toString());
		InputStream stream = entity.getContent();

		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String line = "";
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
	
	/**
	 * 从配置文件中读取微信appId
	 * @return
	 */
	private static String getAppId()
	{
		return "";
	}
	
	private static String getAppSecret()
	{
		return "";
	}
}
