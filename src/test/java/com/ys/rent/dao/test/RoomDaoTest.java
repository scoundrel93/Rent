package com.ys.rent.dao.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ys.rent.dao.RoomDao;
import com.ys.rent.po.Room;
import com.ys.rent.utils.UUIDUtils;

/**
 * 房间的dao测试类
 * @author 云尚公寓
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class RoomDaoTest {
	@Autowired
	private RoomDao roomDao;
	
	@Test
	public void addRoom(){
		Room room = new Room();
		room.setRoom_id(UUIDUtils.getUUID());
		room.setRoom_name("D201");
		room.setPrice(null);
		room.setRoomDescription("一室两厅");
		room.setCreateUser("张飞");
		room.setCreateTime(new Date());
		room.setModifyUser("张");
		room.setModifyTime(new Date());
		
		roomDao.addRoom(room);
	}
	
	@Test
	public void updateRoomStatus(){
		String room_id = "7c82950edf80432ea1b3612444220c29";
		roomDao.updateRoomStatus(0, room_id);
	}
	
}
