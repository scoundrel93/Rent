package com.ys.rent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.rent.dao.StreetDao;
import com.ys.rent.po.Street;
import com.ys.rent.service.StreetService;

/**
 * 街道的业务层实现类
 * @author 云尚公寓
 *
 */
@Service
public class StreetServiceImpl implements StreetService{
	
	@Autowired
	private StreetDao streetDao;

	/**
	 * 得到街道列表
	 */
	@Override
	public List<Street> getStreetList() {
		return streetDao.getStreetList();
	}

	/**
	 * 根据id得到街道
	 */
	@Override
	public Street getStreetByStreetId(String street_id) {
		return streetDao.getStreetByStreetId(street_id);
	}

}
