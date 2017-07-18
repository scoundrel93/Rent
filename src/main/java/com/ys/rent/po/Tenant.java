package com.ys.rent.po;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 租户的实体类
 * @author 云尚公寓
 *
 */
public class Tenant {
	
	private String id;//租户id
	private String name;//租户姓名
	private Date time_end;//交租截至时间（动态改变，用户交租以后自动变为下个月）
    private Date water_time_end;//水电截至时间（动态改变，抄完水电后自动变为下个月）
	private Date call_end;//催租截至时间（动态改变，发送催缴短信以后自动变为下个月）
	
    private String createUser;//租户的创建人
	private Date createTime;//租户的创建时间
	private String modifyUser;//租户的修改人
	private Date modifyTime;//租户的修改时间

	private BigDecimal m_rent;//月租金
	private BigDecimal d_rent;//合同期前租金
	private BigDecimal cash_pledge;//押金
	private BigDecimal management_cost;//管理费
	private BigDecimal sanitation_fee;//卫生费
	private BigDecimal sewage_fee;//排污费
	private BigDecimal incidental;//其他押金（门禁卡押金，空调遥控器押金）
	private BigDecimal total;//总计
	private int rent_day;//交租日
	
	private int isForbid;//租户状态
	private String reason;//禁用理由
	private String idCard;//租户的身份证
	private String address;//租户的身份证地址
	private String remark;//备注
	
	private String user_id;//与用户关联的id
	private String room_id;//与房间关联的id
	
	/**
	 * 以下是需要校验格式的字段
	 */
	@Length(min=1, max=11)
	private String phone;//租户电话
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date contract_term_start;//合同期开始时间
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date contract_term_end;//合同期结束时间

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

	public BigDecimal getM_rent() {
		return m_rent;
	}

	public void setM_rent(BigDecimal m_rent) {
		this.m_rent = m_rent;
	}

	public BigDecimal getD_rent() {
		return d_rent;
	}

	public void setD_rent(BigDecimal d_rent) {
		this.d_rent = d_rent;
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

	public BigDecimal getIncidental() {
		return incidental;
	}

	public void setIncidental(BigDecimal incidental) {
		this.incidental = incidental;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public int getRent_day() {
		return rent_day;
	}

	public void setRent_day(int rent_day) {
		this.rent_day = rent_day;
	}

	public int getIsForbid() {
		return isForbid;
	}

	public void setIsForbid(int isForbid) {
		this.isForbid = isForbid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
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

	
}
