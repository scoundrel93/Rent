package com.ys.rent.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式转换
 * @author 云尚公寓
 *
 */
public class CommonUtils {
	
	/**
	 * 字符串转日期
	 * @param s 要转换的字符串
	 * @param format 转换的格式
	 * @return
	 */
	public static Date string2Date(String s,String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(s);
		} catch (Exception e) {
			return null;
		}
	}


}
