package com.ys.rent.po;

import java.util.Date;

/**
 * 角色的实体类
 * @author 云尚公寓
 *
 */
public class Role {
	
	private String id; //角色的id
	private String roleName; //角色的名字
	private String createUser;//角色的创建人
	private Date createTime;//角色的创建时间
	private String modifyUser;//角色的修改人
	private Date modifyTime;//角色的修改时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	
	
}
