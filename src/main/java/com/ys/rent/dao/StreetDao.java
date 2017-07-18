package com.ys.rent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ys.rent.po.Street;

/**
 * 街道的Dao接口，它的实现类在mapper中，是一个xml文件
 * @Repository表明这是持久层
 * @author 云尚公寓
 *
 */
@Repository
public interface StreetDao {
    /**
     * 得到街道列表
     * @return
     */
    public List<Street> getStreetList();
    
    /**
     * 根据街道id得到街道信息
     * @param street_id
     * @return
     */
    public Street getStreetByStreetId(@Param("street_id") String street_id);
    
}
