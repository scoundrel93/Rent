package com.ys.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.rent.dao.OperatorDao;
import com.ys.rent.po.Operator;
import com.ys.rent.service.OperatorService;
import com.ys.rent.utils.PagerUtils;

/**
 * 日志的业务层实现类
 * @author 云尚公寓
 *
 */
@Service
public class OperatorServiceImpl implements OperatorService{
	
	@Autowired
	private OperatorDao operatorDao;

	/**
	 * 日志列表
	 */
	@Override
	public PagerUtils<Operator> getOperatorList(String operatorUser,
												int pageIndex) {
		PagerUtils<Operator> pr = new PagerUtils<Operator>();
		int dataCount = operatorDao.getOperatorCount(operatorUser);
		pr.setPageIndex(pageIndex);
		pr.setPageSize(10);
		pr.setDataCount(dataCount);
		pr.setPageCount(dataCount%10 == 0 ? dataCount/10 : dataCount/10 + 1);
		pr.setDataList(operatorDao.getOperatorList(operatorUser, 10 , 10 * (pageIndex - 1)));		
		return pr;
	}

	/**
	 * 添加日志
	 */
	@Override
	public void addOperator(Operator operator) {
		operatorDao.addOperator(operator);
	}


	
}
