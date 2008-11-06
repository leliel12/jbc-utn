package utn.frc.dlc.barg.web.ctrl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.HttpJspPage;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class Pepe extends TagSupport {
	
	public int doStartTag() throws javax.servlet.jsp.JspException {
		HttpServletRequest 	req;
   		Locale 				locale;
   		HttpJspPage  		g;
   		DateFormat			df;
   		String				date;
   		javax.servlet.jsp.JspWriter			out;
   				
   		req = ( HttpServletRequest )pageContext.getRequest();
   		locale = req.getLocale();
   		df = SimpleDateFormat.getDateInstance( 
   		        SimpleDateFormat.FULL,locale );
   		date = df.format( new java.util.Date() );
   				
   		try {
     		out = pageContext.getOut();
     		out.print( date );
   		} catch( IOException ioe ) {
     		throw new JspException( "I/O Error : " + ioe.getMessage() );
   		}//end try/catch

    	return Tag.SKIP_BODY;

  	}//end doStartTag()	
	
}
