package com.yzx.framework.core.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import com.yzx.framework.core.dao.DBResultSet;
import com.yzx.framework.core.dao.DataAccess;

@Component("jdbcDataAccess")
public class DataAccessImpl implements DataAccess{
	
	private JdbcTemplate jdbcTemplate;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private DataSource dataSource;
	
	public DataAccessImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		if (this.jdbcTemplate == null ) {
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}
		if (this.namedParameterJdbcTemplate == null) {
			namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		}		
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}
	
	private Connection getConnection() {
		return DataSourceUtils.getConnection(this.dataSource);
	}
	public void releaseConnection(Connection conn) {
		DataSourceUtils.releaseConnection(conn, this.dataSource);		
	}
	
	public int insert(String sql) throws Exception {		
		return this.jdbcTemplate.update(sql);
	}

	public int update(String sql) throws Exception {		
		return this.jdbcTemplate.update(sql);
	}

	public int delete(String sql) throws Exception {
		return this.jdbcTemplate.update(sql);
	}

	public int insert(String sql, Object[] params) throws Exception {
		return this.jdbcTemplate.update(sql, params);
	}
	
	public int update(String sql, Object[] params) throws Exception {
		return this.jdbcTemplate.update(sql, params);
	}
	
	public int delete(String sql, Object[] params) throws Exception {
		return this.jdbcTemplate.update(sql, params);
	}
	
	public int insert(String sql, Map<String, Object> params) throws Exception {
		return this.namedParameterJdbcTemplate.update(sql, params);
	}
	
	public int update(String sql, Map<String, Object> params) throws Exception {
		return this.namedParameterJdbcTemplate.update(sql, params);
	}
	
	public int delete(String sql, Map<String, Object> params) throws Exception {
		return this.namedParameterJdbcTemplate.update(sql, params);
	}
	
	public int[] insert(String[] sql) throws Exception {
		return this.jdbcTemplate.batchUpdate(sql);
	}

	public int[] update(String[] sql) throws Exception {
		return this.jdbcTemplate.batchUpdate(sql);
	}

	public int[] delete(String[] sql) throws Exception {
		return this.jdbcTemplate.batchUpdate(sql);
	}
	
	public int[] insert(final String sql, final List<Object[]> params) throws Exception {
		int rtn[] = this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				for (int j = 0; j < params.get(i).length; j++) {
					ps.setObject(j + 1, params.get(i)[j]);
				}
			}
			public int getBatchSize() {
				return params.size();
			}
		});
		return rtn;
	}

	public int[] update(final String sql, final List<Object[]> params) throws Exception {
		int rtn[] = this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				for (int j = 0; j < params.get(i).length; j++) {
					ps.setObject(j + 1, params.get(i)[j]);
				}
			}
			public int getBatchSize() {
				return params.size();
			}
		});
		return rtn;
	}

	public int[] delete(final String sql, final List<Object[]> params) throws Exception {
		int rtn[] = this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				for (int j = 0; j < params.get(i).length; j++) {
					ps.setObject(j + 1, params.get(i)[j]);
				}
			}
			public int getBatchSize() {
				return params.size();
			}
		});
		return rtn;
	}
	
	public int[] insert(String sql, Map<String, ?>[] params) throws Exception {
		return this.namedParameterJdbcTemplate.batchUpdate(sql, params);
	}

	public int[] update(String sql, Map<String, ?>[] params) throws Exception {
		return this.namedParameterJdbcTemplate.batchUpdate(sql, params);
	}

	public int[] delete(String sql, Map<String, ?>[] params) throws Exception {
		return this.namedParameterJdbcTemplate.batchUpdate(sql, params);
	}
	
	public Map<?, ?> queryForMap(String sql) throws Exception {		
		return this.jdbcTemplate.queryForMap(sql);
	}

	public Map<?, ?> queryForMap(String sql, Object[] params) throws Exception {		
		return this.jdbcTemplate.queryForMap(sql, params);
	}
	
	public List<?> queryForList(String sql) throws Exception {
		return this.jdbcTemplate.queryForList(sql);
	}

	public List<?> queryForList(String sql, Object[] params) throws Exception {
		return this.jdbcTemplate.queryForList(sql, params);
	}
	
	public Map<?, ?> queryForMap(String sql, Map<String, Object> params) throws Exception {
		return this.namedParameterJdbcTemplate.queryForMap(sql, params);
	}

	public List<?> queryForList(String sql, Map<String, Object> params) throws Exception {
		return this.namedParameterJdbcTemplate.queryForList(sql, params);
	}

	public DBResultSet executeQuery(String sql) throws Exception {		
		return executeQuery(sql, null);
	}
	
	public DBResultSet executeQuery(String sql, Object[] params) throws Exception {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			this.setPreparedStatementParamater(1, pstmt, params);
			rs = pstmt.executeQuery();
			rs.last();
			int totalCount = rs.getRow();
			rs.beforeFirst();
			DBResultSet dbRes = DBResultSet.createDBResultSet(rs, totalCount, this);
			return dbRes;
		} catch (Exception e) {
			if (pstmt != null) {
				pstmt.close();
			}
			throw e;
		}
	}
	
	/**
	 * @param startIndex
	 * @param pstmt
	 * @param objs
	 * @throws SQLException
	 */
	private void setPreparedStatementParamater(int startIndex,
			PreparedStatement pstmt, Object[] objs) throws SQLException {
		for (Object obj : objs) {
			if (obj instanceof String) {
				pstmt.setString(startIndex++, (String) obj);
			} else {
				pstmt.setObject(startIndex++, obj);
			}
		}
	}	
}
