package com.ys.rent.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 水电的实体类
 * @author 云尚公寓
 *
 */
public class ElecWater {
	
	private String id;//水电记录表的id
	private int year;//记录水电的年份
	private int month;//记录水电的月份
	private int electric;//电表度数
	private BigDecimal e_money;//当前月电费
	private int water;//水表度数
	private BigDecimal w_money;//当前月水费
	private String createUser;//创建人
	private Date createTime;//创建时间
	private String modifyUser;//修改人
	private Date modifyTime;//修改时间
	
	private String room_id;//与房间关联的id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getElectric() {
		return electric;
	}

	public void setElectric(int electric) {
		this.electric = electric;
	}

	public BigDecimal getE_money() {
		return e_money;
	}

	public void setE_money(BigDecimal e_money) {
		this.e_money = e_money;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public BigDecimal getW_money() {
		return w_money;
	}

	public void setW_money(BigDecimal w_money) {
		this.w_money = w_money;
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

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}


	
	
}
