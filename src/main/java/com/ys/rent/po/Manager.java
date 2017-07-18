package com.ys.rent.po;

import java.util.Date;

/**
 * 管理员实体类
 * @author 云尚公寓
 *
 */
public class Manager{
	
	private String id;//管理员的id
	private String username;//管理员姓名
	private String password;//管理员密码
	private Date createTime;//管理员的创建时间
	private Date modifyTime;//管理员的修改时间
	private Integer isUsed;//是否可用
	private Date lastloginTime;//最后一次登录时间
	private int loginTimes;//登录次数
	
	private String roleId;//与角色关联的id
	private String street_id;//与街道关联的id
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}
	public Date getLastloginTime() {
		return lastloginTime;
	}
	public void setLastloginTime(Date lastloginTime) {
		this.lastloginTime = lastloginTime;
	}
	public int getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}
	public String getStreet_id() {
		return street_id;
	}
	public void setStreet_id(String street_id) {
		this.street_id = street_id;
	}

	

	
	
}
