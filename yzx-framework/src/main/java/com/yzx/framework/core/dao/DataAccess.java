package com.yzx.framework.core.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public interface DataAccess {
	
	public abstract void setDataSource(DataSource dataSource);
	
	public abstract DataSource getDataSource();
	
	public abstract void releaseConnection(Connection conn);
	
	public abstract int insert(String sql) throws Exception;

	public abstract int update(String sql) throws Exception;

	public abstract int delete(String sql) throws Exception;
	
	public abstract int insert(String sql, Object[] params) throws Exception;

	public abstract int update(String sql, Object[] params) throws Exception;

	public abstract int delete(String sql, Object[] params) throws Exception;
	
	public abstract int insert(String sql, Map<String, Object> params) throws Exception;

	public abstract int update(String sql, Map<String, Object> params) throws Exception;

	public abstract int delete(String sql, Map<String, Object> params) throws Exception;
	
	public abstract int[] insert(String sql[]) throws Exception;

	public abstract int[] update(String sql[]) throws Exception;

	public abstract int[] delete(String sql[]) throws Exception;
	
	public abstract int[] insert(String sql, List<Object[]> params) throws Exception;

	public abstract int[] update(String sql, List<Object[]> params) throws Exception;

	public abstract int[] delete(String sql, List<Object[]> params) throws Exception;
	
	public abstract int[] insert(String sql, Map<String ,?>[] params) throws Exception;

	public abstract int[] update(String sql, Map<String ,?>[] params) throws Exception;

	public abstract int[] delete(String sql, Map<String ,?>[] params) throws Exception;
	
	public abstract Map<?, ?> queryForMap(String sql) throws Exception;
	
	public abstract Map<?, ?> queryForMap(String sql, Object[] params) throws Exception;
	
	public abstract Map<?, ?> queryForMap(String sql, Map<String, Object> params) throws Exception;
	
	public abstract List<?> queryForList(String sql) throws Exception;
	
	public abstract List<?> queryForList(String sql, Object[] params) throws Exception;
	
	public abstract List<?> queryForList(String sql, Map<String, Object> params) throws Exception;
	
	public abstract DBResultSet executeQuery(String sql) throws Exception;
	
	public abstract DBResultSet executeQuery(String sql, Object[] params) throws Exception;	
}
