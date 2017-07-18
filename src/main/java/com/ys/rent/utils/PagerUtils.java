package com.ys.rent.utils;

import java.util.List;

/**
 * 分页功能
 * @author 云尚公寓
 *
 * @param <T>
 */
public class PagerUtils<T> {
	
	private int pageIndex;//起始页
	
	private int pageSize;//分页大小
	
	private int pageCount;//分页数量
	
	private int dataCount;//数据数量
	
	private List<T> dataList;//数据

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	
}
