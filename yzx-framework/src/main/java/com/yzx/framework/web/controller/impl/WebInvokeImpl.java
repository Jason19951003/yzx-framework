package com.yzx.framework.web.controller.impl;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import com.yzx.framework.web.controller.WebInvoke;

public class WebInvokeImpl implements WebInvoke {
	
	private String targetMethodName;
	
	private Object object;
	
	public void setTargetObject(Object object) {		
		this.object = object;
	}

	public void setTargetMathodName(String targetMethodName) {
		this.targetMethodName = targetMethodName;
	}

	public Object invoke(Object[] args) throws Throwable {
		Method m = object.getClass().getMethod(targetMethodName, new Class[] { RequestBean.class, ResponseBean.class, HttpServletResponse.class });
		Object rtn = m.invoke(object, args[0], args[1], args[2]);
		return rtn;
	}

}
