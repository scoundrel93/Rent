package com.ys.rent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ys.rent.po.User;

/**
 * 用户的Dao接口，它的实现类在mapper中，是一个xml文件
 * @Repository表明这是持久层
 * @author 云尚公寓
 *
 */
@Repository
public interface UserDao {

	/**
	 * 获取用户列表
	 * @param name
	 * @param pageSize
	 * @param startIndex
	 * @return
	 */
	public List<User> getUserList(@Param("name")String name,
								  @Param("pageSize")int pageSize,
								  @Param("startIndex")int startIndex);
	
	/**
	 * 得到用户列表对应的分页数量
	 * @param name
	 * @return
	 */
	public int getUserCount(@Param("name")String name);

	/**
	 * 添加用户
	 * @param user
	 */
    public void addUser(User user);
    
    /**
     * 根据id查询用户信息(用于修改和删除功能)
     * @param id
     * @return
     */
    public User findUserById(@Param("id") String id);
    
    /**
     * 修改用户
     * @param user
     */
    public void editUser(User user);
    
    /**
     * 删除用户
     * @param user
     */
    public void deleteUser(User user);
	
}
