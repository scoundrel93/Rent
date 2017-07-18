package com.ys.rent.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ys.rent.po.Manager;
import com.ys.rent.po.Role;
import com.ys.rent.po.Street;
import com.ys.rent.service.ManagerService;
import com.ys.rent.service.OperatorService;
import com.ys.rent.service.PayRecordService;
import com.ys.rent.service.RoleService;
import com.ys.rent.service.RoomService;
import com.ys.rent.service.StreetService;
import com.ys.rent.service.TenantService;
import com.ys.rent.utils.CommonUtils;
import com.ys.rent.utils.MD5Utils;
import com.ys.rent.utils.PagerUtils;
import com.ys.rent.utils.UUIDUtils;
import com.ys.rent.vo.ManagerVo;

/**
 * 管理员的控制层
 * @author 云尚公寓
 *
 */

@Controller
@RequestMapping("/admin")
public class ManagerController extends ControllerBase{
	
	
	private static final Log log = LogFactory.getLog(ManagerController.class);
	//注入管理员的service
    @Autowired
    private ManagerService managerService;
    
    //注入角色的service
    @Autowired
    private RoleService roleService;
    
    //注入房间的service
    @Autowired
    private RoomService roomService;
    
    //注入交租记录的service
    @Autowired
    private PayRecordService payRecordService;
    
    //注入日志的service
    @Autowired
	private OperatorService operatorService;
    
    @Autowired
    private StreetService streetService;
    
    @Autowired
    private TenantService tenantService;
    
    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/login")
    public String loginIndex(){
    	return "adminlogin";
    }
    
    /**
     * 欢迎页面
     * @param mm
     * @return
     */
    @RequestMapping("/welcome")
    public String pageWelcome(Model mm,HttpSession httpSession)
    {
    	int freeroomTotal = roomService.getFreeRoomCount();
    	mm.addAttribute("freeroomTotal",freeroomTotal);
    	System.out.println("查询出闲置房间:" + freeroomTotal);
    	
    	int roomTotal = roomService.getRentRoomCount();
    	mm.addAttribute("roomTotal",roomTotal);
    	System.out.println("查询出已租房间:" + roomTotal);
    	
    	int mmoneyTotal = payRecordService.getMMoney();
    	mm.addAttribute("mmoneyTotal",mmoneyTotal);
    	System.out.println("当前待交:" + mmoneyTotal);
    	
    	//获取当前登录用户信息
    	Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    	//得到对应的角色id
    	String roleId = currentManager.getRoleId();
    	//用于权限控制
    	Role role = roleService.getroleName(roleId);
    	mm.addAttribute("role",role);
    	String role_name = role.getRoleName();
    	if (!role_name.equals("超级管理员")){
    		System.out.println("当前不是超级管理员登录");
    		String street_id = currentManager.getStreet_id();
        	Street street = streetService.getStreetByStreetId(street_id);
        	String street_name = street.getStreet_name();
        	mm.addAttribute("street_name",street_name);
        	int expireStreetTotal = tenantService.getExpireCountBystreet(street_name);
        	mm.addAttribute("expireStreetTotal",expireStreetTotal);
    	}
    	//得到总的数量
    	int expireTotal = tenantService.getExpireCount();
    	mm.addAttribute("expireTotal",expireTotal);
    	System.out.println("得到本月到期房间:" + expireTotal);
    	return "admin/welcome";
    }
    
    /**
     * 跳转到主页面（主页面里包含欢迎页面）
     * @return
     */
    @RequestMapping("/index")
    public String pageIndex(Model mm,HttpSession httpSession)
    {
    	//获取当前登录用户信息
    	Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    	//得到对应的角色id
    	String id = currentManager.getRoleId();
    	
    	//用于权限控制
    	Role role = roleService.getroleName(id);
    	mm.addAttribute("role",role);
    	return "admin/index";
    }
    
    /**
     * 管理员登陆
     * @param username
     * @param password
     * @param passcode
     * @param httpSession
     * @param mm
     * @return
     */
    @RequestMapping("/doLogin")
    public String login(String username,String password,String passcode,HttpSession httpSession,ModelMap mm){
    	if(username==null || username=="")
    	{
    		mm.addAttribute("errMsg", "用户名不能为空");
    		return "adminlogin";
    	}
    	if(password==null || password == "")
    	{
    		mm.addAttribute("errMsg", "密码不能为空");
    		return "adminlogin";
    	}
    	if(passcode==null || passcode == "")
    	{
    		mm.addAttribute("errMsg", "验证码不能为空");
    		return "adminlogin";
    	}
    	if(!passcode.equalsIgnoreCase(httpSession.getAttribute("RANDOMCODE").toString()))
    	{
    		mm.addAttribute("errMgr", "验证码填写错误");
    		return "adminlogin";
    	}
    	

    	
    	Manager existManager = managerService.login(username,MD5Utils.MD5(password));
    	
        if(existManager != null){
            //如果验证通过,则将用户信息传到前台
        	httpSession.setAttribute("admininfo", existManager);
        	
        	//通过管理员姓名查找管理员信息
	       	Manager manager = managerService.findManagerByusername(username);
	       	//设置最后一次登录时间为当前时间
	       	manager.setLastloginTime(new Date());
	       	//设置登录次数为之前登录总次数加1
	       	int loginTimes = manager.getLoginTimes();
	       	manager.setLoginTimes(loginTimes+1);
	       	managerService.updateTime(manager);

        	mm.addAttribute("manager", existManager);
    		return "redirect:index";
        }
        else
        {
        	mm.addAttribute("errMsg", "用户名或密码错误");
    		return "adminlogin";
        }
    }
    
    /**
     * 管理员注销 
     */
    @RequestMapping("/logout")
    public String logout(HttpSession httpSession)
    {
    	httpSession.removeAttribute("manager");
    	return "redirect:login";
    }
    
    /**
     * 检验管理员用户名是否已存在
     * @param username
     * @return
     */
    @RequestMapping(value="/checkUsername",method=RequestMethod.GET)
    @ResponseBody()
    public Object checkUsername(@RequestParam("username")String username)
    {
    	Map<String, Object> map = new HashMap<String, Object>();
    	Manager manager = managerService.checkUsername(username);
    	if(manager!=null)
    	{
    		map.put("status", "false");
    		map.put("msg","用户名已存在");
    		return map;
    	}
    	else
    	{
    		map.put("status", "true");
    		map.put("msg","用户名可以使用");
    		return map;
    	} 	
    }
    
    /**
     * 管理员列表
     * @param page
     * @param roleId
     * @param username
     * @param createTime1
     * @param createTime2
     * @param isDelete
     * @param mm
     * @return
     */
    @RequestMapping(value="/list")
    public String getList(@RequestParam("page")int page,
    					  @RequestParam(value="roleId",required=false)String roleId,
    					  @RequestParam(value="username",required=false)String username,
    					  @RequestParam(value="createTime1",required=false)String createTime1,
    					  @RequestParam(value="createTime2",required=false)String createTime2,
    					  @RequestParam(value="isUsed",required=false)Integer isUsed,ModelMap mm){
    	PagerUtils<ManagerVo> pu = managerService.getList(roleId, username, CommonUtils.string2Date(createTime1,"yyyy-MM-dd") , CommonUtils.string2Date(createTime2,"yyyy-MM-dd"), isUsed, page);
    	
    	List<Role> roleList = roleService.getList();
    	
    	//查询条件
    	Map<String,Object> paramsMap = new HashMap<String, Object>();
    	paramsMap.put("username", username);
    	paramsMap.put("roleId", roleId);
    	paramsMap.put("createTime1", createTime1);
    	paramsMap.put("createTime2", createTime2);
    	paramsMap.put("isUsed", isUsed);
    	
    	mm.addAllAttributes(paramsMap); //查询条件
    	mm.addAttribute("roleList",roleList);
    	mm.addAttribute("pageinfo",pu); //分页数据
    	return "admin/manager/manager_list";
    }
    
    /**
     * 从管理员列表页面跳转到添加管理员页面
     * @param mm
     * @return
     */
    @RequestMapping("/add")
    public String add(Model mm){
    	List<Role> roleList = roleService.getList();
    	mm.addAttribute("roleList",roleList);
    	
    	List<Street> streetList = streetService.getStreetList();
    	mm.addAttribute("streetList",streetList);
    	return "admin/manager/manager_add";
    }
    
    /**
     * 添加管理员，并且要分析出错情况(比如手机号格式错误等等)
     * @param manager
     * @param bindingresult
     * @param roleId
     * @param password
     * @param mm
     * @param httpSession
     * @return
     */
    @RequestMapping(value="/add.do",method= RequestMethod.POST)
    public String add(@Valid Manager manager,
    				  BindingResult bindingresult,
    				  String roleId,
    				  String password,
    				  String street_id,
    				  Model mm,
    				  HttpSession httpSession)
    {
    	if (bindingresult.hasErrors()){
    		mm.addAttribute("fail","添加失败,请注意格式");
    		return "admin/manager/manager_add";  
    	}else {
    		//添加创建用户的时间
    		manager.setCreateTime(new Date());
    		//添加角色的id
    		manager.setId(UUIDUtils.getUUID());
    		manager.setRoleId(roleId);
    		
    		Role role = roleService.findRoleById(roleId);
    		String roleName = role.getRoleName();
    		if(roleName.equals("超级管理员")){
    			manager.setStreet_id(null);
    		}else {
    			manager.setStreet_id(street_id);
			}
    		manager.setPassword(MD5Utils.MD5(password));
    		
    		managerService.add(manager);
    		
    		Manager currentManager = (Manager) httpSession.getAttribute("admininfo");
    		AddOperatorLog(currentManager.getUsername(),"权限模块","添加管理员");
    		
    		return "redirect:/admin/list?page=1";
		}
    }
    
    /**
     * 从管理员列表页面跳转到修改管理员页面
     * @param mm
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model mm,String id){
    	Manager manager = managerService.findManagerById(id);
    	mm.addAttribute("manager",manager);
    	
    	List<Role> roleList = roleService.getList();
    	mm.addAttribute("roleList",roleList);
    	System.out.println(roleList+"98888888888888888888888888888888888");
    	
    	String roleId = manager.getRoleId();
    	Role role1 = roleService.findRoleById(roleId);
    	mm.addAttribute("role1",role1);
    	
    	return "admin/manager/manager_edit";
    }
    
    /**
     * 修改管理员信息，并且对修改的格式进行判断
     * @param manager
     * @param bindingresult
     * @param mm
     * @return
     */
    @RequestMapping(value="/edit.do",method= RequestMethod.POST)
    public String edit(@Valid Manager manager,
    					BindingResult bindingresult,
    					String password,
    					Model mm,
    					HttpSession httpSession)
    {
    	if (bindingresult.hasErrors()){
    		mm.addAttribute("fail","修改失败,请注意格式");
    		return "admin/manager/manager_edit";  
    	}else {
    		//设置修改用户的时间
    		manager.setModifyTime(new Date());
    		
    		managerService.editManager(manager);
    		
    		/**
     		 * 以下是日志功能，用来记录谁操作了哪个模块
     		 */
    		Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    		AddOperatorLog(currentManager.getUsername(),"权限模块","修改管理员信息");
    		
    		return "redirect:/admin/list?page=1";
		}
    }
    
    /**
     * 直接从管理员列表页面删除单个管理员
     * @param manager
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Manager manager,HttpSession httpSession){
    	managerService.deleteManager(manager);
    	
    	Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
		AddOperatorLog(currentManager.getUsername(),"权限模块","删除单个管理员");
		
    	return "redirect:/admin/list?page=1";
    }
    
    /**
     * 批量删除管理员
     * @param request
     * @param response
     */
    @RequestMapping("/deleteList")
    @ResponseBody
    public void deleteManagers(HttpServletRequest request,
    						   HttpServletResponse response,
    						   HttpSession httpSession){
    	 String items = request.getParameter("managerList");
         List<String> managerList = new ArrayList<String>();
         String[] strs = items.split(",");
         for (String str : strs) {
        	 managerList.add(str);
         }
         managerService.deleteManagers(managerList);
         
         Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
 		 AddOperatorLog(currentManager.getUsername(),"权限模块","批量删除管理员");
    }
    
}