package utn.frc.dlc.barg.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import utn.frc.dlc.javalib.base.BaseClass;

public class BaseTagSet extends TagSupport {
	BaseClass b = null;
	String a = null, var = null;
	
	public void setObject(BaseClass object) {
		this.b = object;
	}
	
	public void setAttribute(String attribute) {
		this.a = attribute;
	}
	
	public void setVar(String var) {
		this.var = var;
	}
	
	public int doStartTag() throws javax.servlet.jsp.JspException {
   		Object o = null;
   				
   		if (this.b!=null && this.a!=null && this.var!=null) try {
			o = this.b.getAttribute(this.a);
			pageContext.setAttribute(var, o);
   		} catch (Exception e) {
     		throw new JspException("I/O Error: " + e.getMessage());
   		}
    	return Tag.SKIP_BODY;
  	}	
}
