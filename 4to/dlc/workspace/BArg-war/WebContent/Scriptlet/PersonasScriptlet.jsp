<%@ page
		language="java"
		import="utn.frc.dlc.javalib.base.Persona"
		import="utn.frc.dlc.javalib.web.html.table.Table"
		import="utn.frc.dlc.javalib.web.html.table.TableRow"
		import="utn.frc.dlc.javalib.web.html.table.ColumnTitle"
		import="utn.frc.dlc.javalib.web.html.table.TableCell"
		import="utn.frc.dlc.javalib.web.html.Link"
		import="java.util.Hashtable"
		import="java.util.List"
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
<title>Personas (Scriptlet)</title>
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

	<jsp:include flush="true" page="Cabecera.jsp"></jsp:include>

         <%
                                                      	Persona persona = (Persona) session.getAttribute("persona");
                                                      	String origen = "PersonasScriptlet.jsp";

                                                      	Hashtable privs = (Hashtable) persona.getAttribute("privilegios");
                                                      	String priv = null;
                                                      	List personas = (List) session.getAttribute("personas");

                                             	Table t = new Table();
                                             	TableRow tr = new TableRow();
                                             	ColumnTitle th;
                                             	th = new ColumnTitle();	th.setText("Nombre"); tr.add(th);
                                             	th = new ColumnTitle();	th.setText("Documento"); tr.add(th);
                                             	t.add(tr);
                                             	
                                             	if (personas!=null) {
                                             	priv = (String) privs.get("RD_PERSONAS");
                                             	boolean link = (priv!=null && priv.equals("S"));
                                             	int s = personas.size();
                                             	Persona p = null;
                                             	TableCell td = null;
                                             	Link l = null;
                                             	Integer id;
                                             	for (int i=0; i<s; i++) {
                                             	    p = (Persona) personas.get(i);
                                             	    tr = new TableRow();
                                             	    td = new TableCell();
                                             	    if (link) {
                                             	    	l = new Link();
                                             	    	l.setText(p.toString());
                                             	    	id = (Integer) p.getAttribute("idpersona");
                                             	    	l.setUrl("Ctrlr?origen="+origen+"&action=show&id=" + id);
                                             	    	td.add(l);
                                             	    } else td.setText(p.toString());
                                             	    tr.add(td);
                                             	    td = new TableCell();
                                             	    td.setText("(" + p.getDocumento().getTipo() + ") " + p.getDocumento().getNro());
                                             	    tr.add(td);
                                             	    t.add(tr);
                                             	}
                                             	}
                                             		    t.setAttribute("width", "800");
                                             		    t.setAttribute("border", "1");
                                             	out.println(t);
         %>
		

<%-- /tpl:put --%></td>
      </tr>
      <tr>
         <td valign="top" height="20" class="footer"></td>
      </tr>
   </tbody>
</table>
</body>
</html><%-- /tpl:insert --%>