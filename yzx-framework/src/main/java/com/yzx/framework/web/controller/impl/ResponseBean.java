package com.yzx.framework.web.controller.impl;

import java.io.Serializable;

/**
 * @author Jason
 *
 */
public class ResponseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum ReturnType {
		JSON, SERVER_PAGE_FORWARD, DOWNLOAD, NO_RETURN
	}
	private Object data;
	private String returnPage;
	private ReturnType returnType = null;
	
	public ResponseBean() {
		this.setReturnType(ReturnType.JSON);
	}

	/**
	 * @return the returnType
	 */
	public ReturnType getReturnType() {
		return returnType;
	}
	/**
	 * @param returnType the returnType to set
	 */
	public void setReturnType(ReturnType returnType) {
		this.returnType = returnType;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the returnPage
	 */
	public String getReturnPage() {
		return returnPage;
	}

	/**
	 * @param returnPage the returnPage to set
	 */
	public void setReturnPage(String returnPage) {
		this.returnPage = returnPage;
	}
	
}
