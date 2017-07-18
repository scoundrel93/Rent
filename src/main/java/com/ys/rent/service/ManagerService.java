package com.ys.rent.service;

import java.util.Date;
import java.util.List;

import com.ys.rent.po.Manager;
import com.ys.rent.utils.PagerUtils;
import com.ys.rent.vo.ManagerVo;

/**
 * 管理员的service接口
 * @author 云尚公寓
 *
 */
public interface ManagerService {
	
	/**
	 * 管理员登录
	 * @param username
	 * @param password
	 * @return
	 */
	public Manager login(String username,String password);
	
	/**
	 * 校验用户名
	 * @param username
	 * @return
	 */
	public Manager checkUsername(String username);
	
	/**
	 * 管理员列表的分页
	 * @param roleId
	 * @param username
	 * @param createTime1
	 * @param createTime2
	 * @param isDelete
	 * @param pageIndex
	 * @return
	 */
	public PagerUtils<ManagerVo> getList(String roleId,String username,Date createTime1,Date createTime2,Integer isUsed,int pageIndex);

	/**
	 * 添加管理员
	 * @param manager
	 */
	public void add(Manager manager);
	
	/**
	 * 根据id查询管理员
	 * @param id
	 * @return
	 */
	public Manager findManagerById(String id);
	
	/**
	 * 修改管理员
	 * @param manager
	 */
	public void editManager(Manager manager);
	
	/**
	 * 删除单个管理员
	 * @param manager
	 */
	public void deleteManager(Manager manager);
	
	/**
     * 根据角色id删除管理员
     * @param manager
     */
    public void deleteManagerByroleId(String roleId);
	
	/**
	 * 批量删除管理员
	 * @param managerList
	 */
	@SuppressWarnings("rawtypes")
	public void deleteManagers(List managerList);

	public Manager findManagerByusername(String username);

	public void updateTime(Manager manager);
}
