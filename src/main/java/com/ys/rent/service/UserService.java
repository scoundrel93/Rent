package com.ys.rent.service;

import com.ys.rent.po.User;
import com.ys.rent.utils.PagerUtils;

/**
 * 用户的service接口
 * @author 云尚公寓
 *
 */
public interface UserService {
	
	/**
	 * 用户列表
	 * @param name
	 * @param pageIndex
	 * @return
	 */
    public PagerUtils<User> getUserList(String name,int pageIndex);
	
    /**
     * 添加用户
     * @param user
     */
	public void addUser(User user);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public User findUserById(String id);
	
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
	
	/**
	 * 根据openid查找用户
	 * @param openId
	 * @return
	 */
	public User GetUserByOpenId(String openId);
}
