package com.test2.action;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.FrameWrkFullPrject.utils.FacesUtils;
import com.FrameWrkFullPrject.utils.RequestUtil;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class BasePage extends AbstractForm {

	

    protected static final String ERROR_OUTCOME = "error";
    protected static final String CANCEL_OUTCOME = "cancel";
    protected static final String SUCCESS_OUTCOME = "success";
    protected static final String EXIT_OUTCOME = "exit";
    
	protected HttpServletResponse getResponse()
	{
		return (HttpServletResponse)getFacesContext().getExternalContext().getResponse();
	}
	
	public String getText(String key) {
        String message;

        try {
            message = getBundle().getString(key);
        } 
        catch (java.util.MissingResourceException mre) {
        	mre.printStackTrace();
            System.out.println("Missing key for '" + key + "'");
            return "???" + key + "???";
        }

        return message;
    }

    public String getText(String key, Object arg) {
        if (arg == null) {
            return getText(key);
        }

        MessageFormat form = new MessageFormat(getBundle().getString(key));

        if (arg instanceof String) {
            return form.format(new Object[] { arg });
        } else if (arg instanceof Object[]) {
            return form.format(arg);
        } else {
        	System.out.println("arg '" + arg + "' not String or Object[]");

            return "";
        }
    }
    
    @SuppressWarnings("unchecked")
    protected void addMessageOnly(String key, Object arg) {
        FacesUtils.addInfoMessageOnly(getText(key, arg));
    }

    protected void addMessageOnly(String key) {
        addMessage(key, null);
    }
    
	@SuppressWarnings("unchecked")
    protected void addMessage(String key, Object arg) {
        FacesUtils.addInfoMessage(getText(key, arg));
    }

    protected void addMessage(String key) {
        addMessage(key, null);
    }

    @SuppressWarnings("unchecked")
    protected void addError(String key, Object arg) {
        FacesUtils.addErrorMessage(getText(key, arg));
    }

    protected void addError(String key) {
        addError(key, null);
    }

    @SuppressWarnings("unchecked")
    protected void addWarningMessage(String key, Object arg) {
        FacesUtils.addWarningMessage(getText(key, arg));
    }

    protected void addWarningMessage(String key) {
        addWarningMessage(key, null);
    }
    
    public ResourceBundle getBundle() {
        return RequestUtil.getBundle();
    }
    
    public String getBundleName() {
        return RequestUtil.getBundleName();
    }
    
  
    
    protected HttpSession getSession() {
        return getRequest().getSession();
    }
    
    public void sendRedirect(String url){
        try {
            getFacesContext().getExternalContext().redirect(getAppUrl() + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendBaseRedirect(String url){
        try {
            getFacesContext().getExternalContext().redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getAdminUser() {
    	
        return RequestUtil.getAdminUser();
    }
    
    public String getCurrentUser() {
        return RequestUtil.getCurrentUser();
    }

}
