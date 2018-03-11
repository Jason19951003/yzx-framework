package com.yzx.framework.web.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yzx.framework.web.controller.BaseController;
import com.yzx.framework.web.controller.WebExecuteProxy;

/**
 * @author Jason
 *
 */
public abstract class AbstractController extends ActionSupport implements BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ApplicationContext applicationContext;
	
	public enum ReturnType {
		SUCCESS, NONE, FORWARD
	}
	
	private ReturnType returnType = null;	

	@Override
	public String execute() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			ActionContext action = ActionContext.getContext();
			
			WebExecuteProxy webExecuteProxy = (WebExecuteProxy) applicationContext.getBean("webExecuteProxy");
			webExecuteProxy.execute(request, response, action, getCurrent());
			
			if (returnType == null) {
				returnType = ReturnType.NONE;
			}
			if (!response.isCommitted()) {
				switch (returnType) {
				case SUCCESS:
					return SUCCESS;
					
				case NONE:
					
					return NONE;
				case FORWARD:
					
					return NONE;
				default:
					return NONE;
				}
			} else {
				return NONE;
			}
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public AbstractController getCurrent() {
		return this;
	}
	
	public String toJsonString(Object obj) {
		Gson gson = new Gson();
		String str = gson.toJson(obj);
		return str;
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
}
