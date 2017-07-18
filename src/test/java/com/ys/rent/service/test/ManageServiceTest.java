package com.ys.rent.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ys.rent.service.ManagerService;
import com.ys.rent.utils.PagerUtils;
import com.ys.rent.vo.ManagerVo;

/**
 * 管理员的service测试类
 * @author 云尚公寓
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ManageServiceTest {
	
	@Autowired
	private ManagerService managerService;
	
	@Test
	public void getList()
	{
		PagerUtils<ManagerVo> pu = managerService.getList(null, null, null, null, 0, 1);
		for (ManagerVo m : pu.getDataList()) {
			System.out.println(m);			
		}
	}

}
