package com.ys.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.rent.dao.PayRecordDao;
import com.ys.rent.service.PayRecordService;

/**
 * 交租记录的业务层实现类
 * @author 云尚公寓
 *
 */
@Service
public class PayRecordServiceImpl implements PayRecordService{

	@Autowired
	private PayRecordDao payRecordDao;
	
	/**
	 * 得到当月应交租金
	 */
	@Override
	public int getMMoney() {
		return payRecordDao.getMMoney();
	}

}
