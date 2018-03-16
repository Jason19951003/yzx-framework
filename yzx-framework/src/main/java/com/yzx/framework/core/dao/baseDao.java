package com.yzx.framework.core.dao;

import javax.sql.DataSource;

import org.springframework.transaction.TransactionDefinition;

public interface baseDao {
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
	 * @param q
	 * @return
	 */
	public DBResultSet queryDBresultSet(Query q);
	/**
	 * 
	 * @param q
	 * @return
	 */
	public DBResultSetList queryDBresultSetList(Query q);
}
