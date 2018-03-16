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
	private Map<?, ?> paramMap;
	//sql參數
	private Object[] paramObjs;
	//sql參數
	private List<Object> batchList;
	//使用的框架型態
	private Type type;		
	
	public static Query creatQueryBySqlId(String sqlId, Map<String, Object> param) {
		Query q = new QueryImpl();
		q.setSqlId(sqlId);
		q.setParamMap(param);
		q.setType(Type.MyBatis);
		return q;
	}
	
	public static Query creatQueryBySql(String sql, Map<String, Object> param) {
		Query q = new QueryImpl();
		q.setSql(sql);
		q.setParamMap(param);
		q.setType(Type.JDBCTemplate);
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
	public Map<?, ?> getParamMap() {
		return paramMap;
	}
	/**
	 * @param paramMap the paramMap to set
	 */
	public void setParamMap(Map<?, ?> paramMap) {
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

}
