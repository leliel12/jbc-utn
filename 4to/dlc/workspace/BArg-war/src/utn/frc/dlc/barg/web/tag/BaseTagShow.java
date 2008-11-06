package utn.frc.dlc.barg.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import utn.frc.dlc.javalib.base.BaseClass;

public class BaseTagShow extends TagSupport {
	BaseClass b = null;
	String a = null;
	
	public void setObject(BaseClass object) {
		this.b = object;
	}
	
	public void setAttribute(String attribute) {
		this.a = attribute;
	}
	
	public int doStartTag() throws javax.servlet.jsp.JspException {
   		JspWriter			out;
   				
   		Object o = null;
   				
   		if (this.b!=null) try {
			o = this.b.getAttribute(this.a);
     		out = pageContext.getOut();
     		out.print(o);
   		} catch (IOException ioe) {
     		throw new JspException("I/O Error: " + ioe.getMessage());
   		}
    	return Tag.SKIP_BODY;
  	}	
}
