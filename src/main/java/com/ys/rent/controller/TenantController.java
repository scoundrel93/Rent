package com.ys.rent.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taobao.api.ApiException;
import com.ys.rent.po.Manager;
import com.ys.rent.po.Role;
import com.ys.rent.po.Street;
import com.ys.rent.po.Tenant;
import com.ys.rent.service.OperatorService;
import com.ys.rent.service.RoleService;
import com.ys.rent.service.RoomService;
import com.ys.rent.service.RoomTypeService;
import com.ys.rent.service.StreetService;
import com.ys.rent.service.TenantService;
import com.ys.rent.service.UserService;
import com.ys.rent.utils.PagerUtils;
import com.ys.rent.utils.UUIDUtils;
import com.ys.rent.vo.RoomVo;
import com.ys.rent.vo.TenantVo;

/**
 * 租户的控制层
 * @author 云尚公寓
 *
 */
@Controller
@RequestMapping("/tenant")  
public class TenantController extends ControllerBase{
	
	//注入租户的service
	@Autowired
	private TenantService tenantService;
	
	//注入房间的service
	@Autowired
	private RoomService roomService;
	
	//注入用户的service
	@Autowired
	private UserService userService;
	
	//注入街道的service
	@Autowired
	private StreetService streetService;
	
	//注入房间类型的service
	@Autowired
	private RoomTypeService roomTypeService;
	
	//注入日志的service
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private RoleService roleService;
	
	
    /**
     * 从主页面跳转到选择街道的页面
     * @param mm
     * @return
     */
	@RequestMapping("/choose")
	public String choose(Model mm){
		List<Street> streetList = streetService.getStreetList();
    	mm.addAttribute("streetList",streetList);
    	
		return "admin/tenant/tenant_choose";
	}
	
	 /**
     * 从主页面跳转到选择街道的页面
     * @param mm
     * @return
     */
	@RequestMapping("/streetchoose")
	public String streetchoose(Model mm){
		List<Street> streetList = streetService.getStreetList();
    	mm.addAttribute("streetList",streetList);
    	
		return "admin/tenant/street_choose";
	}
	
	 /**
     * 从主页面跳转到选择街道的页面
     * @param mm
     * @return
     */
	@RequestMapping("/forbidchoose")
	public String forbidchoose(Model mm){
		List<Street> streetList = streetService.getStreetList();
    	mm.addAttribute("streetList",streetList);
    	
		return "admin/tenant/forbid_choose";
	}
	
	/**
     * 从主页面跳转到选择街道的页面
     * @param mm
     * @return
     */
	@RequestMapping("/expirechoose")
	public String expirechoose(Model mm){
		List<Street> streetList = streetService.getStreetList();
    	mm.addAttribute("streetList",streetList);
    	
		return "admin/tenant/expire_choose";
	}
	
	
	/**
	 * 根据街道和栋号得到租户列表,并且根据租户名查询租户
	 * @param page
	 * @param name
	 * @param street_name
	 * @param roomType_name
	 * @param mm
	 * @return
	 */
    @RequestMapping(value="/list")
    public String getTenantList(@RequestParam("page")int page,
    		                    @RequestParam(value="room_name",required=false)String room_name,
    		                    @RequestParam(value="street_name",required=false)String street_name,
    		                    @RequestParam(value="roomType_name",required=false)String roomType_name,
    		                    ModelMap mm,
    		                    HttpSession httpSession){
    	
    	//获取当前登录用户信息
    	Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    	String roleId = currentManager.getRoleId();
    	Role role = roleService.getroleName(roleId);
    	String role_name = role.getRoleName();
    	
    	if (role_name.equals("超级管理员")){
    		//得到分页数据
	    	PagerUtils<Tenant> pr = tenantService.getTenantList(room_name,street_name, roomType_name, page);
	    	mm.addAttribute("pageinfo",pr);
	    	
	    	//将查询参数回显到页面上
	    	Map<String,Object> paramsMap = new HashMap<String, Object>();
	    	paramsMap.put("street_name", street_name);
	    	paramsMap.put("roomType_name", roomType_name);
	    	mm.addAllAttributes(paramsMap);
		}else {
			//得到管理员对应的街道id
        	String street_id = currentManager.getStreet_id();
        	//根据街道id得到对应的街道名字
        	Street street = streetService.getStreetByStreetId(street_id);
        	String street_name1 = street.getStreet_name();
        	
        	//得到分页数据
        	PagerUtils<Tenant> pr = tenantService.getTenantList(room_name,street_name1, roomType_name, page);
        	mm.addAttribute("pageinfo",pr);
        	
        	//将查询参数回显到页面上
        	Map<String,Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("street_name", street_name1);
        	paramsMap.put("roomType_name", roomType_name);
        	mm.addAllAttributes(paramsMap);
		}
    	
    	return "admin/tenant/tenant_list";
    }
    
    /**
	 * 根据街道和栋号得到当月到期租户列表,并且根据租户名查询租户
	 * @param page
	 * @param name
	 * @param street_name
	 * @param roomType_name
	 * @param mm
	 * @return
	 */
    @RequestMapping(value="/expirelist")
    public String getExpireTenantList(@RequestParam("page")int page,
    		                    @RequestParam(value="room_name",required=false)String room_name,
    		                    @RequestParam(value="street_name",required=false)String street_name,
    		                    ModelMap mm,
    		                    HttpSession httpSession){
    	
    	//获取当前登录用户信息
    	Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    	String roleId = currentManager.getRoleId();
    	Role role = roleService.getroleName(roleId);
    	String role_name = role.getRoleName();
    	
    	if (role_name.equals("超级管理员")){
    		//得到分页数据
	    	PagerUtils<Tenant> pr = tenantService.getExpireTenantList(room_name,street_name,page);
	    	mm.addAttribute("pageinfo",pr);
	    	
	    	//将查询参数回显到页面上
	    	Map<String,Object> paramsMap = new HashMap<String, Object>();
	    	paramsMap.put("street_name", street_name);
	    	mm.addAllAttributes(paramsMap);
	    	
		}else {
			//得到管理员对应的街道id
        	String street_id = currentManager.getStreet_id();
        	//根据街道id得到对应的街道名字
        	Street street = streetService.getStreetByStreetId(street_id);
        	String street_name1 = street.getStreet_name();
        	
        	//得到分页数据
        	PagerUtils<Tenant> pr = tenantService.getExpireTenantList(room_name,street_name1,page);
        	mm.addAttribute("pageinfo",pr);
        	
        	//将查询参数回显到页面上
        	Map<String,Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("street_name", street_name1);
        	mm.addAllAttributes(paramsMap);
		}
    	
    	return "admin/tenant/tenant_expirelist";
    }
    
    /**
     * 从租户列表页面跳转到修改租户信息页面，并且回显数据
     * @param mm
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model mm,String id){
    	 TenantVo tenantVo = tenantService.selectTenantById(id);
    	 mm.addAttribute("tenantVo",tenantVo);
    	 return "admin/tenant/tenant_edit";
    }
    
    /**
     * 修改租户，也要分析出错情况(修改时信息输入错误)
     * @param tenant
     * @param bindingresult
     * @param street_name
     * @param roomType_name
     * @param mm
     * @param httpSession
     * @return
     */
    @RequestMapping(value="/edit.do",method= RequestMethod.POST)
    public String edit(@Valid Tenant tenant,
    		           BindingResult bindingresult,
    		           String street_name,
    		           String roomType_name,
    		           Model mm,
    		           HttpSession httpSession){
    	if (bindingresult.hasErrors()){
    		mm.addAttribute("fail","修改失败,请注意格式");
    		return "admin/tenant/tenant_edit";  
    	}else {
    		//返回列表页时顺便将这两个参数带过来
    		Map<String,Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("street_name", street_name);
        	paramsMap.put("roomType_name", roomType_name);
        	mm.addAllAttributes(paramsMap);
        	
    		//设置修改租户时的时间
    		tenant.setModifyTime(new Date());
    		
    		//得到当前登录用户，并设为修改人
    		Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    		tenant.setModifyUser(currentManager.getUsername());
    		
    		tenantService.editTenantById(tenant);
    		
    		AddOperatorLog(currentManager.getUsername(),"租户模块","修改租户信息");
			return "redirect:list?page=1";
		}
    }
    
    /**
     * 从租户列表页跳转到禁用租户页面,并回显数据
     * @param mm
     * @param id
     * @return
     */
    @RequestMapping("/forbid")
    public String forbid(Model mm,String id){
    	 TenantVo tenantVo = tenantService.selectTenantById(id);
    	 mm.addAttribute("tenantVo",tenantVo);
     	 return "admin/tenant/tenant_forbid";
    }
    
    /**
     * 禁用租户，并且重定向到禁用租户列表页面
     * @param tenant
     * @param mm
     * @param httpSession
     * @return
     */
    @RequestMapping(value="/forbid.do",method= RequestMethod.POST)
    public String forbid(Tenant tenant,
    					 Model mm,
    					 String street_name,
    					 HttpSession httpSession){
		//设置禁用用户时的时间
    	tenant.setModifyTime(new Date());
    	
    	//得到当前用户，并设为禁用人
		Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
		tenant.setModifyUser(currentManager.getUsername());
		
		tenantService.updateTenantById(tenant);
		
		AddOperatorLog(currentManager.getUsername(),"租户模块","禁用租户");
		
		return "redirect:forbidlist?page=1&street_name="+street_name;
    }
    
    /**
     * 从主页面跳转到禁用租户列表页面
     * @param page
     * @param name
     * @param mm
     * @return
     */
    @RequestMapping(value="/forbidlist")
    public String getForbidTenantList(@RequestParam("page")int page,
    		 						  @RequestParam(value="name",required=false)String name,
    		 						  @RequestParam(value="street_name",required=false)String street_name,
    		 						  ModelMap mm,
    		 						  HttpSession httpSession){
    	
    	//获取当前登录用户信息
    	Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    	String roleId = currentManager.getRoleId();
    	Role role = roleService.getroleName(roleId);
    	String role_name = role.getRoleName();
    	
    	if (role_name.equals("超级管理员")){
    		PagerUtils<Tenant> pr = tenantService.getForbidTenantList(name,street_name,page);
        	mm.addAttribute("pageinfo",pr); //分页数据
        	
        	//将查询参数回显到页面上
        	Map<String,Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("street_name", street_name);
        	mm.addAllAttributes(paramsMap);
		}else {
			//得到管理员对应的街道id
        	String street_id = currentManager.getStreet_id();
        	//根据街道id得到对应的街道名字
        	Street street = streetService.getStreetByStreetId(street_id);
        	String street_name1 = street.getStreet_name();
        	
        	PagerUtils<Tenant> pr = tenantService.getForbidTenantList(name,street_name1,page);
        	mm.addAttribute("pageinfo",pr); //分页数据
        	
        	//将查询参数回显到页面上
        	Map<String,Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("street_name", street_name1);
        	mm.addAllAttributes(paramsMap);
		}
    	
    	return "admin/tenant/forbidtenant_list";
    }
    
    /**
     * 删除禁用租户，删除成功后跳转到禁用租户列表页面
     * @param tenant
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Tenant tenant,HttpSession httpSession,String id){
    	//根据租户id得到对应的街道名称，用于删除以后返回该街道对应的禁用租户列表
    	TenantVo tenantVo = tenantService.selectTenantById(id);
    	String street_name = tenantVo.getStreet_name();
    	
    	tenantService.deleteForbidTenantById(tenant);
    	
    	Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    	AddOperatorLog(currentManager.getUsername(),"租户模块","删除禁用租户");
    	
    	return "redirect:forbidlist?page=1&street_name="+street_name;
    }
    
    /**
     * 合同期查询功能，可查询某年某月到期租户
     * @param page
     * @param year
     * @param month
     * @param mm
     * @return
     */
    @RequestMapping(value="/contract")
    public String selectYearAndMonth(@RequestParam("page")int page,
    								 @RequestParam(value="year",required=false)String year,
    								 @RequestParam(value="month",required=false)String month,
    								 @RequestParam(value="street_name",required=false)String street_name,
    								 ModelMap mm,
    								 HttpSession httpSession){
    	
    	//获取当前登录用户信息
    	Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    	String roleId = currentManager.getRoleId();
    	Role role = roleService.getroleName(roleId);
    	String role_name = role.getRoleName();
    	
    	if (role_name.equals("超级管理员")){
    		PagerUtils<Tenant> pr = tenantService.selectContract(year, month,street_name, page);
        	mm.addAttribute("pageinfo",pr); //分页数据
        	
        	//将查询参数回显到页面上
        	Map<String,Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("year", year);
        	paramsMap.put("month", month);
        	paramsMap.put("street_name", street_name);
        	mm.addAllAttributes(paramsMap);
		}else {
			//得到管理员对应的街道id
        	String street_id = currentManager.getStreet_id();
        	//根据街道id得到对应的街道名字
        	Street street = streetService.getStreetByStreetId(street_id);
        	String street_name1 = street.getStreet_name();
        	
        	PagerUtils<Tenant> pr = tenantService.selectContract(year, month,street_name1, page);
        	mm.addAttribute("pageinfo",pr); //分页数据
        	
        	//将查询参数回显到页面上
        	Map<String,Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("year", year);
        	paramsMap.put("month", month);
        	paramsMap.put("street_name", street_name1);
        	mm.addAllAttributes(paramsMap);
		}
    	
    	return "admin/tenant/tenant_contract";
    }
    
    /**
     * 从房间列表页面跳转到签约房间页面
     * @param room_id
     * @param model
     * @return
     */
    @RequestMapping("/sign")
    public String sign(String room_id,Model model){
    	RoomVo roomVo = roomService.findRoomById(room_id);
    	model.addAttribute("roomVo",roomVo);
    	return "admin/tenant/tenant_sign";
    }
    
    /**
     * 签约房间
     * @param tenantVo
     * @param bindingresult
     * @param street_name
     * @param roomType_name
     * @param room_id
     * @param mm
     * @param httpSession
     * @return
     */
    @RequestMapping(value="/sign.do",method= RequestMethod.POST)
    public String sign(@Valid TenantVo tenantVo,
    		                  BindingResult bindingresult,
    		                  String street_name,
    		                  String roomType_name,
    		                  String room_id,
    		                  Model mm,
    		                  HttpSession httpSession){
    	if (bindingresult.hasErrors()){
    		System.out.println(bindingresult+"8888888888888888888888888888888888888888888888888");
    		mm.addAttribute("fail","签约失败,请注意格式");
    		return "admin/tenant/tenant_sign";  
    	}else {
    		//返回列表页时顺便将这两个参数带过来
    		Map<String,Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("street_name", street_name);
        	paramsMap.put("roomType_name", roomType_name);
        	mm.addAllAttributes(paramsMap);
    		
    		//添加创建用户的时间
        	tenantVo.setCreateTime(new Date());
    		Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    		tenantVo.setCreateUser(currentManager.getUsername());
    		//添加租户的id
    		tenantVo.setId(UUIDUtils.getUUID());
    		//生成租户与房间关联的id
    		tenantVo.setRoom_id(room_id);
    		
    		try {
				tenantService.addTenantBySign(tenantVo);
			} catch (Exception e) {

				e.printStackTrace();
			}
    		
    		//跳转到添加水电底的页面，将房间id带过去
    		RoomVo roomVo = roomService.findRoomById(room_id);
        	mm.addAttribute("roomVo",roomVo);
        	
        	AddOperatorLog(currentManager.getUsername(),"租户模块","签约房间");
      		
			return "admin/room/water_addbysign";
		}
    }
    
    /**
     * 从主页面跳转到待交租列表选择街道的页面
     * @param mm
     * @return
     */
	@RequestMapping("/topaychoose")
	public String topaychoose(Model mm){
		List<Street> streetList = streetService.getStreetList();
    	mm.addAttribute("streetList",streetList);
    	
		return "admin/tenant/tenant_topaychoose";
	}
    
    /**
	 * 待交租列表
	 * @param page
	 * @param name
	 * @param street_name
	 * @param mm
	 * @return
	 */
    @RequestMapping(value="/topaylist")
    public String getToPayTenantList(@RequestParam("page")int page,
    		                    @RequestParam(value="name",required=false)String name,
    		                    @RequestParam(value="street_name",required=false)String street_name,
    		                    ModelMap mm,
    		                    HttpSession httpSession){
    	
    	//获取当前登录用户信息
     	Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
     	String roleId = currentManager.getRoleId();
     	Role role = roleService.getroleName(roleId);
     	String role_name = role.getRoleName();
     	
     	if (role_name.equals("超级管理员")){
     		//得到分页数据
        	PagerUtils<Tenant> pr = tenantService.getToPayTenantList(name,street_name,page);
        	mm.addAttribute("pageinfo",pr);
        	
        	//将查询参数回显到页面上
        	Map<String,Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("street_name", street_name);
        	mm.addAllAttributes(paramsMap);
 		}else {
 			//得到管理员对应的街道id
         	String street_id = currentManager.getStreet_id();
         	//根据街道id得到对应的街道名字
         	Street street = streetService.getStreetByStreetId(street_id);
         	String street_name1 = street.getStreet_name();
         	
         	//得到分页数据
        	PagerUtils<Tenant> pr = tenantService.getToPayTenantList(name,street_name1,page);
        	mm.addAttribute("pageinfo",pr);
        	
        	//将查询参数回显到页面上
        	Map<String,Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("street_name", street_name1);
        	mm.addAllAttributes(paramsMap);
 		}
    	
    	return "admin/tenant/tenant_topaylist";
    }
    
    /**
     * 从主页面跳转到待抄水电列表选择街道的页面
     * @param mm
     * @return
     */
	@RequestMapping("/tocopyewchoose")
	public String tocopyewchoose(Model mm){
		List<Street> streetList = streetService.getStreetList();
    	mm.addAttribute("streetList",streetList);
    	
		return "admin/tenant/tenant_tocopyewchoose";
	}
    
    /**
   	 * 待抄水电列表
   	 * @param page
   	 * @param name
   	 * @param street_name
   	 * @param mm
   	 * @return
   	 */
     @RequestMapping(value="/tocopyewlist")
     public String getToCopyEWTenantList(@RequestParam("page")int page,
   		                    @RequestParam(value="room_name",required=false)String room_name,
   		                    @RequestParam(value="street_name",required=false)String street_name,
   		                    ModelMap mm,
   		                    HttpSession httpSession){
    	 
    	//获取当前登录用户信息
     	Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
     	String roleId = currentManager.getRoleId();
     	Role role = roleService.getroleName(roleId);
     	String role_name = role.getRoleName();
     	
     	if (role_name.equals("超级管理员")){
			//得到分页数据
			PagerUtils<Tenant> pr = tenantService.getToCopyEWTenantList(room_name,street_name,page);
			mm.addAttribute("pageinfo",pr);
			
			//将查询参数回显到页面上
			Map<String,Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("street_name", street_name);
			mm.addAllAttributes(paramsMap);
 		}else {
 			//得到管理员对应的街道id
         	String street_id = currentManager.getStreet_id();
         	//根据街道id得到对应的街道名字
         	Street street = streetService.getStreetByStreetId(street_id);
         	String street_name1 = street.getStreet_name();
         	
         	//得到分页数据
			PagerUtils<Tenant> pr = tenantService.getToCopyEWTenantList(room_name,street_name1,page);
			mm.addAttribute("pageinfo",pr);
			
			//将查询参数回显到页面上
			Map<String,Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("street_name", street_name1);
			mm.addAllAttributes(paramsMap);
 		}
	     return "admin/tenant/tenant_tocopyewlist";
     }
     
     /**
      * 催租
      * @param id
      * @param model
      * @return
      */
     @RequestMapping("/call")
     public String call(Tenant tenant,
    		 				 Model model,
    		 				 HttpSession httpSession){
    	 
    	 try {    		     	  		 
			tenantService.updatecallEnd(tenant);
		} catch (ApiException e) {
			e.printStackTrace();
		}
    	Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    	AddOperatorLog(currentManager.getUsername(),"租户模块","催租");
   		
   		//得到street_name,用于页面跳转
   		String id = tenant.getId();
   		TenantVo tenantVo = tenantService.findTenantById(id);
   		String street_name = tenantVo.getStreet_name();
   		
     	return "redirect:topaylist?page=1&street_name="+street_name;
     }
     
     /**
      * 标记为已交
      * @param id
      * @param model
      * @return
      */
     @RequestMapping("/time_end")
     public String time_end(Tenant tenant,
    		 				 Model model,
    		 				 HttpSession httpSession){
    	 
		tenantService.updatetimeEnd(tenant);
		Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    	AddOperatorLog(currentManager.getUsername(),"租户模块","标记为已交");
   		
   		//得到street_name,用于页面跳转
   		String id = tenant.getId();
   		TenantVo tenantVo = tenantService.findTenantById(id);
   		String street_name = tenantVo.getStreet_name();
   		
     	return "redirect:topaylist?page=1&street_name="+street_name;
     }
     
    
}
