<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"

language='java' info='Mi primera página en JSP'
import='java.util.*'
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Carrito</title>
  </head>
  <body>
  Bienvenido:
  <%= request.getParameter("user") %>
   
  <form name="carritoForm" action="servletcarrito" method="post">
      <p>
        <select size="10" name="productos">
          <option value="monitor">
            Monitor LCD 22
          </option>
          <option value="ups">
            UPS 10 min
          </option>
          <option value="grabadora">
            Grabadora Lite on
          </option>
        </select>
      </p>
      <p><input type="submit" name="comprar" value="Comprar"/>
      </p>
      <p>
        &nbsp;
      </p>
    </form>
    Mis compras: <% 
    try{
    java.util.Vector vector= (java.util.Vector)
    request.getAttribute("carrito");
    for(int i=0; i < vector.size(); i++){
            String p = (String)vector.get(i); %>
            <%out.print(p); %>
            <%
    }
    } catch(Exception ex){}%>
    <form name="finalizarCompra" action="servletcarrito" method="post">
      <input type="submit" name="FinalizarCompra" value="Finalizar Compra"/>
    </form></body>
</html>