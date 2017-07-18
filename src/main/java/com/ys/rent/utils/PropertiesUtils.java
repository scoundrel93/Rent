package com.ys.rent.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取properties文件 工具类
 * @author 云尚公寓
 *
 */
public class PropertiesUtils {
	public String GetValue(String key,String filePath) {
		if(!"".equals(key) && null != key)
		{
			Properties prop = new Properties();
			try {
				//读取属性文件a.properties
				InputStream in = new BufferedInputStream(new FileInputStream(filePath));
				prop.load(in); // /加载属性列表				
				return prop.getProperty(key);								
			} catch (Exception e) {
				return "";
			}
		}
		else
		{
			return "";
		}
		
	}
}
