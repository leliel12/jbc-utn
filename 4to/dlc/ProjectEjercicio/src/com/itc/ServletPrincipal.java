package com.itc;

import com.itc.modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class ServletPrincipal extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

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
   
    HttpSession session = request.getSession(true);
    RequestDispatcher rd ;
    Usuario user = (Usuario) session.getAttribute("usuario");
    
    String action="";
    
       String u = request.getParameter("user");
       String p = request.getParameter("pass");
       
       action = request.getParameter("formAction");
       
       if (action==null)action="";
       
       System.out.println("action:" + action + " user " + user);
       
    if (user==null ){
    
        System.out.println("action:" + action);
        
        if (action.equals("logueame")){
        
            System.out.println("creo el usuario " + action);
            System.out.println("nombre el usuario " + u);
            System.out.println("password el usuario " + p);
            user = new Usuario(u,p,1);
            
            session.setAttribute("usuario", user); // agrego el usuaior a la sesion.
            session.setAttribute("mm", user.getNombre());
            
            String pp = (String)session.getAttribute("mm");
     
             
            rd = this.getServletContext().getRequestDispatcher("/Carrito.jsp");
            request.setAttribute("user", user.getNombre());
            rd.forward(request, response);
        }
        // redirecciono a la pagina de login   
        rd = this.getServletContext().getRequestDispatcher("/login.html") ;
        rd.forward(request, response);
    }else{
        // redirecciono a la pagina de compras "carrito.jsp"
        rd = this.getServletContext().getRequestDispatcher("/Carrito.jsp");
        request.setAttribute("user", user.getNombre());
        rd.forward(request, response);
      }
      
       String s = session.getId();
       System.out.println("La id es servlet principal :" + s);
   }
   
    
   
}// fin del servlet
