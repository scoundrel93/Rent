package com.ys.rent.utils;

import java.util.UUID;

/**
 * 生成uuid
 * @author 云尚公寓
 *
 */
public class UUIDUtils {
	
	/**
	 * UUID
	 * @return
	 */
	public static String getUUID()
	{
		return UUID.randomUUID().toString().replace("-","");
	}
}
