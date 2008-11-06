<%@ page
		language="java"
		import="utn.frc.dlc.javalib.base.Fecha"
		import="java.util.Date"
		contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"
%>
<%@ taglib
		uri="http://java.sun.com/jsp/jstl/core"
		prefix="c"
%>

         <%
         	Fecha fecha = new Fecha(new Date());
         %>

		<table width="800" border="0">
			<tbody>
				<tr>
					<td width="400" align="left">
						<c:out value="Bienvenido ${persona.nombre} ${persona.apellido}"></c:out>
					</td>
					<td width="400" align="right">
						<%=fecha.toString("ES", "dd 'de' MMMM yyyy, H:mm 'hs'")%>
					</td>
				</tr>
			</tbody>
		</table>
