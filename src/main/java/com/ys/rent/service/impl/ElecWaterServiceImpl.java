package com.ys.rent.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.rent.dao.ElecWaterDao;
import com.ys.rent.dao.OperatorDao;
import com.ys.rent.dao.PayRecordDao;
import com.ys.rent.dao.RoomDao;
import com.ys.rent.dao.TenantDao;
import com.ys.rent.po.ElecWater;
import com.ys.rent.po.PayRecord;
import com.ys.rent.po.Tenant;
import com.ys.rent.service.ElecWaterService;
import com.ys.rent.utils.UUIDUtils;

/**
 * 水电的业务层实现类
 * @author 云尚公寓
 *
 */
@Service
public class ElecWaterServiceImpl implements ElecWaterService{
	
	@Autowired
	private ElecWaterDao elecWaterDao;
	
	@Autowired
	private TenantDao tenantDao;
	
	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private PayRecordDao payRecordDao;
	
	@Autowired
	private OperatorDao operatorDao;

	/**
	 * 添加水电
	 */
	@Override
	public void addElecWater(ElecWater elecWater){
		
		//得到当前房间的id
		String room_id = elecWater.getRoom_id();
		
		//得到当前年月
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = (now.get(Calendar.MONTH) + 1);
		
		elecWater.setYear(year);
		elecWater.setMonth(month);
		
		//先将管理员从页面中添加的数据添加到数据库
		elecWaterDao.addElecWater(elecWater);
		
		//进行水费电费的计算
		List<ElecWater> list = elecWaterDao.findEWByRoomId(room_id);
		for (int i = 0; i < list.size(); i++) {
//			int water1 = list.get(0).getWater();//得到上月的水表数
//			int water2 = list.get(1).getWater();//得到本月的水表数
//			int tempwater = Math.abs(water2 - water1);
//			BigDecimal w_money = new BigDecimal(Math.ceil(tempwater*8));//得到水费
			BigDecimal w_money = new BigDecimal(48);//得到水费
			elecWater.setW_money(w_money);  
			
			int elec1 = list.get(0).getElectric();//得到上月的电表数
			int elec2 = list.get(1).getElectric();//得到本月的电表数
			int tempelec = Math.abs(elec2-elec1);
			BigDecimal e_money = new BigDecimal(Math.ceil(tempelec*1.8));//得到电费
			elecWater.setE_money(e_money);
			
		}
		
		//将水电的起止时间改为下个月，即水电添加成功后不再显示当前房间
		tenantDao.updatewaterEnd(room_id);
		//将水费电费更新到数据库
	    elecWaterDao.updateEWmoney(elecWater);
		
	    /**
	     * 1.new一个交租记录表的对象
	     */
	    PayRecord payRecord = new PayRecord();
		
	    /**
	     * 2.得到水电费
	     */
		BigDecimal w_money = elecWater.getW_money();
		BigDecimal e_money = elecWater.getE_money();
		BigDecimal temp_money = w_money.add(e_money);
		
		/**
		 * 3.得到月基本租金和杂费
		 */
		Tenant tenant = tenantDao.findmrentByRoomId(room_id);
		BigDecimal m_rent = tenant.getM_rent();
		BigDecimal management_cost = tenant.getManagement_cost();
		BigDecimal sanitation_fee = tenant.getSanitation_fee();
		BigDecimal sewage_fee = tenant.getSewage_fee();
		
		BigDecimal temp_mmoney = m_rent.add(management_cost).add(sanitation_fee).add(sewage_fee);
		
		
		/**
		 * 4.计算出本月应交租金
		 */
		BigDecimal m_money = temp_mmoney.add(temp_money);
		
		/**
		 * 5.设置交租记录表的id
		 */
		payRecord.setId(UUIDUtils.getUUID());
		
		/**
		 * 6.设置交租记录表的本月应交月租金(包括水费电费和月基本租金)
		 */
		payRecord.setM_money(m_money);
		
		/**
		 * 7.设置交租记录表与租户关联的id(即哪个租户交的)
		 */
		String tenant_id = tenant.getId();
		payRecord.setTenant_id(tenant_id);
		
		/**
		 * 8.设置交租记录表与房间关联的id(即哪个房间交的)
		 */
		payRecord.setRoom_id(room_id);
		
		/**
		 * 9.设置交租记录表的年月
		 */
		payRecord.setYear(year);
		payRecord.setMonth(month);
		
		payRecordDao.addPayRecord(payRecord);
	    
		
	}

	/**
	 * 管理员签约房间时添加一个水电底
	 */
	@Override
	public void addElecWaterBySign(ElecWater elecWater) {
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);//得到当前年份
		int month = (now.get(Calendar.MONTH) + 1);//得到当前月份
		
		elecWater.setYear(year);
		elecWater.setMonth(month);
		
		elecWaterDao.addElecWaterBySign(elecWater);
		
	}

}
