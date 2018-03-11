package com.yzx.framework.web.controller.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.yzx.framework.core.common.BaseConstant;
import com.yzx.framework.web.controller.BaseController;
import com.yzx.framework.web.controller.WebExecuteProxy;
import com.yzx.framework.web.controller.WebInvoke;


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
		
	public final void setApplicationContext(ApplicationContext appplcationContext) {
		this.applicationContext = appplcationContext;
	}
		
	public void execute(HttpServletRequest request, HttpServletResponse response, ActionContext action,
			Object targetObject) throws Exception {
		this._request = request;
		this._response = response;
		this._action = action;
		this.targetControllerObject = targetObject;
		Map<String, Object> requestMap = action.getParameters();
		this._requestBean = RequestBean.buildRequestBean(requestMap);
		//this._responseBean = new ResponseBean();
		
		try {
			this.executeImpl(_requestBean, response);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private void executeImpl(RequestBean _requestBean, HttpServletResponse response) throws Throwable {
		String cmd = getInvoke();
		
		if (cmd == null) {
			throw new Exception("Controller command參數不能為空!");
		}
		try {
			WebInvoke webInvoke = new WebInvokeImpl();
			webInvoke.setTargetObject(targetControllerObject);	
			webInvoke.setTargetMathodName(cmd);			
			webInvoke.invoke(new Object[]{_requestBean, response});
		} catch (Exception e) {
			throw e;
		} catch (Throwable e) {
			throw e;
		} finally {
			
		}
	}
	private String getInvoke() {
		return this._request.getParameter(BaseConstant.WEB_PROXY_INVOKE_COMMAND);
	}
	public ResponseBean getResponseBean() {
		return _responseBean;
	}

	public RequestBean getRequestBean() {		
		return _requestBean;
	}

}
