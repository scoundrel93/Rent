package com.ys.rent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ys.rent.po.Room;
import com.ys.rent.vo.RoomVo;

/**
 * 房间的Dao接口，它的实现类在mapper中，是一个xml文件
 * @Repository表明这是持久层
 * @author 云尚公寓
 *
 */
@Repository
public interface RoomDao {
	/**
	 * 根据id查询房间，用于房间模块所有页面数据的回显，以及修改功能
	 * @param room_id
	 * @return
	 */
    public RoomVo findRoomById(@Param("room_id") String room_id);
	
	/**
	 * 根据街道和栋号得到房间列表，并且根据房间号查询房间
	 * @param room_name
	 * @param street_name
	 * @param roomType_name
	 * @param pageSize
	 * @param startIndex
	 * @return
	 */
    public List<Room> getRoomList(@Param("room_name")String room_name,
    		                      @Param("street_name")String street_name,
	                              @Param("roomType_name")String roomType_name, 
	                              @Param("pageSize")int pageSize,
	                              @Param("startIndex")int startIndex);
    
	/**
	 * 得到房间列表对应的房间数量
	 * @param room_name
	 * @param street_name
	 * @param roomType_name
	 * @return
	 */
    public int getRoomCount(@Param("room_name")String room_name,
    		                @Param("street_name") String street_name,
    		                @Param("roomType_name") String roomType_name);
	
    /**
     * 根据街道得到房间状态列表，并且根据房间号查询房间
     * @param room_name
     * @param street_name
     * @param isRent
     * @param pageSize
     * @param startIndex
     * @return
     */
    public List<Room> getRoomStatusList(@Param("room_name")String room_name,
    		                      @Param("street_name")String street_name,
	                              @Param("isRent")String isRent, 
	                              @Param("pageSize")int pageSize,
	                              @Param("startIndex")int startIndex);
    
	/**
	 * 得到房间状态列表对应的数量
	 * @param room_name
	 * @param street_name
	 * @param isRent
	 * @return
	 */
    public int getRoomStatusCount(@Param("room_name")String room_name,
    		                      @Param("street_name") String street_name,
                                  @Param("isRent") String isRent);
    
    public List<Room> getReturnRoomList(@Param("room_name")String room_name,
            @Param("street_name")String street_name,
            @Param("isRent")String isRent, 
            @Param("pageSize")int pageSize,
            @Param("startIndex")int startIndex);

	/**
	* 得到房间状态列表对应的数量
	* @param room_name
	* @param street_name
	* @param isRent
	* @return
	*/
	public int getReturnRoomCount(@Param("room_name")String room_name,
	            @Param("street_name") String street_name,
	            @Param("isRent") String isRent);
    
    /**
     * 根据id修改房间信息
     * @param room
     */
    public void editRoomById(RoomVo roomVo);
    
    /**
     * 根据id修改房间状态
     * @param isRent
     * @param room_id
     */
	public void updateRoomStatus(@Param("isRent")int isRent,@Param("room_id")String room_id);
	
	/**
	 * 添加房间
	 * @param room
	 */
    public void addRoom(Room room);
    
    /**
     * 得到闲置房间数量,所有
     * @return
     */
    public int getFreeRoomCount();
    
    /**
     * 得到已租房间数量
     * @return
     */
    public int getRentRoomCount();
    
    /**
     * 得到闲置房间数量，根据街道
     * @return
     */
    public int getFreeRoomCountByStreetName(@Param("street_name")String street_name);
    
}
