package com.yzx.framework.web.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.yzx.framework.web.manager.BaseManager;

public abstract class AbstractWebManager implements BaseManager {
	@Autowired
	private ApplicationContext applicationContext;
	
	public <T> T getBean(Class<T> clazz) {
		return (T)applicationContext.getBean(clazz);
	}
	
	public Object getBean(String bean) {
		return applicationContext.getBean(bean);
	}
	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param applicationContext the applicationContext to set
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}	
}
