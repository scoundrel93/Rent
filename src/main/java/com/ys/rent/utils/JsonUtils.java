package com.ys.rent.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * JSON操作工具类
 * @author 云尚公寓
 *
 */
public class JsonUtils {

	/**
	 * 将JSON字符串转化为JSON对象
	 * @param jsonStr
	 * @return
	 */
	public static JsonObject String2JsonObject(String jsonStr)
	{
		return new JsonParser().parse(jsonStr).getAsJsonObject();
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> String2Map(String jsonStr)
	{
		Map<String,Object> map = new HashMap<String, Object>();
		Gson gson = new Gson();
		map = gson.fromJson(jsonStr,HashMap.class);
		return map;
	}
}
