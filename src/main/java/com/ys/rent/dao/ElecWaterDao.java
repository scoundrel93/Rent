package com.ys.rent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ys.rent.po.ElecWater;

/**
 * 水电的Dao接口，它的实现类在mapper中，是一个xml文件
 * @Repository表明这是持久层
 * @author 云尚公寓
 *
 */
@Repository
public interface ElecWaterDao {
	
	/**
	 * 管理员添加本月的水电记录
	 * @param elecWater
	 */
	public void addElecWater(ElecWater elecWater);
	
	/**
	 * 根据房间号查询水电表的相关信息(两条数据)，用于管理员添加水电以后进行水费和电费的计算
	 * @param room_id
	 * @return
	 */
    public List<ElecWater> findEWByRoomId(@Param("room_id") String room_id);
    
    /**
     * 管理员添加水电以后，将计算出来的水费电费更新到数据库
     * @param elecWater
     */
    public void updateEWmoney(ElecWater elecWater);
    
    /**
	 * 管理员签约房间时添加一个水电底
	 * @param elecWater
	 */
	public void addElecWaterBySign(ElecWater elecWater);
	
	/**
	 * 禁用租户以后删除该租户对应的水电
	 * @param room_id
	 */
	public void deleteEW(@Param("room_id") String room_id);
    
}
