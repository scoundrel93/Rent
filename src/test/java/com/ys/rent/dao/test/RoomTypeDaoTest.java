package com.ys.rent.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ys.rent.dao.RoomTypeDao;
import com.ys.rent.po.RoomType;
import com.ys.rent.utils.UUIDUtils;

/**
 * 房间类型的dao测试类
 * @author 云尚公寓
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class RoomTypeDaoTest {
	@Autowired
	private RoomTypeDao roomTypeDao;
	
	@Test
	public void addRoomType(){
		RoomType roomType = new RoomType();
		roomType.setRoomType_id(UUIDUtils.getUUID());
		roomType.setRoomType_name("A栋");
		roomType.setStreet_id(UUIDUtils.getUUID());
		
		roomTypeDao.addRoomType(roomType);
	}
	
}
