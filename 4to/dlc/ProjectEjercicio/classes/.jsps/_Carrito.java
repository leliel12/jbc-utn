
import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;
import java.util.*;


public class _Carrito extends com.orionserver.http.OrionHttpJspPage {

  public String getServletInfo() {
    return( "Mi primera página en JSP");
  }


  // ** Begin Declarations


  // ** End Declarations

  public void _jspService(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException, ServletException {

    response.setContentType( "text/html;charset=windows-1252");
    /* set up the intrinsic variables using the pageContext goober:
    ** session = HttpSession
    ** application = ServletContext
    ** out = JspWriter
    ** page = this
    ** config = ServletConfig
    ** all session/app beans declared in globals.jsa
    */
    PageContext pageContext = JspFactory.getDefaultFactory().getPageContext( this, request, response, null, true, JspWriter.DEFAULT_BUFFER, true);
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    int __jsp_tag_starteval;
    ServletContext application = pageContext.getServletContext();
    JspWriter out = pageContext.getOut();
    _Carrito page = this;
    ServletConfig config = pageContext.getServletConfig();
    javax.servlet.jsp.el.VariableResolver __ojsp_varRes = (VariableResolver)new OracleVariableResolverImpl(pageContext);

    try {


      out.write(__oracle_jsp_text[0]);
      out.write(__oracle_jsp_text[1]);
      out.print( request.getParameter("user") );
      out.write(__oracle_jsp_text[2]);
       
          try{
          java.util.Vector vector= (java.util.Vector)
          request.getAttribute("carrito");
          for(int i=0; i < vector.size(); i++){
                  String p = (String)vector.get(i); 
      out.write(__oracle_jsp_text[3]);
      out.print(p); 
      out.write(__oracle_jsp_text[4]);
      
          }
          } catch(Exception ex){}
      out.write(__oracle_jsp_text[5]);

    }
    catch (Throwable e) {
      if (!(e instanceof javax.servlet.jsp.SkipPageException)){
        try {
          if (out != null) out.clear();
        }
        catch (Exception clearException) {
        }
        pageContext.handlePageException(e);
      }
    }
    finally {
      OracleJspRuntime.extraHandlePCFinally(pageContext, true);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
    }

  }
  private static final char __oracle_jsp_text[][]=new char[6][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n\"http://www.w3.org/TR/html4/loose.dtd\">\n".toCharArray();
    __oracle_jsp_text[1] = 
    "\n<html>\n  <head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\"/>\n    <title>Carrito</title>\n  </head>\n  <body>\n  Bienvenido:\n  ".toCharArray();
    __oracle_jsp_text[2] = 
    "\n   \n  <form name=\"carritoForm\" action=\"servletcarrito\" method=\"post\">\n      <p>\n        <select size=\"10\" name=\"productos\">\n          <option value=\"monitor\">\n            Monitor LCD 22\n          </option>\n          <option value=\"ups\">\n            UPS 10 min\n          </option>\n          <option value=\"grabadora\">\n            Grabadora Lite on\n          </option>\n        </select>\n      </p>\n      <p><input type=\"submit\" name=\"comprar\" value=\"Comprar\"/>\n      </p>\n      <p>\n        &nbsp;\n      </p>\n    </form>\n    Mis compras: ".toCharArray();
    __oracle_jsp_text[3] = 
    "\n            ".toCharArray();
    __oracle_jsp_text[4] = 
    "\n            ".toCharArray();
    __oracle_jsp_text[5] = 
    "\n    <form name=\"finalizarCompra\" action=\"servletcarrito\" method=\"post\">\n      <input type=\"submit\" name=\"FinalizarCompra\" value=\"Finalizar Compra\"/>\n    </form></body>\n</html>".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
