<%@ page
		language="java"
		contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"
%>
<%@ taglib
		uri="http://java.sun.com/jsp/jstl/core"
		prefix="c"
%>
<%@ taglib
		uri="../WEB-INF/base.tld"
		prefix="bt"
%>
<%-- tpl:insert page="/theme/A_blue.htpl" --%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href="/BArg-war/theme/blue.css" type="text/css">
<%-- tpl:put name="headarea" --%>
<title>Personas (JSTL)</title>
<%-- /tpl:put --%>
</head>
<body>
<table width="760" cellspacing="0" cellpadding="0" border="0">
   <tbody>
      <tr>
         <td valign="top">
         <table class="header" cellspacing="0" cellpadding="0" border="0" width="100%">
            <tbody>
               <tr>
                  <td width="150"><img border="0" width="150" height="55" alt="Company's LOGO" src="/BArg-war/theme/logo_blue.gif"></td>
                  <td></td>
               </tr>
            </tbody>
         </table>
         </td>
      </tr>
      <tr>
         <td valign="top" class="nav_head" height="20"></td>
      </tr>
      <tr class="content-area">
         <td valign="top" height="350"><%-- tpl:put name="bodyarea" --%>

		<jsp:include flush="true" page="CabeceraJSTL.jsp"></jsp:include>
		<br>

		<!-- <bt:showAttribute object="${persona}" attribute="idpersona" /> -->

		<table width="800" border="1">
			<tr>
				<th>Nombre</th><th>Documento</th>
			</tr>
			<c:set var="index" value="-1" scope="page"></c:set>
			<c:forEach var="p" items="${personas}">
				<tr>
					<td>
						<c:if test='${privilegios["RD_PERSONAS"]=="S"}'>
							<c:set var="index" value="${index+1}"></c:set>
							<bt:setAttribute object="${p}" attribute="idpersona" var="pageidpersona"/>
<!-- ------------------------------------------------- -->
<!-- Comentar/descomentar una de las siguientes líneas -->
<!-- ------------------------------------------------- -->
<!-- 						<a href='Ctrlr?origen=PersonasJSTL.jsp&action=show&index=${index}'> -->
<!-- ------------------------------------------------- -->
							<a href='Ctrlr?origen=PersonasJSTL.jsp&action=show&id=${pageidpersona}'>
<!-- ------------------------------------------------- -->
						</c:if>
						${p.apellido}, ${p.nombre}
						<c:if test='${privilegios["RD_PERSONAS"]=="S"}'>
							</a>
						</c:if>
					</td>
					<td> (${p.documento.tipo}) ${p.documento.nro} </td>
				</tr>
			</c:forEach>
		</table>



<%-- /tpl:put --%></td>
      </tr>
      <tr>
         <td valign="top" height="20" class="footer"></td>
      </tr>
   </tbody>
</table>
</body>
</html><%-- /tpl:insert --%>