package com.FrameWrkFullPrject.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import com.test2.action.BasePage;


@FacesValidator("valid")
public class CheckValidation extends BasePage implements Validator 
{
	 FacesMessage message=null;
	
	@Override
	public void validate(FacesContext context, UIComponent uiComp,
			Object value) throws ValidatorException {

		if(uiComp.getAttributes().get("id").equals("uploadFileEoh") || uiComp.getAttributes().get("id").equals("uploadFileKpw") || 
				uiComp.getAttributes().get("id").equals("uploadFileSwr"))
		{
			Part part = (Part) value;
	
			String fileName = getFileName(part);
			//System.out.println("----- validator fileName: " + fileName);
			if(fileName.length() == 0 ) {
				message=new FacesMessage("Error: File name is invalid !!");
			} else if (fileName.length() > 90) {
				message=new FacesMessage("Error: File name is too long !!");
			}
			else if(!fileName.endsWith(".xls") && !fileName.endsWith(".XLS") && !fileName.endsWith(".xlsx")) {
				System.out.println("invalid file");
				message=new FacesMessage("Invalid File !!");
			}
			else
			{
				try
				{
					String dtstr = fileName.substring(fileName.lastIndexOf(" ")+1, fileName.lastIndexOf("."));
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
					SimpleDateFormat sdf1 = new SimpleDateFormat("MM.dd.yy");
					Date dateval = sdf1.parse(dtstr);
					String datestr = sdf.format(dateval);
					//System.out.println("dateval=========="+dateval);
					
					Date curDate = new Date();
					String curDatestr=sdf.format(curDate);
					if(curDate.before(dateval))
					{
						message=new FacesMessage("Select a Date before or Same as "+datestr);
					}
					/*else
					{
						String retval = getWeeklyDataService().validateDate(datestr,curDatestr);
						if(retval!=null && !retval.equals(""))
						{
							returnMessage(retval);
						}
					}*/
				}
				catch(Exception e)
				{
					message=new FacesMessage("Invalid File !!");
				}
			}
			if (message!=null && !message.getDetail().isEmpty())
            {
				System.out.println("----ifff");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message );
            }
		}
	}

	// Extract file name from content-disposition header of file part
	private String getFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");
		//System.out.println("----- validator partHeader: " + partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return "";
	}
	
	/*private void returnMessage(String msg)
	{
		FacesMessage message=new FacesMessage();
		message.setDetail(msg);
		message.setSummary(msg);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		throw new ValidatorException(message);
	}*/
}
