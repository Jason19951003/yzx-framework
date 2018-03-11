package com.yzx.framework.web.controller.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yzx.framework.web.support.UploadFile;

public class RequestBean {
	
	private HttpServletRequest request;
	private Map requestMap;
	private Map<String, List<UploadFile>> multiFileMap;
	
	private RequestBean(Map requestMap) {
		this.requestMap = requestMap;
	}

	
	public static RequestBean buildRequestBean(HttpServletRequest request) throws Exception {
		Map<String, String[]> map = request.getParameterMap();
		Map<String, Object> paraMap = formatMap(map);
		RequestBean requestBean = new RequestBean(paraMap);
		return requestBean;
	}
	
	public static RequestBean buildRequestBean(Map<String, Object> requestMap) throws Exception {
		return null;
	}
	
	/**
	 * 將Map轉為<String,Object>
	 * @param map
	 * @return
	 */
	private static Map<String, Object> formatMap(Map<String, String[]> map) {
		Map<String, Object> tranMap = new Hashtable<String, Object>();
		for (String key : map.keySet()) {
			if (map.get(key) != null && ((String[])map.get(key)).length == 1) {
				String val = map.get(key)[0];
				tranMap.put(key, val);
			} else if (map.get(key) != null && ((String[])map.get(key)).length > 1) {
				tranMap.put(key, map.get(key));
			} else {
				tranMap.put(key, "");
			}
		}		
		return tranMap;
	}
	
	/**
	 * @return
	 */
	public Map getRequestMap() {
		return requestMap;
	}
	
	/**
	 * @param key
	 * @return
	 */
	public String getParameter(String key) {
		return requestMap.get(key) == null ? null : requestMap.get(key).toString();
	}
	
	/**
	 * @return
	 */
	public Map<String, List<UploadFile>> getMultiFileMap() {
		return multiFileMap;
	}
	
	/**
	 * @param key
	 * @return
	 */
	public List<UploadFile> getUploadFile(String key) {
		return multiFileMap.get(key);
	}
	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}		
	
	/**
	 * 取得Session
	 * @return
	 */
	public HttpSession getSession() {
		return this.request.getSession();
	}
	/**
	 * 設置Attribute
	 * @param s
	 * @param obj
	 */
	public void setAttribute(String s, Object obj) {
		this.request.setAttribute(s, obj);
	}
	/**
	 * 取得Attribute
	 * @param s
	 * @return
	 */
	public Object getAttribute(String s) {
		return this.request.getAttribute(s);
	}
}
