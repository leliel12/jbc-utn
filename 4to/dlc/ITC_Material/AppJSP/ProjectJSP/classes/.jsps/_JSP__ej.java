
import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import oracle.jsp.el.*;
import javax.servlet.jsp.el.*;
import java.util.*;


public class _JSP__ej extends com.orionserver.http.OrionHttpJspPage {

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
    _JSP__ej page = this;
    ServletConfig config = pageContext.getServletConfig();
    javax.servlet.jsp.el.VariableResolver __ojsp_varRes = (VariableResolver)new OracleVariableResolverImpl(pageContext);

    com.evermind.server.http.JspCommonExtraWriter __ojsp_s_out = (com.evermind.server.http.JspCommonExtraWriter) out;
    try {


      __ojsp_s_out.write(__oracle_jsp_text[0]);
      __ojsp_s_out.write(__oracle_jsp_text[1]);
       // codigo Java
          java.util.HashMap hash = new java.util.HashMap();
       
      __ojsp_s_out.write(__oracle_jsp_text[2]);
      out.print( new java.util.Date() );
      __ojsp_s_out.write(__oracle_jsp_text[3]);
      {
        String __url=OracleJspRuntime.toStr("Pagina.html");
        // Include 
        pageContext.include( __url,true);
        if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) return;
      }

      __ojsp_s_out.write(__oracle_jsp_text[4]);

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
  private static final byte __oracle_jsp_text[][]=new byte[5][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\r\n\"http://www.w3.org/TR/html4/loose.dtd\">\r\n".getBytes("Cp1252");
    __oracle_jsp_text[1] = 
    "\r\n<html>\r\n  <head>\r\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\"/>\r\n    <title>Mi primeraJSP</title>\r\n  </head>\r\n  <body>\r\n        <center><p> Ejemplo JSP </p></center>\r\n        <p>\r\n        JavaServer Pages (JSP) combinan HTML con fragmentos de Java para producir páginas web dinámicas.<br>\r\n        Una página JSP es un archivo de texto simple que consiste en contenido XML o HTML con validaciones en JavaScript y con elementos JSP, escrito en cualquier editor de texto y almacenado con extensión jsp.<br>\r\n\r\n        </p>\r\n        \r\n        <center> <p><b><font face=\"Verdana\" size=\"6\">\r\n             Objetos que componen una p&aacute;gina JSP \r\n          </font> </b> </p></center><br>\r\n        \r\n        <p><b> Directivas:</b><br>\r\n        <b><i>Una directiva de JSP es un fragmaneto de 'codigo' que proporciona la información al motor de JSP para la página que la pide.</b></i>\r\n        <br>\r\n        <b><i> Su sintaxis general es:    @ directiva {atributo =\"valor\"} </i></b><br>\r\n\r\n                      [ language=\"java\"] <br>\r\n                      [ extends=\"package.class\"] <br>\r\n                      [ import= \"{ package.class|package.*}, ...\" ] <br>\r\n                      [ session=\"true|false\"] <br>\r\n                      [ buffer=\"none|8kb|sizekb\"] <br>\r\n                      [ autoFlush=\"true|false\"] <br>\r\n                      [ isThreadSafe=\"true|false\"] <br>\r\n                      [ info=\"text\"] <br>\r\n                      [ errorPage=\"URLrelativa\"] <br>\r\n                      [ contentType=\"mimeType[ ;charset=characterSet]\" | <br>\r\n                        \"text/html; charset=ISO-8859-1\"] <br>\r\n                      [ isErrorPage=\"true|false\"]<br>\r\n</p>\r\n<br><br>\r\n\r\n<p><b>Scripts de JSP </b><br>\r\n\r\nLos Scripts son bloques de código Java residentes entre los tags \"< % y % >\"\r\n".getBytes("Cp1252");
    __oracle_jsp_text[2] = 
    ".\r\n\r\n</p>\r\n<br><br>\r\n<p><b>Expresiones de JSP:</b></p>\r\n<br>\r\nFecha Actual: ".getBytes("Cp1252");
    __oracle_jsp_text[3] = 
    "\r\n<br>\r\n<p><b>Accciones</b></p>\r\n<p> Las acciones sirven para controlar el motor de servlets del servidor mediante  construcciones de sintaxis XML. \r\nEntre las accriones que podremos realizar se encuentran: <br>\r\n        insertar un fichero dinámicamente, <br>\r\n        reutilizar componentes JavaBeans, <br>\r\n        reenviar al usuario a otra página, o <br>\r\n        generar HTML para el plug-in Java. <br>\r\n</p>\r\n<b><i> jsp:include: Incluye un fichero en el momento de petición de esta página. </i></b>\r\n<br>\r\nSu sintaxis es:<br>\r\n        jsp:include page=\"pagina.html\" flush=\"true\" <br>\r\n        \r\n".getBytes("Cp1252");
    __oracle_jsp_text[4] = 
    "\r\n<br><br>\r\n<b><i> jsp:useBean: Encuentra o ejemplariza un JavaBean para su uso en una página JSP. </i></b>\r\n<br>\r\nLa sintaxis es la siguiente:<br>\r\n<p>\r\n    jsp:useBean id=\"now\" class=\"java.util.Date\" <br>\r\n    jsp:useBean id=\"ClienteBean\" scope=\"page\" class=\"com.itc.ClienteBean\" <br>\r\n</p>\r\n\r\n   \r\n</body>\r\n</html>".getBytes("Cp1252");
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
