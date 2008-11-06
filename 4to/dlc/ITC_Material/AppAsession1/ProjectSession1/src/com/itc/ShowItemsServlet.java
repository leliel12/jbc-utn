package com.itc;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class ShowItemsServlet extends HttpServlet {
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
        ArrayList previousItems =
        (ArrayList)session.getAttribute("previousItems");
        
        if (previousItems == null) {
            previousItems = new ArrayList();
            session.setAttribute("previousItems", previousItems);
        }
        
        String newItem = request.getParameter("newItem");
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        String title = "Requerimientos";
        out.println("<BODY BGCOLOR=\"#FDF5E6\">\n" +
        "<H1>" + title + "</H1>");
        synchronized(previousItems) {
        if (newItem != null) {
            previousItems.add(newItem);
        }
        if (previousItems.size() == 0) {
            out.println("<I>No items</I>");
        } else {
            out.println("<UL>");
            for(int i=0; i<previousItems.size(); i++) {
                out.println("<LI>" + (String)previousItems.get(i));
            }
            out.println("</UL>");
            }
        }
        out.println("</BODY></HTML>");
        
    }
    
} // fin del servlet.
    

