package com.yzx.framework.web.controller.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.yzx.framework.web.controller.WebExecuteProxy;

/**
 * @author Jason
 *
 */
@Component("webExecuteProxy")
@Scope("prototype")
public class WebExecuteProxyImpl implements WebExecuteProxy {
	
	private ApplicationContext applicationContext;	
	private Object targetControllerObject;
	private HttpServletRequest _request;
	private HttpServletResponse _response;	
	private ResponseBean _responseBean;
	private RequestBean _requestBean;
	private ActionContext _action;
	
	/**
	 * 
	 * @param appplcationContext
	 */	
	public final void setApplicationContext(ApplicationContext appplcationContext) {
		this.applicationContext = appplcationContext;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param action
	 * @param targetObject
	 * @throws Exception 
	 */	
	public void execute(HttpServletRequest request, HttpServletResponse response, ActionContext action,
			Object targetObject) throws Exception {
		this._request = request;
		this._response = response;
		this._action = action;
		this.targetControllerObject = targetObject;
		Map<String, Object> requestMap = action.getParameters();
		this._requestBean = RequestBean.buildRequestBean(requestMap);
	}

	public ResponseBean getResponseBean() {		
		return _responseBean;
	}

	public RequestBean getRequestBean() {		
		return _requestBean;
	}

}
