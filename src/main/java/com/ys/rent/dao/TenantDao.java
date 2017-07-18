package com.ys.rent.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ys.rent.po.Tenant;
import com.ys.rent.vo.TenantVo;

/**
 * 租户的Dao接口，它的实现类在mapper中，是一个xml文件
 * @Repository表明这是持久层
 * @author 云尚公寓
 *
 */
@Repository
public interface TenantDao {
	
	/**
	 * 根据房间id查询月基本租金
	 * @param room_id
	 * @return
	 */
	public Tenant findmrentByRoomId(@Param("room_id")String room_id);

	/**
	 * 根据id查询租户，用于修改租户信息
	 * @param id
	 * @return
	 */
	public TenantVo selectTenantById(@Param("id") String id);
	
	/**
	 * 根据id查询租户，用于禁用租户
	 * @param id
	 * @return
	 */
	public Tenant findTenantByIdForForbid(@Param("id") String id);
	
	/**
	 * 根据id查询租户，用于催租
	 * @param id
	 * @return
	 */
  	public TenantVo findTenantById(@Param("id") String id);
	
	/**
	 * 根据街道和栋号得到租户列表，并且根据租户名查询租户
	 * @param name
	 * @param street_name
	 * @param roomType_name
	 * @param pageSize
	 * @param startIndex
	 * @return
	 */
    public List<Tenant> getTenantList(
    		@Param("room_name")String room_name,@Param("street_name")String street_name,
    									      @Param("roomType_name")String roomType_name,
									          @Param("pageSize")int pageSize,
									          @Param("startIndex")int startIndex);
	/**
	 * 得到租户列表对应的数量
	 * @param name
	 * @param street_name
	 * @param roomType_name
	 * @return
	 */
    public int getTenantCount(@Param("room_name")String room_name,
    						  @Param("street_name") String street_name,
    						  @Param("roomType_name") String roomType_name);
    
    /**
	 * 根据街道和栋号得到本月到期租户列表，并且根据租户名查询租户
	 * @param name
	 * @param street_name
	 * @param roomType_name
	 * @param pageSize
	 * @param startIndex
	 * @return
	 */
    public List<Tenant> getExpireTenantList(  @Param("room_name")String room_name,
    										  @Param("street_name")String street_name,
									          @Param("pageSize")int pageSize,
									          @Param("startIndex")int startIndex);
	/**
	 * 得到本月到期租户列表对应的数量
	 * @param name
	 * @param street_name
	 * @param roomType_name
	 * @return
	 */
    public int getExpireTenantCount(@Param("street_name")String street_name,@Param("room_name")String room_name);
    
    public int getExpireCountBystreet(@Param("street_name")String street_name);
    
    public int getExpireCount();
		
  	/**
  	 * 根据id修改租户
  	 * @param tenant
  	 */
  	public void editTenantById(Tenant tenant);
  	
  	/**
  	 * 根据id禁用租户
  	 * @param tenant
  	 */
  	public void updateTenantById(Tenant tenant);
  	
    /**
     * 禁用租户列表，并且根据租户姓名查询禁用租户
     * @param name
     * @param pageSize
     * @param startIndex
     * @return
     */
    public List<Tenant> getForbidTenantList(@Param("name") String name,
    										@Param("street_name") String street_name,
    		                                @Param("pageSize")int pageSize,
									        @Param("startIndex")int startIndex);
	/**
	 * 得到禁用租户列表数量
	 * @param name
	 * @return
	 */
    public int getForbidTenantCount(@Param("name") String name,@Param("street_name") String street_name);
  	
  	/**
  	 * 根据id删除禁用租户
  	 * @param tenant
  	 */
  	public void deleteForbidTenantById(Tenant tenant);
  	
	/**
	 * 根据年月获取租户列表，用于合同期查询
	 * @param year
	 * @param month
	 * @param pageSize
	 * @param startIndex
	 * @return
	 */
	public List<Tenant> selectContract(@Param("year")String year,
			                           @Param("month")String month,
			                           @Param("street_name")String street_name,
			                           @Param("pageSize")int pageSize,
			                           @Param("startIndex")int startIndex);
	
	/**
	 * 得到分页数量，用于合同期查询
	 * @param year
	 * @param month
	 * @return
	 */
	public int getContractCount(@Param("year")String year,
								@Param("month")String month,
								@Param("street_name")String street_name);
	
	/**
	 * 添加租户信息(用于签约房间)
	 * @param tenantVo
	 * @throws Exception
	 */
	public void addTenantBySign(TenantVo tenantVo) throws Exception;
	
	/**
	 * 添加一些终止时间，在签约房间时添加
	 * @param room_id
	 */
	public void updateSomeTimeByRoomId(TenantVo tenantVo);
	
	/**
	 * 根据街道得到待交租户列表，并且根据租户名查询租户
	 * @param name
	 * @param street_name
	 * @param pageSize
	 * @param startIndex
	 * @return
	 */
    public List<Tenant> getToPayTenantList(@Param("name")String name,
    		                               @Param("street_name")String street_name,
									       @Param("pageSize")int pageSize,
									       @Param("startIndex")int startIndex);
	/**
	 * 得到待交租户列表租户数量
	 * @param name
	 * @param street_name
	 * @return
	 */
    public int getToPayTenantCount(@Param("name")String name,
    		                       @Param("street_name") String street_name);
    
    /**
     * 根据街道得到待交租户列表，并且根据房间号查询信息
     * @param room_name
     * @param street_name
     * @param pageSize
     * @param startIndex
     * @return
     */
    public List<Tenant> getToCopyEWTenantList(@Param("room_name")String room_name,
    		                                  @Param("street_name")String street_name,
									          @Param("pageSize")int pageSize,
									          @Param("startIndex")int startIndex);
    
	/**
	 * 得到待交租户列表租户数量
	 * @param room_name
	 * @param street_name
	 * @return
	 */
    public int getToCopyEWTenantCount(@Param("room_name")String room_name,
    								  @Param("street_name") String street_name);
    
    /**
     * 更新起止时间，用户交租以后自动变为下个月（在微信公众号的逻辑里）
     * @param room_id
     */
    public void updatetimeEnd(Tenant tenant);
    
    /**
     * 更新起止时间，管理员添加完水电以后自动变为下个月
     * @param room_id
     */
    public void updatewaterEnd(@Param("room_id")String room_id);
    
    /**
     * 更新起止时间，管理员催租以后自动变为下个月
     * @param room_id
     */
    public void updatecallEnd(Tenant tenant);
    
    /**
     * 用于测试
     * @param id
     * @param time_end
     * @param water_time_end
     * @param call_end
     */
    public void updateAll(@Param("id") String id,
    					  @Param("time_end") Date time_end,
    					  @Param("water_time_end") Date water_time_end,
    					  @Param("call_end") Date call_end);
    
    /**
     * 用于测试
     * @return
     */
    public List<TenantVo> selectAll();
    
    
}

