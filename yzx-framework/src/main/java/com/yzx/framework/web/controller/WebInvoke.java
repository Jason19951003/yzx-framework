package com.yzx.framework.web.controller;

/**
 * @author Jason
 *
 */
public interface WebInvoke {
	/**
	 * @param args
	 */
	public void setTargetObject(Object object);
	
	/**
	 * @param cmd
	 */
	public void setTargetMathodName(String cmd);
	
	
	/**
	 * @param args
	 * @return
	 * @throws Throwable
	 */
	public Object invoke(Object[] args) throws Throwable ;
}
