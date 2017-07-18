package com.ys.rent.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ys.rent.po.Manager;
import com.ys.rent.vo.ManagerVo;

/**
 * 管理员的Dao接口，它的实现类在mapper中，是一个xml文件
 * @Repository表明这是持久层
 * @author 云尚公寓
 *
 */
@Repository
public interface ManagerDao {
	
	/**
	 * 管理员登录
	 * @param username
	 * @param password
	 * @return
	 */
	public Manager login(@Param("username") String username,
						 @Param("password") String password);

		
	/**
	 * 更新最后一次登录时间和登录次数
	 * @param manager
	 */
	public void updateTime(Manager manager);
	
	/**
	 * 根据管理员姓名查找管理员
	 * @param username
	 * @return
	 */
	public Manager findManagerByusername(@Param("username") String username);
	
	/**
	 * 查找管理员
	 * @param username  用户名
	 * @param roleId	id
	 * @param createTime 创建时间
	 * @param isDelete 是否删除
	 * @param pageSize 分页大小
	 * @param startIndex 起始页
	 * @return
	 */
	public List<ManagerVo> getListByPage(@Param("username")String username,
										 @Param("roleId")String roleId,
										 @Param("createTime1")Date createTime1,
										 @Param("createTime2")Date createTime2,
										 @Param("isUsed")Integer isUsed,
										 @Param("pageSize")int pageSize,
										 @Param("startIndex")int startIndex);

	/**
	 * 查询记录条数
	 * @param username
	 * @param roleId
	 * @param createTime1
	 * @param createTime2
	 * @param isDelete
	 * @return
	 */
	public int getCount(@Param("username")String username,
			            @Param("roleId")String roleId,
			            @Param("createTime1")Date createTime1,
			            @Param("createTime2")Date createTime2,
			            @Param("isUsed")Integer isUsed);
	
	/**
	 * 检验用户名是否存在
	 * @param username
	 * @return
	 */
	public Manager checkUsername(@Param("username")String username);
	
	/**
	 * 添加管理员
	 * @param manager
	 */
	public void addManager(Manager manager);
	
	/**
	 * 根据id查询管理员信息(用于修改和删除功能)
	 * @param id
	 * @return
	 */
    public Manager findManagerById(@Param("id") String id);
    
    /**
     * 修改管理员
     * @param manager
     */
    public void editManager(Manager manager);
    
    /**
     * 根据管理员id删除管理员
     * @param manager
     */
    public void deleteManager(Manager manager);
    
    /**
     * 根据角色id删除管理员
     * @param manager
     */
    public void deleteManagerByroleId(@Param("roleId") String roleId);
    
    /**
     * 批量删除角色
     * @param managerList
     */
    @SuppressWarnings("rawtypes")
	public void deleteManagers(List managerList);

}
