package com.ys.rent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ys.rent.po.Role;

/**
 * 角色的Dao接口，它的实现类在mapper中，是一个xml文件
 * @Repository表明这是持久层
 * @author 云尚公寓
 *
 */
@Repository
public interface RoleDao {

	/**
	 * 根据id得到角色名，用于权限控制
	 * @param id
	 * @return
	 */
	public Role getroleName(@Param("id") String id);
	
	/**
	 * 得到角色的List集合，用于管理员列表页的角色查询
	 * @return
	 */
	public List<Role> getList();
	
	/**
	 * 得到角色列表
	 * @param roleName
	 * @param pageSize
	 * @param startIndex
	 * @return
	 */
	public List<Role> getRoleList(@Param("roleName")String roleName,
			@Param("pageSize")int pageSize,@Param("startIndex")int startIndex);
	
	/**
	 * 得到角色列表的分页数量
	 * @param roleName
	 * @return
	 */
	public int getRoleCount(@Param("roleName")String roleName);

	/**
	 * 添加角色
	 * @param role
	 */
    public void addRole(Role role);
    
    /**
     * 根据id查询角色信息(用于修改和删除功能)
     * @param id
     * @return
     */
    public Role findRoleById(@Param("id") String id);
    
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

}
