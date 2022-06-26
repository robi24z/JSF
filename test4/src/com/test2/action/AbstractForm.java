package com.test2.action;

import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.FrameWrkFullPrject.utils.RequestUtil;

public class AbstractForm {
	public FacesContext getFacesContext()
	{
		return FacesContext.getCurrentInstance();
	}
	protected HttpServletRequest getRequest()
	{
		return RequestUtil.getRequest();
	}
	protected ServletContext getServletContext() {
        return RequestUtil.getServletContext();
    }
	public String getAppUrl() {
		return RequestUtil.getAppURL(getRequest());
	}
	public Locale getLocale()
	{
		return RequestUtil.getLocale();
	}
}
