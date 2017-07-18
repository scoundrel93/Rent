package com.ys.rent.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ys.rent.po.Operator;
import com.ys.rent.service.OperatorService;
import com.ys.rent.utils.PagerUtils;

/**
 * 日志控制层
 * @author 云尚公寓
 *
 */
@Controller
@RequestMapping("/operator")
public class OperatorController{
	
	//注入日志的service
	@Autowired
	private OperatorService operatorService;

	/**
	 * 日志列表
	 * @param page
	 * @param operatorUser
	 * @param mm
	 * @return
	 */
    @RequestMapping("/list")
    public String getList(@RequestParam("page")int page,
    		              @RequestParam(value="operatorUser",required=false)String operatorUser,
    		              ModelMap mm){
    	PagerUtils<Operator> pr = operatorService.getOperatorList(operatorUser, page);
    	mm.addAttribute("pageinfo",pr); //分页数据
    	
    	Map<String,Object> paramsMap = new HashMap<String, Object>();
    	paramsMap.put("operatorUser", operatorUser);
    	mm.addAllAttributes(paramsMap);
    	
    	return "admin/operator/operator_list";
    }
	
	
}
