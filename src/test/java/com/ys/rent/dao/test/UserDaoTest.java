package com.ys.rent.dao.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ys.rent.dao.UserDao;
import com.ys.rent.po.User;
import com.ys.rent.utils.UUIDUtils;

/**
 * 用户的dao测试类
 * @author 云尚公寓
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void addUser()
	{
		User user = new User();
		user.setId(UUIDUtils.getUUID());
		user.setUserType(1);
		user.setName("test15");
		user.setPhone("1771239898");
		user.setAge(23);
		user.setSex(1);
		user.setRemark("无不良记录");
		user.setCreateTime(new Date());
		user.setModifyTime(new Date());
	    
		userDao.addUser(user);
	}
	
	@Test
	public void editUser()
	{
		User user = new User();
		user.setId("0d34e64e0ebf45c19f19ee7992b21fb5");
		user.setName("王浩");
		user.setPhone("130");
		user.setAge(15);
		user.setSex(1);
		user.setRemark("你好");
		userDao.editUser(user);
	}
	
}
