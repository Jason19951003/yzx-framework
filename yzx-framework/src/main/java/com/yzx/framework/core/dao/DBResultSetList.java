package com.yzx.framework.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBResultSetList {		
	
	private List<?> datalist = new ArrayList<Map<String,Object>>();

	private DBResultSetList(List<?> dataList) {
		this.datalist = dataList;
	}
	
	public static final DBResultSetList createDBResultSetList(List<?> dataList) {
		return new DBResultSetList(dataList);
	}
	
	public int getTotalCount() {
		return datalist.size();
	}

	public List<?> getDatalist() {
		return datalist;
	}
	
	public Map<?, ?> getFirstByMap() {
		return datalist.size() > 0 ? (Map<?, ?>) datalist.get(0) : null;
	}
	
	public void clear() {
		datalist.clear();
	}
	
}
