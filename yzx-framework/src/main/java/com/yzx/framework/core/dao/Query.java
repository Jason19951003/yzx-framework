package com.yzx.framework.core.dao;

import java.util.List;
import java.util.Map;

import com.yzx.framework.core.dao.impl.QueryImpl;

public abstract class Query {

	public enum Type {
		MyBatis,JDBCTemplate,JDBC
	}
	//mybatis的mapperID
	private String SqlId;
	//jdbc的sql
	private String sql;
	//sql參數
	private Map<String, Object> paramMap;
	//sql參數
	private Object[] paramObjs;
	//sql參數
	private List<Object> batchList;
	//sql參數
	private List<Object[]> batchListArr;
	
	//sql參數
	private Map<String, Object>[] paramMapArr;
	//使用的框架型態
	private Type type;
	/**
	 * 預設為使用Mybatis
	 * @param sqlId
	 * @param param
	 * @return
	 */
	public static Query creatQueryBySqlId(String sqlId, Map<String, Object> param) {
		Query q = new QueryImpl();
		q.setSqlId(sqlId);
		q.setParamMap(param);
		q.setType(Type.MyBatis);
		return q;
	}
	/**
	 * 預設使用JDBC
	 * @param sql
	 * @param paramObjs
	 * @return
	 */
	public static Query creatQueryBySql(String sql, Object[] paramObjs) {
		Query q = new QueryImpl();
		q.setSql(sql);
		q.setParamObjs(paramObjs);
		q.setType(Type.JDBC);
		return q;
	}
	
	public final static Query createQuery() {
		return new QueryImpl();
	}
	
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}	
	
	/**
	 * @return the sqlId
	 */
	public String getSqlId() {		
		return SqlId;
	}
	/**
	 * @param sqlId the sqlId to set
	 */
	public void setSqlId(String sqlId) {
		SqlId = sqlId;
	}
	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}
	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}
	/**
	 * @return the paramMap
	 */
	public Map<String, Object> getParamMap() {
		return paramMap;
	}
	/**
	 * @param paramMap the paramMap to set
	 */
	public void setParamMap(Map<String, Object> paramMap) {		
		this.paramMap = paramMap;
	}
	/**
	 * @return the paramObjs
	 */
	public Object[] getParamObjs() {
		return paramObjs;
	}
	/**
	 * @param paramObjs the paramObjs to set
	 */
	public void setParamObjs(Object[] paramObjs) {		
		this.paramObjs = paramObjs;
	}
	/**
	 * @return the batchList
	 */
	public List<Object> getBatchList() {
		return batchList;
	}
	/**
	 * @param batchList the batchList to set
	 */
	public void setBatchList(List<Object> batchList) {
		this.batchList = batchList;
	}
	/**
	 * @return the paramMapArr
	 */
	public Map<String, Object>[] getParamMapArr() {
		return paramMapArr;
	}
	/**
	 * @param paramMapArr the paramMapArr to set
	 */
	public void setParamMapArr(Map<String, Object>[] paramMapArr) {
		this.paramMapArr = paramMapArr;
	}
	
	/**
	 * @return the batchListArr
	 */
	public List<Object[]> getBatchListArr() {
		return batchListArr;
	}
	/**
	 * @param batchListArr the batchListArr to set
	 */
	public void setBatchListArr(List<Object[]> batchListArr) {
		this.batchListArr = batchListArr;
	}
}
