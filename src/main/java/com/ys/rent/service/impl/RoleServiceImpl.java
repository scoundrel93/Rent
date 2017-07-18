package com.ys.rent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.rent.dao.RoleDao;
import com.ys.rent.po.Role;
import com.ys.rent.service.RoleService;
import com.ys.rent.utils.PagerUtils;

/**
 * 角色的业务层实现类
 * @author 云尚公寓
 *
 */
@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao roleDao;

	/**
	 * 角色列表
	 */
	@Override
	public PagerUtils<Role> getRoleList(String roleName, int pageIndex) {
		PagerUtils<Role> pr = new PagerUtils<Role>();
		int dataCount = roleDao.getRoleCount(roleName);
		pr.setPageIndex(pageIndex);
		pr.setPageSize(10);
		pr.setDataCount(dataCount);
		pr.setPageCount(dataCount%10 == 0 ? dataCount/10 : dataCount/10 + 1);
		pr.setDataList(roleDao.getRoleList(roleName, 10 , 10 * (pageIndex - 1)));		
		return pr;
	}

	/**
	 * 得到角色信息，用于管理员列表的角色查询
	 */
	@Override
	public List<Role> getList() {
		return roleDao.getList();
	}

	/**
	 * 添加角色
	 */
	@Override
	public void addRole(Role role) {
		roleDao.addRole(role);
	}

	/**
	 * 根据id查询角色
	 */
	@Override
	public Role findRoleById(String id) {
		return roleDao.findRoleById(id);
	}

	/**
	 * 修改角色
	 */
	@Override
	public void editRole(Role role) {
		roleDao.editRole(role);
	}

	/**
	 * 删除角色
	 */
	@Override
	public void deleteRole(Role role) {
		roleDao.deleteRole(role);
	}

	/**
	 * 批量删除角色
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void deleteRoles(List roleList) {
		roleDao.deleteRoles(roleList);
	}

	@Override
	public Role getroleName(String id) {
		return roleDao.getroleName(id);
	}
	
}
