package com.ys.rent.dao.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ys.rent.dao.StreetDao;

/**
 * 街道的dao测试类
 * @author 云尚公寓
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class StreetDaoTest {
	
	@Autowired
	private StreetDao streetDao;
	
}
