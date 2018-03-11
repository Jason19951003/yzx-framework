package com.yzx.framework.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.yzx.framework.web.controller.impl.RequestBean;
import com.yzx.framework.web.controller.impl.ResponseBean;

public interface WebExecuteProxy {
	
	public void execute(HttpServletRequest request, HttpServletResponse response, ActionContext action, Object targetObject) throws Exception;
	
	public void setApplicationContext(ApplicationContext appplcationContext);
	
	public abstract ResponseBean getResponseBean();
	  
	public abstract RequestBean getRequestBean();
}
