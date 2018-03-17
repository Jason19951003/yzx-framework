package com.yzx.framework.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import com.yzx.framework.core.dao.BaseDao;
import com.yzx.framework.core.dao.DBResultSet;
import com.yzx.framework.core.dao.DBResultSetList;
import com.yzx.framework.core.dao.DataAccess;
import com.yzx.framework.core.dao.Query;
import com.yzx.framework.core.dao.Query.Type;
import com.yzx.framework.core.dao.support.GenerateResult;
import com.yzx.framework.core.dao.support.SqlGenerate;
import com.yzx.framework.core.dao.support.YzxTrabsactionManager;

public abstract class AbstractDao implements BaseDao {
	
	@Autowired
	private ApplicationContext applicationContext;
	/*資料庫來源*/
	private DataSource dataSource;
	
	private Type type;
	
	public DataSource getDataSource() {
		return this.dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ApplicationContext getApplicationContext() {
		return this.applicationContext;
	}	
	
	public YzxTrabsactionManager beginTransaction() throws Exception {
		return beginTransaction(BaseDao.PROPAGATION_NESTED);
	}
	
	public YzxTrabsactionManager beginTransaction(int propagationBehavior) throws Exception {
		return YzxTrabsactionManager.createTransactionManager(dataSource, propagationBehavior);
	}

	public YzxTrabsactionManager beginTransaction(int propagationBehavior, int timeout) throws Exception {
		return YzxTrabsactionManager.createTransactionManager(dataSource, propagationBehavior, timeout);
	}
	
	/**
	 * @return
	 */
	public SqlGenerate getSqlGenerate() {
		return (SqlGenerate)this.applicationContext.getBean("myBatisSqlGenerate");
	}
	
	/**
	 * @return
	 */
	private final DataAccess getDataAccess() {		
		DataAccess da = (DataAccess)getApplicationContext().getBean("jdbcDataAccess");
		Assert.notNull(this.getDataSource(), " dataSource is null.");
		da.setDataSource(this.getDataSource());		
		return da;
	}
	
	public int insert(Query q) throws Exception {
		type = q.getType();
		switch (type) {
		case MyBatis:
			SqlGenerate sqlGenerate = getSqlGenerate();
			GenerateResult generateResult = sqlGenerate.generate(q.getSql(), q.getParamMap());
			if (q.getParamMap() == null || q.getParamMap().isEmpty()) {
				return getDataAccess().insert(generateResult.getSql());
			} else {
				return getDataAccess().insert(generateResult.getSql(), generateResult.getParams());
			}
		case JDBC:
			if (q.getParamObjs() == null || q.getParamObjs().length == 0) {
				return getDataAccess().insert(q.getSql());
			} else {
				return getDataAccess().insert(q.getSql(),q.getParamObjs());
			}
		case JDBCTemplate:
			if (q.getParamMap() == null || q.getParamMap().isEmpty()) {
				return getDataAccess().insert(q.getSql());
			} else {
				return getDataAccess().insert(q.getSql(), q.getParamMap());
			}				
		default:
			throw new Exception("Type Can't null!");			
		}		
	}

	public int update(Query q) throws Exception {
		type = q.getType();
		switch (type) {
		case MyBatis:
			SqlGenerate sqlGenerate = getSqlGenerate();
			GenerateResult generateResult = sqlGenerate.generate(q.getSql(), q.getParamMap());
			if (q.getParamMap() == null || q.getParamMap().isEmpty()) {
				return getDataAccess().update(generateResult.getSql());
			} else {
				return getDataAccess().update(generateResult.getSql(), generateResult.getParams());
			}
		case JDBC:
			if (q.getParamObjs() == null || q.getParamObjs().length == 0) {
				return getDataAccess().update(q.getSql());
			} else {
				return getDataAccess().update(q.getSql(),q.getParamObjs());
			}
		case JDBCTemplate:
			if (q.getParamMap() == null || q.getParamMap().isEmpty()) {
				return getDataAccess().update(q.getSql());
			} else {
				return getDataAccess().update(q.getSql(), q.getParamMap());
			}				
		default:
			throw new Exception("Type Can't null!");			
		}
	}

	public int delete(Query q) throws Exception {
		type = q.getType();
		switch (type) {
		case MyBatis:
			SqlGenerate sqlGenerate = getSqlGenerate();
			GenerateResult generateResult = sqlGenerate.generate(q.getSql(), q.getParamMap());
			if (q.getParamMap() == null || q.getParamMap().isEmpty()) {
				return getDataAccess().delete(generateResult.getSql());
			} else {
				return getDataAccess().delete(generateResult.getSql(), generateResult.getParams());
			}
		case JDBC:
			if (q.getParamObjs() == null || q.getParamObjs().length == 0) {
				return getDataAccess().delete(q.getSql());
			} else {
				return getDataAccess().delete(q.getSql(),q.getParamObjs());
			}
		case JDBCTemplate:
			if (q.getParamMap() == null || q.getParamMap().isEmpty()) {
				return getDataAccess().delete(q.getSql());
			} else {
				return getDataAccess().delete(q.getSql(), q.getParamMap());
			}
		default:
			throw new Exception("Type Can't null!");			
		}
	}	

	public int[] batchInsert(Query q) throws Exception {
		type = q.getType();
		switch (type) {
		case MyBatis:
			SqlGenerate sqlGenerate = getSqlGenerate();
			List<Object[]> paramList = new ArrayList<Object[]>();
			String sql = "";
			for (Object obj : q.getBatchList()) {
				GenerateResult generateResult = sqlGenerate.generate(q.getSql(), (Map)obj);
				sql = generateResult.getSql();
				paramList.add(generateResult.getParams());
			}
			return getDataAccess().insert(sql, paramList);
		case JDBC:
			if (q.getBatchListArr() == null || q.getBatchListArr().size() == 0) {
				throw new Exception("SQL參數不可為空!");
			} else {
				return getDataAccess().insert(q.getSql(), q.getBatchListArr());
			}
		case JDBCTemplate:
			if (q.getParamMapArr() == null) {
				throw new Exception("SQL參數不可為空!");
			} else {
				return getDataAccess().insert(q.getSql(), q.getParamMapArr());
			}
		default:
			throw new Exception("Type Can't null!");
		}
	}

	public int[] batchUpdate(Query q) throws Exception {
		type = q.getType();
		switch (type) {
		case MyBatis:
			SqlGenerate sqlGenerate = getSqlGenerate();
			List<Object[]> paramList = new ArrayList<Object[]>();
			String sql = "";
			for (Object obj : q.getBatchList()) {
				GenerateResult generateResult = sqlGenerate.generate(q.getSql(), (Map)obj);
				sql = generateResult.getSql();
				paramList.add(generateResult.getParams());
			}
			return getDataAccess().insert(sql, paramList);
		case JDBC:
			if (q.getBatchListArr() == null || q.getBatchListArr().size() == 0) {
				throw new Exception("SQL參數不可為空!");
			} else {
				return getDataAccess().insert(q.getSql(), q.getBatchListArr());
			}
		case JDBCTemplate:
			if (q.getParamMapArr() == null) {
				throw new Exception("SQL參數不可為空!");
			} else {
				return getDataAccess().insert(q.getSql(), q.getParamMapArr());
			}
		default:
			throw new Exception("Type Can't null!");
		}
	}
	
	public int[] batchDelete(Query q) throws Exception {
		type = q.getType();
		switch (type) {
		case MyBatis:
			SqlGenerate sqlGenerate = getSqlGenerate();
			List<Object[]> paramList = new ArrayList<Object[]>();
			String sql = "";
			for (Object obj : q.getBatchList()) {
				GenerateResult generateResult = sqlGenerate.generate(q.getSql(), (Map)obj);
				sql = generateResult.getSql();
				paramList.add(generateResult.getParams());
			}
			return getDataAccess().insert(sql, paramList);
		case JDBC:
			if (q.getBatchListArr() == null || q.getBatchListArr().size() == 0) {
				throw new Exception("SQL參數不可為空!");
			} else {
				return getDataAccess().insert(q.getSql(), q.getBatchListArr());
			}
		case JDBCTemplate:
			if (q.getParamMapArr() == null) {
				throw new Exception("SQL參數不可為空!");
			} else {
				return getDataAccess().insert(q.getSql(), q.getParamMapArr());
			}
		default:
			throw new Exception("Type Can't null!");
		}
	}
	
	public DBResultSet queryDBresultSet(Query q) throws Exception {
		type = q.getType();
		switch (type) {
		case MyBatis:
			SqlGenerate sqlGenerate = getSqlGenerate();
			GenerateResult generateResult = sqlGenerate.generate(q.getSql(), q.getParamMap());
			if (q.getParamMap() == null || q.getParamMap().isEmpty()) {
				return getDataAccess().executeQuery(generateResult.getSql());
			} else {
				return getDataAccess().executeQuery(generateResult.getSql(), generateResult.getParams());
			}
		case JDBC:
			if (q.getParamObjs() == null || q.getParamObjs().length == 0) {
				return getDataAccess().executeQuery(q.getSql());
			} else {
				return getDataAccess().executeQuery(q.getSql(), q.getParamObjs());
			}
		case JDBCTemplate:
			throw new Exception("DBResult無提供 JDBCTemplate 類型的查詢方法!");
		default:
			throw new Exception("Type Can't null!");
		}
	}

	public DBResultSetList queryDBresultSetList(Query q) throws Exception {
		type = q.getType();
		switch (type) {
		case MyBatis:
			SqlGenerate sqlGenerate = getSqlGenerate();
			GenerateResult generateResult = sqlGenerate.generate(q.getSql(), q.getParamMap());
			if (q.getParamMap() == null || q.getParamMap().isEmpty()) {
				List<?> dataList = getDataAccess().queryForList(generateResult.getSql());
				return DBResultSetList.createDBResultSetList(dataList);
			} else {
				List<?> dataList = getDataAccess().queryForList(generateResult.getSql(), generateResult.getParams());
				return DBResultSetList.createDBResultSetList(dataList);
			}
		case JDBC:
			if (q.getParamObjs() == null || q.getParamObjs().length == 0) {
				List<?> dataList = getDataAccess().queryForList(q.getSql());
				return DBResultSetList.createDBResultSetList(dataList);
			} else {
				List<?> dataList = getDataAccess().queryForList(q.getSql(),q.getParamObjs());
				return DBResultSetList.createDBResultSetList(dataList);
			}
		case JDBCTemplate:
			if (q.getParamMap() == null || q.getParamMap().isEmpty()) {
				List<?> dataList = getDataAccess().queryForList(q.getSql());
				return DBResultSetList.createDBResultSetList(dataList);
			} else {
				List<?> dataList = getDataAccess().queryForList(q.getSql(), q.getParamMap());
				return DBResultSetList.createDBResultSetList(dataList);
			}
		default:
			throw new Exception("Type Can't null!");
		}
	}
	
	public Map queryForMap(Query q) throws Exception {
		DBResultSetList result = queryDBresultSetList(q);
		return result.getFirstByMap();
	}	
}
