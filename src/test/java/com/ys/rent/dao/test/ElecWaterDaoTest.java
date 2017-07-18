package com.ys.rent.dao.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ys.rent.dao.ElecWaterDao;
import com.ys.rent.po.ElecWater;

/**
 * 水电的dao测试类
 * @author 云尚公寓
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ElecWaterDaoTest {
	
	@Autowired
	private ElecWaterDao elecWaterDao;
	
	@Test
	public void testfindEWById()
	{
		String room_id = "1";
		List<ElecWater> list = elecWaterDao.findEWByRoomId(room_id);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(0).getWater()+"88888888888888888888888888");
		}
		BigDecimal w_money = new BigDecimal(30*8);
		System.out.println(w_money+"/"+"4555555555555555555555555555555");
	}
	
}
