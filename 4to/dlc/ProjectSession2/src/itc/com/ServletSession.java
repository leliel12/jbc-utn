package itc.com;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletSession extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    execute(request, response);
    
}

public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
     execute(request, response);
 }
 
public void execute(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         
         String title = "Ejemplo de sesiones";
         HttpSession session = request.getSession(true);
       
            
         session.setMaxInactiveInterval(20); // seteo el tiempo de inactividad de la sesion en 20 seg
       
         String heading="";
         Integer accessCount =
         (Integer)session.getAttribute("accessCount");
         if (accessCount == null) {
                 accessCount = new Integer(0);
                 heading = "Bienvenido";
         } else {
             heading = "Bienvenido nuevamente";
             accessCount = new Integer(accessCount.intValue() + 1);
         }
         session.setAttribute("accessCount", accessCount);
         out.println(
             "<BODY BGCOLOR=\"#FDF5E6\">\n" +
             "<H1 ALIGN=\"CENTER\">" + heading + "</H1>\n" +
             "<H2>Informacion acerca de la sesion:</H2>\n" +
             "<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
             "<TR BGCOLOR=\"#FFAD00\">\n" +
             " <TH>Tipo de Informacion<TH>Valor\n" +
             "<TR>\n" +
             " <TD>ID\n" +
             " <TD>" + session.getId() + "\n" +
             "<TR>\n" +
             " <TD>creacion\n" +
             " <TD>" +
         new Date(session.getCreationTime()) + "\n" +
             "<TR>\n" +
             " <TD>Ultimo acceso\n" +
             " <TD>" +
         new Date(session.getLastAccessedTime()) + "\n" +
             "<TR>\n" +
             " <TD>Numero de accesos\n" +
             " <TD>" + accessCount + "\n" +
             "</TABLE>\n" +
             "</BODY></HTML>");
     }
}