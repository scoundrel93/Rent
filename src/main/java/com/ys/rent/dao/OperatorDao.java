package com.ys.rent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ys.rent.po.Operator;

/**
 * 日志的Dao接口，它的实现类在mapper中，是一个xml文件
 * @Repository表明这是持久层
 * @author 云尚公寓
 *
 */
@Repository
public interface OperatorDao {

	/**
	 * 获取日志列表
	 * @param operatorUser
	 * @param pageSize
	 * @param startIndex
	 * @return
	 */
	public List<Operator> getOperatorList(@Param("operatorUser")String operatorUser,
			                              @Param("pageSize")int pageSize,
			                              @Param("startIndex")int startIndex);
	
	/**
	 * 得到日志列表的分页数量
	 * @param operatorUser
	 * @return
	 */
	public int getOperatorCount(@Param("operatorUser")String operatorUser);
	
	/**
	 * 添加日志
	 * @param operator
	 */
	public void addOperator(Operator operator);

}
