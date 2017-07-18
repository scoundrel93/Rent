package com.ys.rent.service;

import java.util.List;

import com.ys.rent.po.RoomType;

/**
 * 房间类型的service接口
 * @author 云尚公寓
 *
 */
public interface RoomTypeService {
	/**
	 * 根据街道id得到房间类型列表
	 * @param street_id
	 * @return
	 */
	public List<RoomType> getRoomTypeListByStreetId(String street_id);

	/**
	 * 根据id得到房间类型列表信息
	 * @param roomType_id
	 * @return
	 */
	public RoomType getRoomTypeByRoomTypeId(String roomType_id);
}
