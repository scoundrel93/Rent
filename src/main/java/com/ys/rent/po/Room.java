package com.ys.rent.po;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
/**
 * 房间的实体类
 * @author 云尚公寓
 *
 */
public class Room {
	
	private String room_id;//房间的id
	private String createUser;//房间添加人
	private Date createTime;//房间添加时间
	private String modifyUser;//房间修改人
	private Date modifyTime;//房间修改时间
	private int isRent;//房间状态
	
	private String roomType_id;//与房间类型关联的id
	
	/**
	 * 以下是需要对其格式进行校验的字段
	 */
	@NotNull
	private String room_name;//房间号
	@NotNull
	private BigDecimal price;//房间价格
	@NotNull
	private String roomDescription;//房间描述
	
	public String getRoomType_id() {
		return roomType_id;
	}
	public void setRoomType_id(String roomType_id) {
		this.roomType_id = roomType_id;
	}
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
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
	public int getIsRent() {
		return isRent;
	}
	public void setIsRent(int isRent) {
		this.isRent = isRent;
	}

	
}
