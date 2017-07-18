package com.ys.rent.controller;

import java.util.Date;

import javax.annotation.Resource;
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

import com.ys.rent.po.Manager;
import com.ys.rent.po.User;
import com.ys.rent.service.OperatorService;
import com.ys.rent.service.UserService;
import com.ys.rent.utils.PagerUtils;
import com.ys.rent.utils.UUIDUtils;

/**
 * 用户的控制层
 * @author 云尚公寓
 *
 */
@Controller  
@RequestMapping("/user")
public class UserController extends ControllerBase{  
	
	//注入用户的service
    @Resource  
    private UserService userService;  
    
    //注入日志类的service
    @Autowired
	private OperatorService operatorService;
    
    /**
     * 用户列表
     * @param page
     * @param name
     * @param mm
     * @return
     */
    @RequestMapping("/list")
    public String getUserList(@RequestParam("page")int page,
    						  @RequestParam(value="name",required=false)String name,
    		ModelMap mm){
    	PagerUtils<User> pr = userService.getUserList(name, page);
    	mm.addAttribute("pageinfo",pr); //分页数据
    	return "admin/user/user_list";
    }
	
    /**
     * 从主页面跳转到添加用户的页面
     * @return
     */
    @RequestMapping("/add")
    public String add(){
    	return "admin/user/user_add";
    }
    
    /**
     * 添加用户，并且要分析出错情况(比如手机号格式错误等等)
     * @param user
     * @param bindingresult
     * @param mm
     * @param httpSession
     * @return
     */
    @RequestMapping(value="/add.do",method= RequestMethod.POST)
    public String add(@Valid User user,
    				  BindingResult bindingresult,
    				  Model mm,
    				  HttpSession httpSession)
    {
    	if (bindingresult.hasErrors()){
    		mm.addAttribute("fail","添加失败,请注意格式");
    		return "admin/user/user_add";  
    	}else {
    		user.setCreateTime(new Date());
    		user.setId(UUIDUtils.getUUID());
    		userService.addUser(user);
    		
    		Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
        	AddOperatorLog(currentManager.getUsername(),"用户模块","添加用户");
    		return "redirect:/user/list?page=1";
		}
    }
    
    /**
     * 从用户列表页面跳转到修改用户页面
     * @param mm
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model mm,String id){
    	User user = userService.findUserById(id);
    	mm.addAttribute("user",user);
    	return "admin/user/user_edit";
    }
    
    /**
     * 修改用户，并且要分析出错情况(比如手机号格式错误等等)
     * @param user
     * @param bindingresult
     * @param mm
     * @param httpSession
     * @return
     */
    @RequestMapping(value="/edit.do",method= RequestMethod.POST)
    public String edit(@Valid User user,
    				    BindingResult bindingresult,
    				    Model mm,
    				    HttpSession httpSession)
    {
    	if (bindingresult.hasErrors()){
    		System.out.println(bindingresult+"8888888888888888888888888888888888888888888888888");
    		mm.addAttribute("fail","修改失败,请注意格式");
    		return "admin/user/user_edit";  
    	}else {
    		user.setModifyTime(new Date());
    		userService.editUser(user);
    		
    		Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
        	AddOperatorLog(currentManager.getUsername(),"用户模块","修改用户");
        	
    		return "redirect:/user/list?page=1";
		}
    }
    
    /**
     * 从用户列表页面删除用户
     * @param user
     * @param httpSession
     * @return
     */
    @RequestMapping("/delete")
    public String delete(User user,HttpSession httpSession){
    	userService.deleteUser(user);
    	
    	Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    	AddOperatorLog(currentManager.getUsername(),"用户模块","删除用户");
    	
    	return "redirect:/user/list?page=1";
    }
    
}