package com.ys.rent.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.ys.rent.apis.WeixinCRMUtils;
import com.ys.rent.po.User;
import com.ys.rent.service.UserService;
import com.ys.rent.utils.JsonUtils;

@Controller
@RequestMapping("/weixin")
public class WeixinController {
	
	private static final Logger logger = LoggerFactory.getLogger(WeixinController.class);
	
	@Autowired
	private UserService userService;

	/**
	 * 微信授权登录 
	 * 通过code获取AccessToken 和openid 从而获取用户信息
	 * @param code
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/oauth")
	public String GetWeixinUserInfo(@RequestParam("code")String code,@RequestParam("state")String state) throws Exception
	{
		if(!"".equals(code))
		{
			//1.获取到accesstoken 的结果
			String accessTokenResult = WeixinCRMUtils.GetAccessToken4UserLogin(code);
			
			//2.解析结果拿到openid和accesstoken
			Map<String,Object> accessTokenResultMap = JsonUtils.String2Map(accessTokenResult);
			String openId = (String) accessTokenResultMap.get("openid");
			String accessToken = (String) accessTokenResultMap.get("access_token");
			
			//3.通过openid和accesstoken 获取用户信息,拿到返回JSON
			String result = WeixinCRMUtils.GetUserInfo(accessToken,openId);
			
			//4.解析json
			Map<String,Object> userinfoResultMap = JsonUtils.String2Map(result);
			/* 返回结果
			openid	用户的唯一标识
			nickname	用户昵称
			sex	用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
			province	用户个人资料填写的省份
			city	普通用户个人资料填写的城市
			country	国家，如中国为CN
			headimgurl	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空*/
			
			if(userinfoResultMap.containsKey("errcode"))
			{
				logger.error("获取用户信息失败,返回结果:" + new Gson().toJson(userinfoResultMap));
				return "loginfail"; //登录失败页面
			}
			
			User user = userService.GetUserByOpenId(openId);
			if(user == null) //该用户第一次授权,则新增一个用户
			{
				user = new User();
				user.setUserType(0); //普通用户
				user.setAge(0);
				user.setWxusername((String)userinfoResultMap.get("nickname")); //微信昵称
				//user.setSex((int)userinfoResultMap.get("sex"));
				user.setOpenId((String)userinfoResultMap.get("openid"));
				
				userService.addUser(user);
			}
			else //该用户已存在,则将用户信息与微信同步
			{
				userService.editUser(user);
			}
		}
		
		return "";
	}

}
