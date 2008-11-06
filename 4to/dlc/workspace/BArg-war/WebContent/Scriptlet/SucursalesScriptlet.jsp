<%@ page
		language="java"
		import="utn.frc.dlc.javalib.base.Persona"
		import="utn.frc.dlc.javalib.base.Fecha"
		import="java.util.Date"
		contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"
%>
<%-- tpl:insert page="/theme/A_blue.htpl" --%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href="/BArg-war/theme/blue.css" type="text/css">
<%-- tpl:put name="headarea" --%>
<title>Sucursales (Scriptlet)</title>
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

         <%
         	Persona persona = (Persona) session.getAttribute("persona");
         	Fecha fecha = new Fecha(new Date());
         %>

		<table width="800" border="0">
			<tbody>
				<tr height="20">
					<td width="400" align="left">
						<%
							out.println("Bienvenido: " + persona.getNombre() + " " + persona.getApellido());
						%>
					</td>
					<td width="400" align="right">
						<%=fecha.toString("ES", "dd 'de' MMMM 'de' yyyy, H:mm 'hs'")%>
					</td>
				</tr>
			</tbody>
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