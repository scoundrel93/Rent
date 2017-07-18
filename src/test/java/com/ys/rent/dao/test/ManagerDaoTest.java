package com.ys.rent.dao.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ys.rent.dao.ManagerDao;
import com.ys.rent.po.Manager;
import com.ys.rent.utils.MD5Utils;
import com.ys.rent.utils.UUIDUtils;

/**
 * 管理员的dao测试类
 * @author 云尚公寓
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ManagerDaoTest {
	
	@Autowired
	private ManagerDao managerDao;;
	
	@Test
	public void testLogin()
	{
		Manager manager = managerDao.login("admin","admin");
		
		System.out.println(manager);
	}
	
	@Test
	public void testAddManager(){
		Manager m = new Manager();
		m.setId(UUIDUtils.getUUID());
		m.setUsername("admin");
		m.setPassword(MD5Utils.MD5("admin"));
		m.setCreateTime(new Date());
		m.setModifyTime(new Date());
		
	    managerDao.addManager(m);
	}
	
	@Test
	public void AddManagers()
	{
		for (int i = 0; i < 15; i++) {
			Manager m = new Manager();
			m.setId(UUIDUtils.getUUID());
			m.setUsername("admin" + i);
			m.setPassword(MD5Utils.MD5("admin" + i));
			m.setCreateTime(new Date());
			m.setModifyTime(new Date());
			
			managerDao.addManager(m);
		}
	}
	
}
