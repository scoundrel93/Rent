package com.ys.rent.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ys.rent.po.Manager;
import com.ys.rent.po.Role;
import com.ys.rent.service.ManagerService;
import com.ys.rent.service.OperatorService;
import com.ys.rent.service.RoleService;
import com.ys.rent.utils.PagerUtils;
import com.ys.rent.utils.UUIDUtils;

/**
 * 角色控制层
 * @author 云尚公寓
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController extends ControllerBase{
	
	//注入角色的service
	@Autowired
	private RoleService roleService;
	
	//注入日志的service
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private ManagerService managerService;

	/**
	 * 角色列表
	 * @param page
	 * @param roleName
	 * @param mm
	 * @return
	 */
    @RequestMapping("/list")
    public String getList(@RequestParam("page")int page,@RequestParam(value="roleName",required=false)String roleName,
    		ModelMap mm){
    	PagerUtils<Role> pr = roleService.getRoleList(roleName, page);
    	mm.addAttribute("pageinfo",pr); //分页数据
    	return "admin/role/role_list";
    }
	
    /**
     * 从角色列表页面跳转到添加角色的页面
     * @return
     */
    @RequestMapping("/add")
    public String add(){
    	return "admin/role/role_add";
    }
    
    /**
     * 添加角色，并且要分析出错情况(比如手机号格式错误等等)
     * @param role
     * @param bindingresult
     * @param mm
     * @param httpSession
     * @return
     */
    @RequestMapping(value="/add.do",method= RequestMethod.POST)
    public String add(@Valid Role role,BindingResult bindingresult,Model mm,HttpSession httpSession)
    {
    	if (bindingresult.hasErrors()){
    		mm.addAttribute("fail","添加失败,请注意格式");
    		return "admin/role/role_add";  
    	}else {
    		//添加创建角色的时间
    		role.setCreateTime(new Date());
    		//添加角色的id
    		role.setId(UUIDUtils.getUUID());
    		
    		//得到当前登录用户，并设为创建人
    		Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    		role.setCreateUser(currentManager.getUsername());
    		
    		roleService.addRole(role);
    		
    		AddOperatorLog(currentManager.getUsername(),"角色模块","添加角色");
    		
    		return "redirect:/role/list?page=1";
		}
    }
    
    /**
     * 从角色列表页面跳转到修改角色的页面
     * @param mm
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model mm,String id){
    	Role role = roleService.findRoleById(id);
    	mm.addAttribute("role",role);
    	return "admin/role/role_edit";
    }
    
    /**
     * 修改角色，并且要分析出错情况(比如手机号格式错误等等)
     * @param role
     * @param bindingresult
     * @param mm
     * @param httpSession
     * @return
     */
    @RequestMapping(value="/edit.do",method= RequestMethod.POST)
    public String edit(@Valid Role role,BindingResult bindingresult,Model mm,HttpSession httpSession)
    {
    	if (bindingresult.hasErrors()){
    		mm.addAttribute("fail","修改失败,请注意格式");
    		return "admin/role/role_edit";  
    	}else {
    		//设置修改用户的时间
    		role.setModifyTime(new Date());
    		
    		//得到当前登录用户，并设为修改人
    		Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    		role.setModifyUser(currentManager.getUsername());
    		
    		roleService.editRole(role);
    		
    		AddOperatorLog(currentManager.getUsername(),"权限模块","修改角色信息");
    		
    		return "redirect:/role/list?page=1";
		}
    }
    
    /**
     * 删除单个角色
     * @param role
     * @param httpSession
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Role role,HttpSession httpSession){
    	/**
  		 * 若删除角色，则同时会删除该角色下的所有管理员
  		 */
  		String RoleId = role.getId();
  		managerService.deleteManagerByroleId(RoleId);
    	
    	roleService.deleteRole(role);
    	
    	Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
		AddOperatorLog(currentManager.getUsername(),"权限模块","删除单个角色");
  		
    	return "redirect:/role/list?page=1";
    }
    
    /**
     * 批量删除角色
     * @param request
     * @param response
     * @param httpSession
     */
    @RequestMapping("/deleteList")
    @ResponseBody
    public void deleteRoles(HttpServletRequest request,
    						HttpServletResponse response,
    						HttpSession httpSession){
    	 String items = request.getParameter("roleList");
         List<String> roleList = new ArrayList<String>();
         String[] strs = items.split(",");
         for (String str : strs) {
        	 roleList.add(str);
         }
         roleService.deleteRoles(roleList);
         
         Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
 		 AddOperatorLog(currentManager.getUsername(),"权限模块","批量删除角色");
         
    }
    
	
}
