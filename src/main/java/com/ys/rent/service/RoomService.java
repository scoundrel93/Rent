package com.ys.rent.service;


import com.ys.rent.po.Room;
import com.ys.rent.utils.PagerUtils;
import com.ys.rent.vo.RoomVo;

/**
 * 房间的service接口
 * @author 云尚公寓
 *
 */
public interface RoomService {
	
	/**
	 * 根据id查询房间，用于房间模块所有页面数据的回显，以及修改功能
	 * @param room_id
	 * @return
	 */
    public RoomVo findRoomById(String room_id);
	
	/**
	 * 根据街道和栋号得到房间列表,并且根据房间号查询房间
	 * @param room_name
	 * @param street_name
	 * @param roomType_name
	 * @param pageIndex
	 * @return
	 */
	public PagerUtils<Room> getRoomList(String room_name,
			                            String street_name,
			                            String roomType_name,
			                            int pageIndex);
	
	/**
	 * 根据街道得到房间状态列表,并且根据房间号查询房间
	 * @param room_name
	 * @param street_name
	 * @param isRent
	 * @param pageIndex
	 * @return
	 */
	public PagerUtils<Room> getRoomStatusList(String room_name,
				                            String street_name,
				                            String isRent,
				                            int pageIndex);
	
	/**
	 * 根据街道得到房间状态列表,并且根据房间号查询房间
	 * @param room_name
	 * @param street_name
	 * @param isRent
	 * @param pageIndex
	 * @return
	 */
	public PagerUtils<Room> getReturnRoomList(String room_name,
				                            String street_name,
				                            String isRent,
				                            int pageIndex);
	
	/**
	 * 根据id修改房间信息
	 * @param room
	 */
	public void editRoomById(RoomVo roomVo);
	
	/**
	 * 添加房间
	 * @param room
	 */
	public void addRoom(Room room);
	
	/**
	 * 得到闲置房间数量,所有
	 * @return
	 */
    public int getFreeRoomCount();
    
    /**
     * 得到已租房间数量
     * @return
     */
    public int getRentRoomCount();
    
    /**
	 * 得到闲置房间数量，根据街道
	 * @return
	 */
    public int getFreeRoomCountByStreetName(String street_name);
	
}
