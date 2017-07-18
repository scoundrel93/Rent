package com.ys.rent.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 交租记录表的实体类
 * @author 云尚公寓
 *
 */
public class PayRecord {
	
	private String id;//交租记录表的id
	private BigDecimal m_money;//租户实际每月交的租金（包括水电费和月基本租金）
	private Date rent_date;//租户实际交租日期
	private int year;//年份
	private int month;//月份
	private int isPay;//是否已交

	private String room_id;//与房间关联的id
	private String tenant_id;//与租户关联的id
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getM_money() {
		return m_money;
	}
	public void setM_money(BigDecimal m_money) {
		this.m_money = m_money;
	}
	public Date getRent_date() {
		return rent_date;
	}
	public void setRent_date(Date rent_date) {
		this.rent_date = rent_date;
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
	public int getIsPay() {
		return isPay;
	}
	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}
	public String getRoom_id() {
		return room_id;
	}
	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	public String getTenant_id() {
		return tenant_id;
	}
	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}
	
	

	
	
}
