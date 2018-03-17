package com.yzx.framework.core.dao.support;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class YzxTrabsactionManager {
	private DataSourceTransactionManager txManager;

	private DefaultTransactionDefinition def;
	
	private TransactionStatus status;
	
	public static YzxTrabsactionManager createTransactionManager(DataSource ds, int propagationBehavior) {
		YzxTrabsactionManager tx = new YzxTrabsactionManager(ds, propagationBehavior);		
		return tx;
	}
	
	public static YzxTrabsactionManager createTransactionManager(DataSource ds, int propagationBehavior, int timeout) {
		YzxTrabsactionManager tx = new YzxTrabsactionManager(ds, propagationBehavior, timeout);
		return tx;
	}
	
	private YzxTrabsactionManager(DataSource ds, int propagationBehavior) {
		txManager = new DataSourceTransactionManager(ds);
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(propagationBehavior);
		status = txManager.getTransaction(def);
	}
	
	private YzxTrabsactionManager(DataSource ds, int propagationBehavior, int timeout) {
		txManager = new DataSourceTransactionManager(ds);
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(propagationBehavior);
		def.setTimeout(timeout);
		status = txManager.getTransaction(def);
	}
	
	public void commit() {
		txManager.commit(status);
	}
	
	public void rollBack() {
		txManager.rollback(status);
	}
	
	public void release() {
		if (!status.isCompleted())
			txManager.rollback(status);
	}

	/**
	 * @return the txManager
	 */
	public DataSourceTransactionManager getTxManager() {
		return this.txManager;
	}

	/**
	 * @param txManager the txManager to set
	 */
	public void setTxManager(DataSourceTransactionManager txManager) {
		this.txManager = txManager;
	}

	/**
	 * @return the def
	 */
	public DefaultTransactionDefinition getDef() {
		return def;
	}

	/**
	 * @param def the def to set
	 */
	public void setDef(DefaultTransactionDefinition def) {
		this.def = def;
	}

	/**
	 * @return the status
	 */
	public TransactionStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

}
