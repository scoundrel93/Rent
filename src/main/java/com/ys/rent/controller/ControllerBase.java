package com.ys.rent.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.ys.rent.po.Operator;
import com.ys.rent.service.OperatorService;
import com.ys.rent.utils.UUIDUtils;

public class ControllerBase {
	
	@Autowired
	private OperatorService operatorService;
		
	/**
	 * 添加管理员操作日志
	 * @param currentManagerName 当前管理员用户名
	 * @param modulName 模块名称
	 * @param remark 备注
	 */
	public void AddOperatorLog(String currentManagerName,String modulName,String remark)
	{
		Operator operator = new Operator();
		operator.setId(UUIDUtils.getUUID());
		
		operator.setOperatorUser(currentManagerName);
		
		operator.setOperatorDate(new Date());
		operator.setOperatorModule(modulName);
		operator.setOperatorRemark(remark);
		
		InetAddress addr=null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String operatorIP=addr.getHostAddress();//获得本机IP
		operator.setOperatorIP(operatorIP);
		
		operatorService.addOperator(operator);
	}
}
