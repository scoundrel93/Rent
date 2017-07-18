package com.ys.rent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ys.rent.po.RoomType;

/**
 * 房间类型的Dao接口，它的实现类在mapper中，是一个xml文件
 * @Repository表明这是持久层
 * @author 云尚公寓
 *
 */
@Repository
public interface RoomTypeDao {
	 /**
	  * 根据街道id得到房间类型列表，如根据广达路得到AB栋
	  * @param street_id
	  * @return
	  */
	 public List<RoomType> getRoomTypeListByStreetId(@Param("street_id") String street_id);
	 
	 /**
	  * 根据id得到房间类型信息
	  * @param roomType_id
	  * @return
	  */
	 public RoomType getRoomTypeByRoomTypeId(@Param("roomType_id") String roomType_id);
	 
	 /**
	  * 添加房间类型
	  * @param roomType
	  */
	 public void addRoomType(RoomType roomType);
}
