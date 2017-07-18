package com.ys.rent.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ys.rent.po.ElecWater;
import com.ys.rent.po.Manager;
import com.ys.rent.service.ElecWaterService;
import com.ys.rent.service.OperatorService;
import com.ys.rent.service.RoomService;
import com.ys.rent.utils.UUIDUtils;
import com.ys.rent.vo.RoomVo;

/**
 * 水电的控制层
 * @author 云尚公寓
 *
 */
@Controller
@RequestMapping("/water")
public class ElecWaterController extends ControllerBase{
	
	//注入水电的service
	@Autowired
	private ElecWaterService elecWaterService;
	
	//注入房间的service
	@Autowired
	private RoomService roomService;
	
	//注入日志的service
	@Autowired
	private OperatorService operatorService;
	
	/**
	 * 从待抄水电列表页面跳转到添加水电页面，并回显相关数据
	 * @param room_id
	 * @param mm
	 * @return
	 */
	@RequestMapping("/add")
    public String add(String room_id,Model mm){
		RoomVo roomVo = roomService.findRoomById(room_id);
    	mm.addAttribute("roomVo",roomVo);
    	return "admin/room/water_add";
    }
	    
    /**
     * 添加水电，并且要分析出错情况(比如手机号格式错误等等)
     * @param elecWater
     * @param bindingresult
     * @param mm
     * @param street_name
     * @param httpSession
     * @param room_id
     * @return
     */
    @RequestMapping(value="/add.do",method= RequestMethod.POST)
    public String add(@Valid ElecWater elecWater,
    		           BindingResult bindingresult,
    		           Model mm,
    		           String street_name,
    		           HttpSession httpSession,
    		           String room_id)
    {
    	if (bindingresult.hasErrors()){
    		mm.addAttribute("fail","添加失败,请注意格式");
    		return "admin/room/water_add";  
    	}else {
    		//添加水电的时间
    		elecWater.setCreateTime(new Date());
    		//添加水电表的id
    		elecWater.setId(UUIDUtils.getUUID());
    		elecWater.setRoom_id(room_id);
    		
    		//得到当前登录用户，并设为添加人
    		Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    		elecWater.setCreateUser(currentManager.getUsername());
    		
    		//返回列表页时顺便将参数带过来
    		Map<String,Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("street_name", street_name);
        	mm.addAllAttributes(paramsMap);
    		
    		elecWaterService.addElecWater(elecWater);
    		
    		AddOperatorLog(currentManager.getUsername(),"水电模块","管理员添加水电");
    		   		
    		return "redirect:/tenant/tocopyewlist?page=1";
		}
    }
    
    /**
     * 从签约房间页面跳转到添加水电底的页面
     * @param mm
     * @return
     */
    @RequestMapping("/addbysign")
    public String addBySign(Model mm){
    	return "admin/room/water_addbysign";
    }
	    
    /**
     * 添加水电底
     * @param elecWater
     * @param bindingresult
     * @param mm
     * @param street_name
     * @param httpSession
     * @param room_id
     * @return
     */
    @RequestMapping(value="/addbysign.do",method= RequestMethod.POST)
    public String addBySign(@Valid ElecWater elecWater,
    		           BindingResult bindingresult,
    		           Model mm,
    		           String street_name,
    		           String roomType_name,
    		           HttpSession httpSession,
    		           String room_id)
    {
    	if (bindingresult.hasErrors()){
    		mm.addAttribute("fail","添加失败,请注意格式");
    		return "admin/room/water_addbysign";  
    	}else {
    		//添加水电表的id
    		elecWater.setId(UUIDUtils.getUUID());
    		//添加水电的时间
    		elecWater.setCreateTime(new Date());
    		//该水电表所关联的房间id
    		elecWater.setRoom_id(room_id);
    		
    		//得到当前登录用户，并设为添加人
    		Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    		elecWater.setCreateUser(currentManager.getUsername());
    		
    		//返回列表页时顺便将参数带过来
    		Map<String,Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("street_name", street_name);
        	paramsMap.put("roomType_name", roomType_name);
        	mm.addAllAttributes(paramsMap);
    		
    		elecWaterService.addElecWaterBySign(elecWater);
    		
    		AddOperatorLog(currentManager.getUsername(),"水电模块","管理员签约房间时添加一个水电底");
    		
    		return "redirect:/room/list?page=1";
		}
    }
    
}
