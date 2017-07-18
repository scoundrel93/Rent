package com.ys.rent.apis;

import java.util.Map;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
/**
 * 阿里大于短信接口
 * @author 云尚公寓
 *
 */
public class AliDaYuMsg {
    public static final String appKey = "23814592";  //应用列表中可以找到
    public static final String appSecret = "3696a91ea12b19310fe7c6b8bc07377c";//secret
    public static final String url="http://gw.api.taobao.com/router/rest";   //网址
    //public static final String smsTemplateCode ="SMS_66800373";    //模板ID 
    public static final String smsFreeSignName ="云尚公寓";    //短信签名 
	    
	public static String send(Map<String, String> map,int type) throws ApiException{    
	    TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
		AlibabaAliqinFcSmsNumSendRequest req = buildAlibabaAliqinFcSmsNumSendRequest(map,type);
		/*req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName(smsFreeSignName);
		req.setSmsParamString(parameters);
		req.setRecNum(phone);
		req.setSmsTemplateCode(getSMSTemplete(type));*/
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		System.out.println(rsp);
		return rsp.getBody();
	}
	
	private static AlibabaAliqinFcSmsNumSendRequest buildAlibabaAliqinFcSmsNumSendRequest(
			Map<String, String> map,int type)
	{	
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		//req.setExtend(tenantVo.getUser_id());
		req.setSmsType("normal");
		req.setSmsFreeSignName(smsFreeSignName);
		req.setSmsParamString(getParameters(map,type));
		req.setRecNum(map.get("phone"));
		//req.setRecNum("17683836475");
		req.setSmsTemplateCode(getSMSTemplete(type));
		return req;
	}

	private static String getSMSTemplete(int type) {
		switch (type) {
		case 1:
			return "SMS_71160398";
		case 2:
			return "SMS_71305243";
		default:
			break;
		}
		return "";
	}

	private static String getParameters(Map<String, String> map, int type) {
		
		String result = "";
		switch (type) {
		case 1:				//签约通知老板
			result = "{\"name\":\"" + "李云仲" +  "\",\"street\":\"" + map.get("street_name") +  "\",\"room\":\"" + map.get("room_name") + "\",\"money\":\"" + map.get("money") +  "\"}";
			break;
		case 2:				//催租
			result = "{\"name\":\"" + map.get("name") +  "\",\"money\":\"" + map.get("m_money") +"\",\"date\":\"" + map.get("time_end") + "\"}";
			break;
		default:
			break;
		}
		return result;
	}
	
}
