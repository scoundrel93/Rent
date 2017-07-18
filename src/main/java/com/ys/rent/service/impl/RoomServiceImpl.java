package com.ys.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.rent.dao.ElecWaterDao;
import com.ys.rent.dao.RoomDao;
import com.ys.rent.po.Room;
import com.ys.rent.service.RoomService;
import com.ys.rent.utils.PagerUtils;
import com.ys.rent.vo.RoomVo;

/**
 * 房间的业务层实现类
 * @author 云尚公寓
 *
 */
@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private ElecWaterDao elecWaterDao;
	
	/**
	 * 根据id查询房间，用于房间模块所有页面数据的回显，以及修改功能
	 */
	@Override
	public RoomVo findRoomById(String room_id) {
		return roomDao.findRoomById(room_id);
	}
	
	/**
	 * 根据街道和栋号得到房间列表
	 */
	@Override
	public PagerUtils<Room> getRoomList(String room_name,String street_name,String roomType_name,
			int pageIndex) {
		PagerUtils<Room> pr = new PagerUtils<Room>();
		int dataCount = roomDao.getRoomCount(room_name,street_name,roomType_name);
		pr.setPageIndex(pageIndex);
		pr.setPageSize(10);
		pr.setDataCount(dataCount);
		pr.setPageCount(dataCount%10 == 0 ? dataCount/10 : dataCount/10 + 1);
		pr.setDataList(roomDao.getRoomList(room_name,street_name,roomType_name,10 , 10 * (pageIndex - 1)));		
		return pr;
	}
	
	/**
	 * 根据街道得到房间状态列表
	 */
	@Override
	public PagerUtils<Room> getRoomStatusList(String room_name,
			String street_name, String isRent, int pageIndex) {
		PagerUtils<Room> pr = new PagerUtils<Room>();
		int dataCount = roomDao.getRoomStatusCount(room_name,street_name,isRent);
		pr.setPageIndex(pageIndex);
		pr.setPageSize(10);
		pr.setDataCount(dataCount);
		pr.setPageCount(dataCount%10 == 0 ? dataCount/10 : dataCount/10 + 1);
		pr.setDataList(roomDao.getRoomStatusList(room_name,street_name,isRent,10 , 10 * (pageIndex - 1)));		
		return pr;
	}

	/**
	 * 根据id修改房间信息
	 */
	@Override
	public void editRoomById(RoomVo roomVo) {
		roomDao.editRoomById(roomVo);
	}

	/**
	 * 添加房间
	 */
	@Override
	public void addRoom(Room room) {
		roomDao.addRoom(room);
	}

	/**
	 * 得到闲置房间数量
	 */
	@Override
	public int getFreeRoomCount() {
		return roomDao.getFreeRoomCount();
	}

	/**
	 * 得到已租房间数量
	 */
	@Override
	public int getRentRoomCount() {
		return roomDao.getRentRoomCount();
	}

	/**
	 * 得到可转租房间列表
	 */
	@Override
	public PagerUtils<Room> getReturnRoomList(String room_name,
			String street_name, String isRent, int pageIndex) {
		PagerUtils<Room> pr = new PagerUtils<Room>();
		int dataCount = roomDao.getReturnRoomCount(room_name, street_name, isRent);
		pr.setPageIndex(pageIndex);
		pr.setPageSize(10);
		pr.setDataCount(dataCount);
		pr.setPageCount(dataCount%10 == 0 ? dataCount/10 : dataCount/10 + 1);
		pr.setDataList(roomDao.getReturnRoomList(room_name,street_name,isRent,10 , 10 * (pageIndex - 1)));		
		return pr;
	}

	@Override
	public int getFreeRoomCountByStreetName(String street_name) {
		return roomDao.getFreeRoomCountByStreetName(street_name);
	}

	
}
