package com.FrameWrkFullPrject.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FacesUtils {

    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance()
                                            .getExternalContext().getContext();
    }



    public static String getRequestParameter(String name) {
        return (String) FacesContext.getCurrentInstance().getExternalContext()
                                    .getRequestParameterMap().get(name);
    }

    public static void addInfoMessage(String msg) {
        addInfoMessage(null, msg);
    }

    public static void addInfoMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId,
                                                     new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                      msg, msg));
    }


    public static void addInfoMessageOnly(String msg) {
        addInfoMessage(null, msg);
    }


    public static void addInfoMessageOnly(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId,
                                                     new FacesMessage(msg));
    }

    public static void addErrorMessage(String msg) {
        addErrorMessage(null, msg);
    }


    public static void addErrorMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId,
                                                     new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                      msg, msg));
    }

    public static void addWarningMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId,
                                                     new FacesMessage(FacesMessage.SEVERITY_WARN,
                                                                      msg, msg));
    }

    public static void addWarningMessage(String msg) {
        addWarningMessage(null, msg);
    }







    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                                                .getExternalContext()
                                                .getRequest();
    }
    
    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance()
                                                .getExternalContext()
                                                .getResponse();
    }
    
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                                                .getExternalContext()
                                                .getSession(false);
    }


    public static boolean hasErrors() {
        FacesMessage.Severity maximumSeverity = FacesContext.getCurrentInstance().getMaximumSeverity();
        return maximumSeverity != null && maximumSeverity.getOrdinal() >= FacesMessage.SEVERITY_ERROR.getOrdinal();
    }
}
