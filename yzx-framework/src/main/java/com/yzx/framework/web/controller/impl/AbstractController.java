package com.yzx.framework.web.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yzx.framework.core.common.BaseConstant;
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
	
	private static final int DOWNLOAD_BUFFER_SIZE = 8096;
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
	
	private AbstractController getCurrent() {
		return this;
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
	 * 下載檔案(使用預設檔名)
	 * @param response
	 * @param f
	 */
	public void pushToClient(HttpServletResponse response, File f)  {
		pushToClient(response, f, f.getName());
	}
	/**
	 * 下載檔案(自定義檔名)
	 * @param response
	 * @param f
	 * @param fileName
	 */
	public void pushToClient(HttpServletResponse response, File f, String fileName)  {
		response.setContentType(BaseConstant.HTTP_CONTENT_TYPE_STREAM);
		response.setContentLength((int)f.length());
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		InputStream fis = null;
		OutputStream out = null;
		try {
			fis = new FileInputStream(f);
			out = response.getOutputStream();
			int len = 0;
			byte[] buffer = new byte[DOWNLOAD_BUFFER_SIZE];
			
			while((len = fis.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
