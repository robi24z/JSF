package com.FrameWrkFullPrject.utils;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/uploadStatusServlet")
public class UploadStatusServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init(final ServletConfig config) throws ServletException
	{
		System.out.println("statusssss initialized:");
    	super.init();
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String retdata="Loading...";
		if(request.getSession().getAttribute("statusUp")!=null)
			retdata=request.getSession().getAttribute("statusUp").toString();
		response.getWriter().write(retdata);
                        
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String retdata="";
		
		if(request.getSession().getAttribute("Message")!=null)
			retdata=request.getSession().getAttribute("Message").toString();
		System.out.println("=================doPost==============="+retdata);
		request.removeAttribute("Message");
		request.removeAttribute("statusUp");
		response.getWriter().write(retdata);
	}

}
