package com.ys.rent.po;

import java.util.Date;

/**
 * 操作日志的实体类
 * @author 云尚公寓
 *
 */
public class Operator {
	
	private String id;//日志表的id
	private String operatorUser;//操作人
	private Date operatorDate;//操作日期
	private String operatorModule;//操作模块
	private String operatorRemark;//操作备注
	private String operatorIP;//操作者的ip
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOperatorUser() {
		return operatorUser;
	}
	public void setOperatorUser(String operatorUser) {
		this.operatorUser = operatorUser;
	}
	public Date getOperatorDate() {
		return operatorDate;
	}
	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}
	public String getOperatorModule() {
		return operatorModule;
	}
	public void setOperatorModule(String operatorModule) {
		this.operatorModule = operatorModule;
	}
	public String getOperatorRemark() {
		return operatorRemark;
	}
	public void setOperatorRemark(String operatorRemark) {
		this.operatorRemark = operatorRemark;
	}
	public String getOperatorIP() {
		return operatorIP;
	}
	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
	}
	
	
	
}
