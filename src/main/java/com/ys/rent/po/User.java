package com.ys.rent.po;

import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户的实体类
 * @author 云尚公寓
 *
 */
public class User{
	
	private String id; //用户id 
	private int userType;//用户类型
    private int sex;//用户性别
	private String remark;//备注
	private Date createTime; //创建时间
	private Date modifyTime;//修改时间
	private String openId;//微信的openId
	private String wxusername;//微信公众号用户名
	
	/**
	 * 以下是需要校验格式的字段
	 */
	@NotBlank(message="用户名不能为空") 
	private String name;//用户名

	@Length(min=1, max=11)
	private String phone;//用户电话
	
	@Min(value=10, message="年龄的最小值为10")
	private Integer age;//用户年龄

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
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

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getWxusername() {
		return wxusername;
	}

	public void setWxusername(String wxusername) {
		this.wxusername = wxusername;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	


}
