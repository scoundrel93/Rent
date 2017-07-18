package com.ys.rent.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taobao.api.ApiException;
import com.ys.rent.apis.AliDaYuMsg;
import com.ys.rent.dao.ElecWaterDao;
import com.ys.rent.dao.PayRecordDao;
import com.ys.rent.dao.RoomDao;
import com.ys.rent.dao.TenantDao;
import com.ys.rent.dao.UserDao;
import com.ys.rent.po.Tenant;
import com.ys.rent.service.TenantService;
import com.ys.rent.utils.PagerUtils;
import com.ys.rent.vo.TenantVo;

/**
 * 租户的业务层实现类
 * @author 云尚公寓
 *
 */
@Service
public class TenantServiceImpl implements TenantService{

	@Autowired
	private TenantDao tenantDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private ElecWaterDao elecWaterDao;
	
	@Autowired
	private PayRecordDao payRecordDao;
	
	/**
	 * 根据id查询租户,用于催租
	 */
	@Override
	public TenantVo findTenantById(String id) {
		return tenantDao.findTenantById(id);
	}
	
	/**
	 * 根据街道和栋号得到租户列表
	 */
	@Override
	public PagerUtils<Tenant> getTenantList(String room_name,String street_name,String roomType_name,
			int pageIndex) {
		PagerUtils<Tenant> pr = new PagerUtils<Tenant>();
		int dataCount = tenantDao.getTenantCount(room_name,street_name,roomType_name);
		pr.setPageIndex(pageIndex);
		pr.setPageSize(10);
		pr.setDataCount(dataCount);
		pr.setPageCount(dataCount%10 == 0 ? dataCount/10 : dataCount/10 + 1);
		pr.setDataList(tenantDao.getTenantList(room_name,street_name,roomType_name,10 , 10 * (pageIndex - 1)));		
		return pr;
	}

	/**
	 * 根据id修改租户信息
	 */
	@Override
	public void editTenantById(Tenant tenant) {
		tenantDao.editTenantById(tenant);
	}
	
	/**
	 * 根据id禁用租户,租户禁用以后房间状态变为闲置,同时删除该房间对应的水电记录
	 */
	@Override
	public void updateTenantById(Tenant tenant) {
		//改变房间状态
		String id = tenant.getId();
		
		//必须重新new一个对象才能得到房间id
		Tenant tenant1 = tenantDao.findTenantByIdForForbid(id);
		String room_id = tenant1.getRoom_id();
		roomDao.updateRoomStatus(0, room_id);
		
		//同时删除对应的水电
		elecWaterDao.deleteEW(room_id);
		
		tenantDao.updateTenantById(tenant);
	}
	
	/**
	 * 禁用租户列表
	 */
	@Override
	public PagerUtils<Tenant> getForbidTenantList(String name,String street_name,int pageIndex) {
		PagerUtils<Tenant> pr = new PagerUtils<Tenant>();
		int dataCount = tenantDao.getForbidTenantCount(name,street_name);
		pr.setPageIndex(pageIndex);
		pr.setPageSize(10);
		pr.setDataCount(dataCount);
		pr.setPageCount(dataCount%10 == 0 ? dataCount/10 : dataCount/10 + 1);
		pr.setDataList(tenantDao.getForbidTenantList(name,street_name,10 , 10 * (pageIndex - 1)));		
		return pr;
	}

	/**
	 * 根据id删除禁用租户
	 */
	@Override
	public void deleteForbidTenantById(Tenant tenant) {
		tenantDao.deleteForbidTenantById(tenant);
	}

	/**
	 * 查询合同期列表
	 */
	@Override
	public PagerUtils<Tenant> selectContract(String year, String month,String street_name,
			int pageIndex) {
		PagerUtils<Tenant> pr = new PagerUtils<Tenant>();
		int dataCount = tenantDao.getContractCount(year, month,street_name);
		pr.setPageIndex(pageIndex);
		pr.setPageSize(10);
		pr.setDataCount(dataCount);
		pr.setPageCount(dataCount%10 == 0 ? dataCount/10 : dataCount/10 + 1);
		pr.setDataList(tenantDao.selectContract(year,month,street_name, 10 , 10 * (pageIndex - 1)));		
		return pr;
	}

	/**
	 * 用户与房间建立关系(签约)
	 */
	@SuppressWarnings("deprecation")
	@Override
	@Transactional(rollbackFor={RuntimeException.class, Exception.class},
				   noRollbackFor=ApiException.class)
	public void addTenantBySign(TenantVo tenantVo) throws Exception {
		//把用户状态改为租户
		//String id = tenant.getUser_id();
		//userDao.updateUsertype(id);
		
		//改变房间状态
		String room_id = tenantVo.getRoom_id();
		roomDao.updateRoomStatus(1, room_id);
		
		//发送短信、暂时发送测试模板
	    String phone = "13302909579";
		
		String street_name = tenantVo.getStreet_name();
		String room_name = tenantVo.getRoom_name();
		BigDecimal total = tenantVo.getTotal();
		String money = total.toString();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		map.put("street_name", street_name);
		map.put("room_name", room_name);
		map.put("money", money);
		AliDaYuMsg.send(map, 1);
		
		//添加合同信息	
		tenantDao.addTenantBySign(tenantVo);
		
		//更新相应起止时间
		Date time_temp = tenantVo.getContract_term_start();
		int month = time_temp.getMonth() + 1;
		time_temp.setMonth(month);
		
		tenantVo.setTime_end(time_temp);
		tenantVo.setWater_time_end(time_temp);
		tenantVo.setCall_end(time_temp);
		
	    tenantDao.updateSomeTimeByRoomId(tenantVo);
		
		//操作日志、模板消息 待定
	    
	}

	/**
	 * 根据街道得到待交租户列表
	 */
	@Override
	public PagerUtils<Tenant> getToPayTenantList(String name,
												 String street_name, 
												 int pageIndex) {
		PagerUtils<Tenant> pr = new PagerUtils<Tenant>();
		int dataCount = tenantDao.getToPayTenantCount(name,street_name);
		pr.setPageIndex(pageIndex);
		pr.setPageSize(10);
		pr.setDataCount(dataCount);
		pr.setPageCount(dataCount%10 == 0 ? dataCount/10 : dataCount/10 + 1);
		pr.setDataList(tenantDao.getToPayTenantList(name,street_name,10 , 10 * (pageIndex - 1)));		
		return pr;
	}

	/**
	 * 根据街道得到待抄水电列表
	 */
	@Override
	public PagerUtils<Tenant> getToCopyEWTenantList(String room_name,
			String street_name, int pageIndex) {
		PagerUtils<Tenant> pr = new PagerUtils<Tenant>();
		int dataCount = tenantDao.getToCopyEWTenantCount(room_name,street_name);
		pr.setPageIndex(pageIndex);
		pr.setPageSize(10);
		pr.setDataCount(dataCount);
		pr.setPageCount(dataCount%10 == 0 ? dataCount/10 : dataCount/10 + 1);
		pr.setDataList(tenantDao.getToCopyEWTenantList(room_name,street_name,10 , 10 * (pageIndex - 1)));		
		return pr;
	}

	/**
	 * 催租功能
	 * @throws ApiException 
	 */
	@Override
	public void updatecallEnd(Tenant tenant) throws ApiException {
		//更新催租时间
		tenantDao.updatecallEnd(tenant);
		
		//发送催租短信
		String id = tenant.getId();
		TenantVo tenantVo = tenantDao.findTenantById(id);
		//String phone = tenantVo.getPhone();
		String phone = "13807927278";
		//String phone = "17683836475";
		
		String name = tenantVo.getName();
		BigDecimal money = tenantVo.getM_money();
		String m_money = money.toString();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = tenantVo.getTime_end();
		String time_end = sdf.format(date);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		map.put("name", name);
		map.put("m_money", m_money);
		map.put("time_end", time_end);
		AliDaYuMsg.send(map, 2);
	}

	/**
	 * 根据id查询租户，用于修改租户信息
	 */
	@Override
	public TenantVo selectTenantById(String id) {
		return tenantDao.selectTenantById(id);
	}

	/**
	 * 根据id查询租户，用于禁用租户相关功能
	 */
	@Override
	public Tenant findTenantByIdForForbid(String id) {
		return tenantDao.findTenantByIdForForbid(id);
	}

	/**
	 * 更新已交租用的时间和交租记录表的已交字段，目前方便管理员操作
	 */
	@Override
	public void updatetimeEnd(Tenant tenant) {
		String id = tenant.getId();
		payRecordDao.updateisPay(id);
		tenantDao.updatetimeEnd(tenant);
	}

	@Override
	public PagerUtils<Tenant> getExpireTenantList(String room_name,String street_name,
			int pageIndex) {
		PagerUtils<Tenant> pr = new PagerUtils<Tenant>();
		int dataCount = tenantDao.getExpireTenantCount(room_name,street_name);
		pr.setPageIndex(pageIndex);
		pr.setPageSize(10);
		pr.setDataCount(dataCount);
		pr.setPageCount(dataCount%10 == 0 ? dataCount/10 : dataCount/10 + 1);
		pr.setDataList(tenantDao.getExpireTenantList(room_name,street_name,10 , 10 * (pageIndex - 1)));		
		return pr;
	}

	@Override
	public int getExpireCountBystreet(String street_name) {
		return tenantDao.getExpireCountBystreet(street_name);
	}

	@Override
	public int getExpireCount() {
		return tenantDao.getExpireCount();
	}


}
