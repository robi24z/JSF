package com.FrameWrkFullPrject.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	
	private RequestUtil()
	{
		
	}

	public static HttpServletRequest getRequest()
	{
		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	

	public static ServletContext getServletContext() {
	        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	    }
	    
	 public static ResourceBundle getBundle() {
	        ClassLoader classLoader =  Thread.currentThread().getContextClassLoader();
	        return ResourceBundle.getBundle(getBundleName(), getRequest().getLocale(), classLoader);
	    }

	    public static String getBundleName() {
	        return FacesContext.getCurrentInstance().getApplication().getMessageBundle();
	    } 
	    
	    public static String getAppURL(HttpServletRequest request) {
	        StringBuffer url = new StringBuffer();
	        int port = request.getServerPort();
	        if (port < 0) {
	            port = 80; // Work around java.net.URL bug
	        }
	        String scheme = request.getScheme();
	        url.append(scheme);
	        url.append("://");
	        url.append(request.getServerName());
	        if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
	            url.append(':');
	            url.append(port);
	        }
	        url.append(request.getContextPath());
	        return url.toString();
	    }
	    
	    
	    public static String getRequestUrl()
		{
	    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    	 StringBuffer url = new StringBuffer();
		        int port = request.getServerPort();
		        if (port < 0) {
		            port = 80; // Work around java.net.URL bug
		        }
		        String scheme = request.getScheme();
		        url.append(scheme);
		        url.append("://");
		        url.append(request.getServerName());
		        if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
		            url.append(':');
		            url.append(port);
		        }
		        url.append(request.getContextPath());
		        return url.toString();
	    	
		}
	    
	    
	    public static String getAdminUser() 
	    {
	    	
	    	String aduser = getRequest().getSession().getAttribute("aduserid") == null ? "":getRequest().getSession().getAttribute("aduserid").toString();
	    	if(aduser == null || aduser.equals(""))
	    	{
	    		return "unAuthAdmin";
	    	}
	    	return aduser;
	    }
	    
	    public static String getCurrentUser() 
	    {
	    	
	    	String loguser = getRequest().getSession().getAttribute("userId") == null ? "":getRequest().getSession().getAttribute("userId").toString();
	    	
	    	if(loguser == null || loguser.equals(""))
	    	{
	    		return "unAuthUser";
	    	}
	    	return loguser;
	    }
	    public static Locale getLocale()
	    {
	    	Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
			return locale;
	    }
}
