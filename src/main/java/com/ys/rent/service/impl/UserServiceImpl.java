package com.ys.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.rent.dao.OperatorDao;
import com.ys.rent.dao.UserDao;
import com.ys.rent.po.User;
import com.ys.rent.service.UserService;
import com.ys.rent.utils.PagerUtils;

/**
 * 用户的业务层实现类
 * @author 云尚公寓
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OperatorDao operatorDao;

	/**
	 * 用户列表
	 */
	@Override
	public PagerUtils<User> getUserList(String name, int pageIndex) {
		PagerUtils<User> pr = new PagerUtils<User>();
		int dataCount = userDao.getUserCount(name);
		pr.setPageIndex(pageIndex);
		pr.setPageSize(10);
		pr.setDataCount(dataCount);
		pr.setPageCount(dataCount%10 == 0 ? dataCount/10 : dataCount/10 + 1);
		pr.setDataList(userDao.getUserList(name, 10 , 10 * (pageIndex - 1)));		
		return pr;
	}

	/**
	 * 添加用户
	 */
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	/**
	 * 通过id查询用户
	 */
	@Override
	public User findUserById(String id) {
		return userDao.findUserById(id);
	}

	/**
	 * 修改用户
	 */
	@Override
	public void editUser(User user) {
		userDao.editUser(user);
	}

	/**
	 * 删除用户
	 */
	@Override
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	@Override
	public User GetUserByOpenId(String openId) {
		return null;
	}
	

}