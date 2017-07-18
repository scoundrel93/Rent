package com.ys.rent.service;

/**
 * 交租记录的service接口
 * @author 云尚公寓
 *
 */
public interface PayRecordService {

	/**
	 * 得到当月待交租金
	 * @return
	 */
	public int getMMoney();
}
