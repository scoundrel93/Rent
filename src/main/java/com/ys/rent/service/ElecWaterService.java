package com.ys.rent.service;

import com.ys.rent.po.ElecWater;

/**
 * 水电的service接口
 * @author 云尚公寓
 *
 */
public interface ElecWaterService {

	/**
	 * 管理员进行每月的水电添加
	 * @param elecWater
	 */
	public void addElecWater(ElecWater elecWater);
	
	/**
	 * 管理员签约房间时添加一个水电底
	 * @param elecWater
	 */
	public void addElecWaterBySign(ElecWater elecWater);
}
