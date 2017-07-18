package com.ys.rent.service;

import com.taobao.api.ApiException;
import com.ys.rent.po.Tenant;
import com.ys.rent.utils.PagerUtils;
import com.ys.rent.vo.TenantVo;

/**
 * 租户的service接口
 * @author 云尚公寓
 *
 */
public interface TenantService {
	
	/**
	 * 根据id查找租户，用于修改租户信息
	 * @param id
	 * @return
	 */
	public TenantVo selectTenantById(String id);
	
	/**
	 * 根据id查询租户，用于禁用租户
	 * @param id
	 * @return
	 */
	public Tenant findTenantByIdForForbid(String id);
	
	/**
	 * 根据id查询租户，用于催租模块
	 * @param id
	 * @return
	 */
	public TenantVo findTenantById(String id);
	
	/**
	 * 根据街道和栋号得到租户列表,并且根据租户姓名查询租户
	 * @param name
	 * @param street_name
	 * @param roomType_name
	 * @param pageIndex
	 * @return
	 */
	public PagerUtils<Tenant> getTenantList(String room_name,String street_name,String roomType_name,int pageIndex);
		
	/**
	 * 根据街道和栋号得到当月到期租户列表,并且根据租户姓名查询租户
	 * @param name
	 * @param street_name
	 * @param roomType_name
	 * @param pageIndex
	 * @return
	 */
	public PagerUtils<Tenant> getExpireTenantList(String room_name,String street_name,int pageIndex);
	
	public int getExpireCountBystreet(String street_name);
	
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
	 * 得到禁用租户列表,并且根据租户姓名查询已禁用租户
	 * @param name
	 * @param pageIndex
	 * @return
	 */
	public PagerUtils<Tenant> getForbidTenantList(String name,String street_name,int pageIndex);
	
	/**
	 * 根据id删除租户
	 * @param tenant
	 */
	public void deleteForbidTenantById(Tenant tenant);
	
	/**
	 * 根据年月查询合同期到期租户
	 * @param year
	 * @param month
	 * @param pageIndex
	 * @return
	 */
	public PagerUtils<Tenant> selectContract(String year,String month,String street_name,int pageIndex);
	
	/**
	 * 添加租户信息(用于签约房间)
	 * @param tenantVo
	 * @throws Exception
	 */
	public void addTenantBySign(TenantVo tenantVo) throws Exception;
	
	/**
	 * 根据街道得到待交房租租户列表,并且根据租户姓名查询租户
	 * @param name
	 * @param street_name
	 * @param pageIndex
	 * @return
	 */
	public PagerUtils<Tenant> getToPayTenantList(String name,String street_name,int pageIndex);
	
	/**
	 * 根据街道得到待抄水电租户列表,并且根据房间号查询信息
	 * @param room_name
	 * @param street_name
	 * @param pageIndex
	 * @return
	 */
	public PagerUtils<Tenant> getToCopyEWTenantList(String room_name,String street_name,int pageIndex);
	
	/**
	 * 催租功能
	 * @param room_id
	 * @throws ApiException
	 */
	public void updatecallEnd(Tenant tenant)throws ApiException;
	
	/**
	 * 更新交租用的时间，目前用于方便管理员操作
	 * @param tenant
	 */
	public void updatetimeEnd(Tenant tenant);
	
	
}
