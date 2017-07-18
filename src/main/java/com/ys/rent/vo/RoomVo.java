package com.ys.rent.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 房间的扩展类，用于多表查询
 * @author 云尚公寓
 *
 */
public class RoomVo {
	
	/**
	 * 房间表
	 */
	private String room_id;//房间id
	private String room_name;//房间名
	@NotNull
	private String price;//房间价格
	@NotNull
	private String roomDescription;//房间描述
	private String createUser;//创建人
	private Date createTime;//创建时间
	private String modifyUser;//修改人
	private Date modifyTime;//修改时间
	private String isRent;//是否闲置
	
	/**
	 * 租户表
	 */
	private int rent_day;//交租日
	private Date contract_term_start;//合同期开始时间
	
	/**
	 * 街道表
	 */
	private String street_id;//街道id
	private String street_name;//街道名
	
	/**
	 * 房间类型表
	 */
	private String roomType_name;//房间类型名
	
	
	public String getRoom_id() {
		return room_id;
	}
	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getIsRent() {
		return isRent;
	}
	public void setIsRent(String isRent) {
		this.isRent = isRent;
	}
	public int getRent_day() {
		return rent_day;
	}
	public void setRent_day(int rent_day) {
		this.rent_day = rent_day;
	}
	public Date getContract_term_start() {
		return contract_term_start;
	}
	public void setContract_term_start(Date contract_term_start) {
		this.contract_term_start = contract_term_start;
	}
	public String getStreet_name() {
		return street_name;
	}
	public void setStreet_name(String street_name) {
		this.street_name = street_name;
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
