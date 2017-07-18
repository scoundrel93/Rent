package com.ys.rent.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ys.rent.po.User;
import com.ys.rent.service.UserService;

/**
 * 用户的service测试类
 * @author 云尚公寓
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@Test
	public void editUser(){
		User user = new User();
		user.setId("103a9a59f8e64af5ba050c425836c78a");
		user.setName("哈哈");
		user.setPhone("130");
		user.setAge(15);
		user.setSex(1);
		user.setRemark("你好");
		userService.editUser(user);
	}
}
