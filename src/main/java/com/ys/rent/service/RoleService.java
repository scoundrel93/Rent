package com.ys.rent.service;

import java.util.List;

import com.ys.rent.po.Role;
import com.ys.rent.utils.PagerUtils;

/**
 * 角色的service接口
 * @author 云尚公寓
 *
 */
public interface RoleService {
	
	/**
	 * 角色列表
	 * @param roleName
	 * @param pageIndex
	 * @return
	 */
	public PagerUtils<Role> getRoleList(String roleName,int pageIndex);
	
	/**
	 * 添加角色
	 * @param role
	 */
	public void addRole(Role role);
	
	/**
	 * 根据id查询角色
	 * @param id
	 * @return
	 */
	public Role findRoleById(String id);
	
	/**
	 * 修改角色
	 * @param role
	 */
	public void editRole(Role role);
	
	/**
	 * 删除角色
	 * @param role
	 */
	public void deleteRole(Role role);
	
	/**
	 * 批量删除角色
	 * @param roleList
	 */
	@SuppressWarnings("rawtypes")
	public void deleteRoles(List roleList);
	
	/**
	 * 得到角色信息，用于管理员列表的角色查询
	 * @return
	 */
	public List<Role> getList();
	
	/**
	 * 根据id的到角色名，用于权限控制
	 * @param id
	 * @return
	 */
	public Role getroleName(String id);
	
	
}
