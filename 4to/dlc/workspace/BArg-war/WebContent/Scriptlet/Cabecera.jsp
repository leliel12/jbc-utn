<%@ page
		language="java"
		import="utn.frc.dlc.javalib.base.Persona"
		import="utn.frc.dlc.javalib.base.Fecha"
		import="java.util.Date"
		contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"
%>


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
				<tr height="20">
				</tr>
			</tbody>
		</table>


