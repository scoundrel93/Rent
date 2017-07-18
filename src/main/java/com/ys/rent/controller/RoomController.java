package com.ys.rent.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ys.rent.po.Manager;
import com.ys.rent.po.Role;
import com.ys.rent.po.Room;
import com.ys.rent.po.RoomType;
import com.ys.rent.po.Street;
import com.ys.rent.service.OperatorService;
import com.ys.rent.service.RoleService;
import com.ys.rent.service.RoomService;
import com.ys.rent.service.RoomTypeService;
import com.ys.rent.service.StreetService;
import com.ys.rent.service.TenantService;
import com.ys.rent.utils.PagerUtils;
import com.ys.rent.utils.UUIDUtils;
import com.ys.rent.vo.RoomVo;

/**
 * 房间的控制层
 * @author 云尚公寓
 *
 */
@Controller
@RequestMapping("/room")
public class RoomController extends ControllerBase{
	
	//注入房间的service
	@Autowired
	private RoomService roomService;
	
	//注入街道的service
	@Autowired
	private StreetService streetService;
	
	//注入房间类型的service
	@Autowired
	private RoomTypeService roomTypeService;
	
	//注入租户类型的service
	@Autowired
	private TenantService tenantService;
	
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
    	
		return "admin/room/room_choose";
	}
	
	 /**
     * 从主页面跳转到选择街道的页面
     * @param mm
     * @return
     */
	@RequestMapping("/freeroomchoose")
	public String freeroomchoose(Model mm){
		List<Street> streetList = streetService.getStreetList();
    	mm.addAttribute("streetList",streetList);
    	   
		return "admin/room/freeroom_choose";
	}
	
	/**
	 * 根据街道和栋号得到房间列表,并且根据房间名查询房间
	 * @param page
	 * @param room_name
	 * @param street_name
	 * @param roomType_name
	 * @param mm
	 * @return
	 */
    @RequestMapping(value="/list")
    public String getRoomList(@RequestParam("page")int page,
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
        	PagerUtils<Room> pr = roomService.getRoomList(room_name,street_name, roomType_name, page);
        	mm.addAttribute("pageinfo",pr);
        	
        	int freeroomTotal = roomService.getFreeRoomCountByStreetName(street_name);
        	mm.addAttribute("freeroomTotal",freeroomTotal);
        	
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
        	PagerUtils<Room> pr = roomService.getRoomList(room_name,street_name1, roomType_name, page);
        	mm.addAttribute("pageinfo",pr);
        	
        	int freeroomTotal = roomService.getFreeRoomCountByStreetName(street_name1);
        	mm.addAttribute("freeroomTotal",freeroomTotal);
        	
        	//将查询参数回显到页面上
        	Map<String,Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("street_name", street_name1);
        	paramsMap.put("roomType_name", roomType_name);
        	mm.addAllAttributes(paramsMap);
		}
    	
    	return "admin/room/room_list";
    }
    
    /**
	 * 根据街道得到房间状态(闲置房间)列表,并且根据房间名查询房间
	 * @param page
	 * @param room_name
	 * @param street_name
	 * @param roomType_name
	 * @param mm
	 * @return
	 */
    @RequestMapping(value="/statuslist")
    public String getRoomStatusList(@RequestParam("page")int page,
    		                    @RequestParam(value="room_name",required=false)String room_name,
    		                    @RequestParam(value="street_name",required=false)String street_name,
    		                    @RequestParam(value="isRent",required=false)String isRent,
    		                    ModelMap mm){
    	
    	//得到分页数据
    	PagerUtils<Room> pr = roomService.getRoomStatusList(room_name,street_name, isRent, page);
    	mm.addAttribute("pageinfo",pr);
    	
    	//将查询参数回显到页面上
    	Map<String,Object> paramsMap = new HashMap<String, Object>();
    	paramsMap.put("street_name", street_name);
    	paramsMap.put("isRent", isRent);
    	mm.addAllAttributes(paramsMap);
    	
    	return "admin/room/room_statuslist";
    }
    
    /**
	 * 根据街道得到租满三个月的房间，即可以转租的房间，当前并未应用
	 * 保留该方法，后续完善
	 * @param page
	 * @param room_name
	 * @param street_name
	 * @param roomType_name
	 * @param mm
	 * @return
	 */
    @RequestMapping(value="/returnlist")
    public String getReturnRoomList(@RequestParam("page")int page,
    		                    @RequestParam(value="room_name",required=false)String room_name,
    		                    @RequestParam(value="street_name",required=false)String street_name,
    		                    @RequestParam(value="isRent",required=false)String isRent,
    		                    ModelMap mm){
    	
    	//得到分页数据
    	PagerUtils<Room> pr = roomService.getReturnRoomList(room_name,street_name, isRent, page);
    	mm.addAttribute("pageinfo",pr);
    	
    	//将查询参数回显到页面上
    	Map<String,Object> paramsMap = new HashMap<String, Object>();
    	paramsMap.put("street_name", street_name);
    	paramsMap.put("isRent", isRent);
    	mm.addAllAttributes(paramsMap);
    	
    	return "admin/room/room_returnlist";
    }
    
    /**
     * 从房间列表页面跳转到修改房间页面，并回显数据
     * @param mm
     * @param room_id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model mm,String room_id){
    	RoomVo roomVo = roomService.findRoomById(room_id);
    	mm.addAttribute("roomVo",roomVo);
    	return "admin/room/room_edit";
    }
    
    /**
     * 修改房间信息，也要分析出错情况(修改时信息输入错误)
     * @param room
     * @param bindingresult
     * @param street_name
     * @param roomType_name
     * @param mm
     * @param httpSession
     * @return
     */
    @RequestMapping(value="/edit.do",method= RequestMethod.POST)
    public String edit(@Valid RoomVo roomVo,
    		           BindingResult bindingresult,
    		           String street_name,
    		           String roomType_name,
    		           Model mm,
    		           HttpSession httpSession){
    	if (bindingresult.hasErrors()){
    		System.out.println(bindingresult+"8888888888888888888888888888888888888888888888888");
    		mm.addAttribute("fail","修改失败,请注意格式");
    		return "admin/room/room_edit";  
    	}else {
    		//返回列表页时顺便将这两个参数带过来
    		Map<String,Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("street_name", street_name);
        	paramsMap.put("roomType_name", roomType_name);
        	mm.addAllAttributes(paramsMap);
        	
        	roomVo.setModifyTime(new Date());
    		
    		//得到当前登录用户，并设为修改人
    		Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    		roomVo.setModifyUser(currentManager.getUsername());
    		
    		roomService.editRoomById(roomVo);
    		
    		AddOperatorLog(currentManager.getUsername(),"房间模块","修改房间信息");
    		
			return "redirect:list?page=1";
		}
    }
    
    /**
     * 从主页面跳转到添加房间页面
     * @param mm
     * @return
     */
    @RequestMapping("/add")
    public String add(Model mm){
    	//得到所属街道
    	List<Street> streetList = streetService.getStreetList();
    	mm.addAttribute("streetList",streetList);
    	
    	return "admin/room/room_add";
    }
    
    /**
     * 根据街道查找房间类型，前台用ajax异步获取
     * @param reuqest
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="findRoomType")  
    public String findClass(HttpServletRequest reuqest,HttpServletResponse response) throws Exception{  
        response.setContentType("text/json; charset=UTF-8");  
        //获取ID  
        String street_id = reuqest.getParameter("street_id");  
        PrintWriter out = null;  
        try{  
            out = response.getWriter();  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        JSONArray array = new JSONArray();  
        JSONObject member = null;  
        try{  
        	List<RoomType> roomTypeList = roomTypeService.getRoomTypeListByStreetId(street_id);
            for(RoomType roomType:roomTypeList){  
                member = new JSONObject();  
                member.put("roomType_id", roomType.getRoomType_id());
                member.put("roomType_name", roomType.getRoomType_name());
                array.add(member);
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        out.print(array.toString());  
        return null;  
    }  
    
    /**
     * 添加房间
     * @param room
     * @param bindingresult
     * @param mm
     * @param roomType_id
     * @param street_id
     * @param room_id
     * @param httpSession
     * @return
     */
    @RequestMapping(value="/add.do",method= RequestMethod.POST)
    public String add(@Valid Room room,
    		           BindingResult bindingresult,
    		           Model mm,
    		           String roomType_id,
    		           String street_id,
    		           String room_id,
    		           HttpSession httpSession){
    	if (bindingresult.hasErrors()){
    		System.out.println(bindingresult+"8888888888888888888888888888888888888888888888888");
    		mm.addAttribute("fail","添加失败,请注意格式");
    		return "admin/room/room_add";  
    	}else {
    		//设置添加房间时的时间
    		room.setCreateTime(new Date());
    		//生成房间的id
    		room.setRoom_id(UUIDUtils.getUUID());
    		//设置房间对应的栋号id
    		room.setRoomType_id(roomType_id);
    	
    		//得到当前选中的街道，用于添加成功后页面的跳转
    		Street street = streetService.getStreetByStreetId(street_id);
    		String street_name = street.getStreet_name();
    		
    		//得到当前选中的栋号，用于添加成功后页面的跳转
    		RoomType roomType = roomTypeService.getRoomTypeByRoomTypeId(roomType_id);
    		String roomType_name = roomType.getRoomType_name();
    		
    		//得到当前登录用户，并设为创建人
    		Manager currentManager = (Manager)httpSession.getAttribute("admininfo");
    		room.setCreateUser(currentManager.getUsername());
    		
    		roomService.addRoom(room);
    		
    		AddOperatorLog(currentManager.getUsername(),"房间模块","添加房源");
    		
			return "redirect:list?page=1&street_name="+street_name+"&roomType_name="+roomType_name;
		}
    }
    
     
}
