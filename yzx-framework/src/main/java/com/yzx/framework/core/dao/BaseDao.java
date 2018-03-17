package com.yzx.framework.core.dao;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.TransactionDefinition;

import com.yzx.framework.core.dao.support.YzxTrabsactionManager;

public interface BaseDao {
	/** 指出不應在交易中進行，如果有的話就丟出例外 */
	public static final int PROPAGATION_NEVER = TransactionDefinition.PROPAGATION_NEVER;
	/** 支援現在的交易，如果沒有的話就建立一個新的交易 */
	public static final int PROPAGATION_REQUIRED = TransactionDefinition.PROPAGATION_REQUIRED;
	/** 建立一個新的交易，如果現存一個交易的話就先暫停，並啟始一個新的交易來執行*/
	public static final int PROPAGATION_REQUIRES_NEW = TransactionDefinition.PROPAGATION_REQUIRES_NEW;
	/** 支援現在的交易，如果沒有的話就以非交易的方式執行*/
	public static final int PROPAGATION_SUPPORTS = TransactionDefinition.PROPAGATION_SUPPORTS;
	/** 方法必須在一個現存的交易中進行，否則丟出例外*/
	public static final int PROPAGATION_MANDATORY = TransactionDefinition.PROPAGATION_MANDATORY;
	/** 指出不應在交易中進行，如果有的話就暫停現存的交易*/
	public static final int PROPAGATION_NOT_SUPPORTED = TransactionDefinition.PROPAGATION_NOT_SUPPORTED;
	/** 在一個巢狀的交易中進行，如果不是的話，則同PROPAGATION_REQUIRED*/
	public static final int PROPAGATION_NESTED = TransactionDefinition.PROPAGATION_NESTED;
	/** 預設交易 TIMEOUT 時間 300 秒 */
	public static int DEFAULT_TRANSACTION_TIMEOUT = 300;
	/**
	 * 
	 * @return
	 */
	public DataSource getDataSource();
	
	/**
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource);
	
	/**
	 * 
	 * @return
	 */
	public ApplicationContext getApplicationContext();
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public YzxTrabsactionManager beginTransaction() throws Exception;
	
	/**
	 * 	
	 * @param propagationBehavior
	 * @return
	 * @throws Exception
	 */	
	public YzxTrabsactionManager beginTransaction(int propagationBehavior) throws Exception ;
	
	/**
	 * 
	 * @param propagationBehavior
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public YzxTrabsactionManager beginTransaction(int propagationBehavior, int timeout) throws Exception ;
	
	/**
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	public int insert(Query q) throws Exception;
	
	/**
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	public int update(Query q) throws Exception;
	
	/**
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	public int delete(Query q) throws Exception;
	
	/**
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	public int[] batchInsert(Query q) throws Exception;
	
	/**
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	public int[] batchUpdate(Query q) throws Exception;
	
	/**
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	public int[] batchDelete(Query q) throws Exception;
	
	/**
	 * 
	 * @param q
	 * @return
	 * @throws Exception 
	 */
	public DBResultSet queryDBresultSet(Query q) throws Exception;
	
	/**
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	public DBResultSetList queryDBresultSetList(Query q) throws Exception;
	
	/**
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	public Map queryForMap(Query q) throws Exception;
}
