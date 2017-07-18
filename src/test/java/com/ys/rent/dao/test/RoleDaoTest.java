package com.ys.rent.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ys.rent.dao.RoleDao;
import com.ys.rent.po.Role;
import com.ys.rent.utils.UUIDUtils;

/**
 * 角色的dao测试类
 * @author 云尚公寓
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RoleDaoTest {
	
	@Autowired
	private RoleDao roleDao;
	
	@Test
	public void testGetList()
	{
		List<Role> list = roleDao.getList();
		if(list!=null && list.size()>0)
		{
			for (Role role : list) {
				System.out.println(role);
			}
		}		
	}
	
	@Test
	public void testAdd()
	{
		Role role = new Role();
		role.setId(UUIDUtils.getUUID());
		role.setRoleName("管理员11");
		role.setCreateTime(new Date());
		role.setCreateUser("admin");
		role.setModifyTime(new Date());
		role.setModifyUser("admin");
		
	    roleDao.addRole(role);
	}
	
}
