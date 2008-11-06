<%-- jsf:pagecode language="java" location="/src/pagecode/PersonasJSF.java" --%>
<%-- /jsf:pagecode --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<f:view>
	<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
	<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<%-- tpl:insert page="/theme/A_blue.htpl" --%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href="/BArg-war/theme/blue.css" type="text/css">
<%-- tpl:put name="headarea" --%>
<title>PersonasJSF</title>
<meta name="GENERATOR" content="Rational Application Developer">
			<link rel="stylesheet" type="text/css" href="theme/stylesheet.css"
				title="Style">
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
						<hx:scriptCollector id="scriptCollector1">


							<jsp:include flush="true" page="/JSTL/CabeceraJSTL.jsp"></jsp:include>
							<br>
							<h:form id="form1" styleClass="form">
								<hx:dataTableEx border="0" cellpadding="2" cellspacing="0"
									columnClasses="columnClass1" headerClass="headerClass"
									footerClass="footerClass" rowClasses="rowClass1, rowClass2"
									id="tblPersonas" styleClass="dataTableEx" var="varpersonas"
									value="#{sessionScope.personas}">
									<hx:columnEx id="clNombre">
										<f:facet name="header">
											<h:outputText id="hdNombre" styleClass="outputText"
												value="Nombre"></h:outputText>
										</f:facet>
										<h:outputText id="txtNombre" styleClass="outputText"
											value="#{varpersonas.apellido}, #{varpersonas.nombre}"></h:outputText>
									</hx:columnEx>
									<hx:columnEx id="clDocumento">
										<f:facet name="header">
											<h:outputText id="hdDocumento" styleClass="outputText"
												value="Documento"></h:outputText>
										</f:facet>
										<h:outputText id="txtDocumento" styleClass="outputText"
											value="(#{varpersonas.documento.tipo}) #{varpersonas.documento.nro}"></h:outputText>
									</hx:columnEx>
								</hx:dataTableEx>
							</h:form>
							<br>
							<br>












						</hx:scriptCollector>
					<%-- /tpl:put --%></td>
      </tr>
      <tr>
         <td valign="top" height="20" class="footer"></td>
      </tr>
   </tbody>
</table>
</body>
</html><%-- /tpl:insert --%>
</f:view>