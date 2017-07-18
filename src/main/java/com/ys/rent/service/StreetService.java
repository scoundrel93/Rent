package com.ys.rent.service;

import java.util.List;

import com.ys.rent.po.Street;

/**
 * 街道的service接口
 * @author 云尚公寓
 *
 */
public interface StreetService {
	/**
	 * 得到街道列表
	 * @return
	 */
	public List<Street> getStreetList();

	/**
	 * 根据街道id得到街道信息
	 * @param street_id
	 * @return
	 */
	public Street getStreetByStreetId(String street_id);
	
}
