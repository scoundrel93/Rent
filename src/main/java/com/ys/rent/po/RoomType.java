package com.ys.rent.po;

/**
 * 房间类型的实体类
 * @author 云尚公寓
 *
 */
public class RoomType {
	
	private String roomType_id;//房间的id
	private String roomType_name;//房间类型名称
	private String street_id;//与街道关联的id
	
	public String getRoomType_id() {
		return roomType_id;
	}
	public void setRoomType_id(String roomType_id) {
		this.roomType_id = roomType_id;
	}
	public String getRoomType_name() {
		return roomType_name;
	}
	public void setRoomType_name(String roomType_name) {
		this.roomType_name = roomType_name;
	}
	public String getStreet_id() {
		return street_id;
	}
	public void setStreet_id(String street_id) {
		this.street_id = street_id;
	}

	
	
}
