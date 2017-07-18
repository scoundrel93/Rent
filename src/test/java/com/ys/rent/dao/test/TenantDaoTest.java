package com.ys.rent.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ys.rent.dao.TenantDao;
import com.ys.rent.po.Tenant;
import com.ys.rent.utils.UUIDUtils;
import com.ys.rent.vo.TenantVo;

/**
 * 租户的dao测试类
 * @author 云尚公寓
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class TenantDaoTest {

	@Autowired
	private TenantDao tenantDao;
	
//	@Test
//	public void addTenant(){
//		Tenant tenant = new Tenant();
//		tenant.setName("曹操");
//		tenant.setId(UUIDUtils.getUUID());
//		tenant.setContract_term_end(new Date());
//		
//		tenantDao.addTenant(tenant);
//	}
	
	@Test
	public void selectYearAndMonth(){
		String year = "2017";
		String month= "6";
		String street_name = "广达路38号";
		int pageSize = 2;
		int startIndex = 0;
		List<Tenant> tenant = tenantDao.selectContract(year, month,street_name, pageSize, startIndex);
		System.out.println(tenant);
		
	}
	
	@Test
	public void addTenantBySign() throws Exception{
		TenantVo tenantVo = new TenantVo();
		tenantVo.setId(UUIDUtils.getUUID());
		tenantVo.setContract_term_start(new Date());
		tenantVo.setContract_term_end(new Date());
		
		tenantDao.addTenantBySign(tenantVo);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void updateTest(){
		List<TenantVo> list = tenantDao.selectAll();
		for (int i = 0; i < list.size(); i++) {
			TenantVo tenantVo = list.get(i);
			Date time_end = tenantVo.getTime_end();
			Date water_time_end = tenantVo.getWater_time_end();
			Date call_end = tenantVo.getCall_end();
			String id = tenantVo.getId();
			
//			time_end.setMonth(06);
//			water_time_end.setMonth(06);
//			call_end.setMonth(06);
			time_end.setYear(117);
			water_time_end.setYear(117);
			call_end.setYear(117);
			tenantDao.updateAll(id,time_end, water_time_end, call_end);
		}
	}
	
	
}
