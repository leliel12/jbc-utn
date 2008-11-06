package com.itc;

import com.itc.modelo.Carrito;

import com.itc.modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Enumeration;
import java.util.HashMap;

import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;

public class ServletCarrito extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    Vector vector = new Vector();
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
        execute(request, response);     
    }

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
       
    execute(request, response);     
    }
    
    public void execute(HttpServletRequest request,
               HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
    
    HttpSession session = request.getSession();// obtengo la session 
    String s = session.getId();                 // la id de session es igual al id que se genero
    // ver la salida que genera con el servletprincipal
    System.out.println("La id es                   :" + s);
    Carrito car = (Carrito)session.getAttribute("carrito");
   
   // obtengo el objeto usuario que se encuentra en el objeto session
    Usuario u= (Usuario)session.getAttribute("usuario");
    String nom = (String)session.getAttribute("mm");
    
    RequestDispatcher rd ;
    int cant = 0;
    
    if (car==null){
        car = new Carrito();
        cargarCarrito("uno");
    }
    
      rd = this.getServletContext().getRequestDispatcher("/Carrito.jsp");
      request.setAttribute("carrito", vector);
      request.setAttribute("user", u.getNombre());
      System.out.println("el usuario es SC:" + u.getNombre());
      rd.forward(request, response);
    
  }
  /**
     * Metodo para cargar productos
     * @param producto
     */
  private void cargarCarrito(String producto){
      
      vector.add(producto);
      
      
  }
}
