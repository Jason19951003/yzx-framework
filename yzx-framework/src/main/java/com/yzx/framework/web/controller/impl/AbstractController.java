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

public abstract class AbstractController extends ActionSupport implements BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ActionContext action = ActionContext.getContext();
		
		WebExecuteProxy webExecuteProxy = (WebExecuteProxy) applicationContext.getBean("webExecuteProxy");
		webExecuteProxy.execute(request, response, action, getCurrent());
		return super.execute();
	}
	
	public AbstractController getCurrent() {
		return this;
	}
	
	public String toJsonString(Object obj) {
		Gson gson = new Gson();
		String str = gson.toJson(obj);
		return str;
	}
}
