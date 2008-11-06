<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"
language='java' info='Mi primera página en JSP'
import='java.util.*'
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Mi primeraJSP</title>
  </head>
  <body>
        <center><p> Ejemplo JSP </p></center>
        <p>
        JavaServer Pages (JSP) combinan HTML con fragmentos de Java para producir páginas web dinámicas.<br>
        Una página JSP es un archivo de texto simple que consiste en contenido XML o HTML con validaciones en JavaScript y con elementos JSP, escrito en cualquier editor de texto y almacenado con extensión jsp.<br>

        </p>
        
        <center> <p><b><font face="Verdana" size="6">
             Objetos que componen una p&aacute;gina JSP 
          </font> </b> </p></center><br>
        
        <p><b> Directivas:</b><br>
        <b><i>Una directiva de JSP es un fragmaneto de 'codigo' que proporciona la información al motor de JSP para la página que la pide.</b></i>
        <br>
        <b><i> Su sintaxis general es:    @ directiva {atributo ="valor"} </i></b><br>

                      [ language="java"] <br>
                      [ extends="package.class"] <br>
                      [ import= "{ package.class|package.*}, ..." ] <br>
                      [ session="true|false"] <br>
                      [ buffer="none|8kb|sizekb"] <br>
                      [ autoFlush="true|false"] <br>
                      [ isThreadSafe="true|false"] <br>
                      [ info="text"] <br>
                      [ errorPage="URLrelativa"] <br>
                      [ contentType="mimeType[ ;charset=characterSet]" | <br>
                        "text/html; charset=ISO-8859-1"] <br>
                      [ isErrorPage="true|false"]<br>
</p>
<br><br>

<p><b>Scripts de JSP </b><br>

Los Scripts son bloques de código Java residentes entre los tags "< % y % >"
<% // codigo Java
    java.util.HashMap hash = new java.util.HashMap();
 %>.

</p>
<br><br>
<p><b>Expresiones de JSP:</b></p>
<br>
Fecha Actual: <%= new java.util.Date() %>
<br>
<p><b>Accciones</b></p>
<p> Las acciones sirven para controlar el motor de servlets del servidor mediante  construcciones de sintaxis XML. 
Entre las accriones que podremos realizar se encuentran: <br>
        insertar un fichero dinámicamente, <br>
        reutilizar componentes JavaBeans, <br>
        reenviar al usuario a otra página, o <br>
        generar HTML para el plug-in Java. <br>
</p>
<b><i> jsp:include: Incluye un fichero en el momento de petición de esta página. </i></b>
<br>
Su sintaxis es:<br>
        jsp:include page="pagina.html" flush="true" <br>
        
<jsp:include page="Pagina.html" flush="true" />
<br><br>
<b><i> jsp:useBean: Encuentra o ejemplariza un JavaBean para su uso en una página JSP. </i></b>
<br>
La sintaxis es la siguiente:<br>
<p>
    jsp:useBean id="now" class="java.util.Date" <br>
    jsp:useBean id="ClienteBean" scope="page" class="com.itc.ClienteBean" <br>
</p>

   
</body>
</html>