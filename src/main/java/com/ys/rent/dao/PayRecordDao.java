package com.ys.rent.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ys.rent.po.PayRecord;

/**
 * 交租记录的Dao接口，它的实现类在mapper中，是一个xml文件
 * @Repository表明这是持久层
 * @author 云尚公寓
 *
 */
@Repository
public interface PayRecordDao {

	/**
	 * 添加交租记录信息
	 * @param payRecord
	 */
	public void addPayRecord(PayRecord payRecord);
	
	/**
	 * 得到今日待交总租金
	 * @return
	 */
    public int getMMoney();
    
    public void updateisPay(@Param("tenant_id")String tenant_id);
	
}
