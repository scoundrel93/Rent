package com.ys.rent.service;

import com.ys.rent.po.Operator;
import com.ys.rent.utils.PagerUtils;

/**
 * 日志的service接口
 * @author 云尚公寓
 *
 */
public interface OperatorService {
	
	/**
	 * 日志列表
	 * @param operatorUser
	 * @param pageIndex
	 * @return
	 */
	public PagerUtils<Operator> getOperatorList(String operatorUser,int pageIndex);
	
	/**
	 * 添加日志
	 * @param operator
	 */
	public void addOperator(Operator operator);
	
}
