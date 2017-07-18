package com.ys.rent.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.rent.dao.ManagerDao;
import com.ys.rent.dao.RoomDao;
import com.ys.rent.po.Manager;
import com.ys.rent.service.ManagerService;
import com.ys.rent.utils.PagerUtils;
import com.ys.rent.vo.ManagerVo;

/**
 * 管理员的业务层实现类
 * @author 云尚公寓
 *
 */
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {  
	
     @Autowired
     ManagerDao managerDao;
     
     @Autowired
     RoomDao roomDao;
 
     /**
      * 管理员登录
      */
     @Override
     public Manager login(String username, String password) {
    	 return managerDao.login(username, password);
     }

    /**
     * 检查用户名
     */
	@Override
	public Manager checkUsername(String username) {
		return managerDao.checkUsername(username);
	}

	/**
	 * 管理员列表
	 */
	@Override
	public PagerUtils<ManagerVo> getList(String roleId, 
										 String username,
			                             Date createTime1, 
			                             Date createTime2, 
			                             Integer isUsed,
			                             int pageIndex) {
		
		PagerUtils<ManagerVo> pu = new PagerUtils<ManagerVo>();
		int dataCount = managerDao.getCount(username, roleId, createTime1, createTime2, isUsed);
		pu.setPageIndex(pageIndex);
		pu.setPageSize(10);
		pu.setDataCount(dataCount);
		pu.setPageCount(dataCount%10 == 0 ? dataCount/10 : dataCount/10 + 1);
		pu.setDataList(managerDao.getListByPage(username, roleId, createTime1, createTime2, isUsed, 10 , 10 * (pageIndex - 1)));		
		return pu;
	}

	/**
	 * 添加管理员
	 */
	@Override
	public void add(Manager manager) {
		managerDao.addManager(manager);
	}

	/**
	 * 根据id查询管理员
	 */
	@Override
	public Manager findManagerById(String id) {
		return managerDao.findManagerById(id);
	}

	/**
	 * 修改管理员
	 */
	@Override
	public void editManager(Manager manager) {
		managerDao.editManager(manager);
	}

	/**
	 * 删除管理员
	 */
	@Override
	public void deleteManager(Manager manager) {
		managerDao.deleteManager(manager);
	}

	/**
	 * 批量删除管理员
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void deleteManagers(List managerList) {
		managerDao.deleteManagers(managerList);
	}

	/**
	 * 根据角色id删除管理员
	 */
	@Override
	public void deleteManagerByroleId(String roleId) {
		managerDao.deleteManagerByroleId(roleId);
	}

	@Override
	public Manager findManagerByusername(String username) {
		return managerDao.findManagerByusername(username);
	}

	@Override
	public void updateTime(Manager manager) {
		managerDao.updateTime(manager);
	}
}
