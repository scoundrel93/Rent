package com.ys.rent.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 租户的扩展类，用于多表查询
 * @author 云尚公寓
 *
 */
public class TenantVo {
	
	/**
	 * 租户表
	 */
	private String id;//租户id
	private String name;//租户姓名
	private String phone;//租户电话
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date contract_term_start;//合同期开始时间
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date contract_term_end;//合同期结束时间
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date time_end;//当月交租成功以后，当月列表页不再显示
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date water_time_end;//当月水电添加以后，当月列表不再显示
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date call_end;//用于短信通知
	
	private BigDecimal d_rent;//合同期前租金
	private BigDecimal m_rent;//月租金
	private BigDecimal incidental;//门禁卡押金和空调遥控器押金
	private BigDecimal cash_pledge;//房屋押金
	private BigDecimal management_cost;//管理费
	private BigDecimal sanitation_fee;//卫生费
	private BigDecimal sewage_fee;//排污费
	private int rent_day;//交租日
	private BigDecimal total;//总计

	private String remark;//备注
	private Date createTime;//创建时间
	private String createUser;//创建人
	private Date modifyTime;//修改时间
	private String modifyUser;//修改人
	
	private String reason;//禁用租户理由
	private int isForbid;//租户状态
	
	/**
	 * 房间表
	 */
	private String room_id;//房间的id
	private String room_name;//房间号
	
	/**
	 * 房间类型表
	 */
	private String roomType_id;//栋号的id
	private String roomType_name;//栋号名字
	
	/**
	 * 街道表
	 */
	private String street_id;//街道的id
	private String street_name;//街道名字
	
	/**
	 * 水电表
	 */
	private int w_money;//水费
	private int e_money;//电费
	
	
	/**
	 * 交租记录表
	 */
	private BigDecimal m_money;//每月实际交的费用（包括水电费和月基本租金）
	
	/**
	 * 用户表
	 */
	private String user_id;//用户的id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getContract_term_start() {
		return contract_term_start;
	}

	public void setContract_term_start(Date contract_term_start) {
		this.contract_term_start = contract_term_start;
	}

	public Date getContract_term_end() {
		return contract_term_end;
	}

	public void setContract_term_end(Date contract_term_end) {
		this.contract_term_end = contract_term_end;
	}

	public Date getTime_end() {
		return time_end;
	}

	public void setTime_end(Date time_end) {
		this.time_end = time_end;
	}

	public Date getWater_time_end() {
		return water_time_end;
	}

	public void setWater_time_end(Date water_time_end) {
		this.water_time_end = water_time_end;
	}

	public Date getCall_end() {
		return call_end;
	}

	public void setCall_end(Date call_end) {
		this.call_end = call_end;
	}

	public BigDecimal getD_rent() {
		return d_rent;
	}

	public void setD_rent(BigDecimal d_rent) {
		this.d_rent = d_rent;
	}

	public BigDecimal getM_rent() {
		return m_rent;
	}

	public void setM_rent(BigDecimal m_rent) {
		this.m_rent = m_rent;
	}

	public BigDecimal getIncidental() {
		return incidental;
	}

	public void setIncidental(BigDecimal incidental) {
		this.incidental = incidental;
	}

	public BigDecimal getCash_pledge() {
		return cash_pledge;
	}

	public void setCash_pledge(BigDecimal cash_pledge) {
		this.cash_pledge = cash_pledge;
	}

	public BigDecimal getManagement_cost() {
		return management_cost;
	}

	public void setManagement_cost(BigDecimal management_cost) {
		this.management_cost = management_cost;
	}

	public BigDecimal getSanitation_fee() {
		return sanitation_fee;
	}

	public void setSanitation_fee(BigDecimal sanitation_fee) {
		this.sanitation_fee = sanitation_fee;
	}

	public BigDecimal getSewage_fee() {
		return sewage_fee;
	}

	public void setSewage_fee(BigDecimal sewage_fee) {
		this.sewage_fee = sewage_fee;
	}

	public int getRent_day() {
		return rent_day;
	}

	public void setRent_day(int rent_day) {
		this.rent_day = rent_day;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getIsForbid() {
		return isForbid;
	}

	public void setIsForbid(int isForbid) {
		this.isForbid = isForbid;
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

	public String getStreet_name() {
		return street_name;
	}

	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}

	public int getW_money() {
		return w_money;
	}

	public void setW_money(int w_money) {
		this.w_money = w_money;
	}

	public int getE_money() {
		return e_money;
	}

	public void setE_money(int e_money) {
		this.e_money = e_money;
	}

	public BigDecimal getM_money() {
		return m_money;
	}

	public void setM_money(BigDecimal m_money) {
		this.m_money = m_money;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
