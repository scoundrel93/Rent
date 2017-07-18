package com.ys.rent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.rent.dao.RoomTypeDao;
import com.ys.rent.po.RoomType;
import com.ys.rent.service.RoomTypeService;

/**
 * 房间类型的业务层实现类
 * @author 云尚公寓
 *
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService{
	
	@Autowired
	private RoomTypeDao roomTypeDao;

	/**
	 * 得到街道对应的栋号列表
	 */
	@Override
	public List<RoomType> getRoomTypeListByStreetId(String street_id) {
		return roomTypeDao.getRoomTypeListByStreetId(street_id);
	}

	/**
	 * 根据id得到街道信息
	 */
	@Override
	public RoomType getRoomTypeByRoomTypeId(String roomType_id) {
		return roomTypeDao.getRoomTypeByRoomTypeId(roomType_id);
	}



}
