package utn.frc.dlc.barg.web.ctrl;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utn.frc.dlc.barg.db.DBPersona;
import utn.frc.dlc.javalib.base.Persona;
import utn.frc.dlc.javalib.db.SqlManager;

/**
 * Servlet implementation class for Servlet: Ctrlr
 *
 */
 public class Ctrlr extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Ctrlr() {
		super();
	}   	

	
	private String personas(HttpServletRequest request, HttpServletResponse response, String type) {
		String dest = null;
		SqlManager sql = (SqlManager) request.getSession().getAttribute("sql");
		ResultSet rs = null;
		List<Persona> personas = new ArrayList<Persona>();
		int idPersona = 0;
		Persona p = null;
		try {
			sql.connect();
			rs = sql.executeQuery("SELECT p.idpersona " +
					"FROM persona p " +
					"ORDER BY p.apellido, p.nombre");
			if (rs.first()) do {
				idPersona = rs.getInt("idpersona");
				p = DBPersona.loadDB(sql, idPersona);
				personas.add(p);
			} while (rs.next());
			request.getSession().setAttribute("personas", personas);
			dest = "/" + type + "/Personas" + type + ".jsp";
			//dest = "/Personas" + type + ".faces";
			sql.close();
		} catch (Exception e) {
			System.out.println("Ctrlr Error: " + e.getMessage());
			dest = "Error.jsp";
		}
		return dest;
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp;
		ServletContext app = this.getServletContext();
		String dest = null;
		
		String origen = (String) request.getParameter("origen");
		String action = null;
		
		if (origen!=null) {
			if (origen.equals("MenuScriptlet.jsp")) {
				action = request.getParameter("action");
				if (action.equals("sucursales")) dest = "/Scriptlet/SucursalesScriptlet.jsp";
				else if (action.equals("personal")) dest = "/Scriptlet/PersonalScriptlet.jsp";
				else if (action.equals("clientes")) dest = "/Scriptlet/ClientesScriptlet.jsp";
				//else if (action.equals("personas")) dest = "/Scriptlet/PersonasScriptlet.jsp";
				else if (action.equals("personas")) dest = personas(request, response, "Scriptlet");
//			} else if (origen.equals("MenuJSTL.jsp")) {
//				action = request.getParameter("action");
//				if (action.equals("sucursales")) dest = "/JSTL/SucursalesJSTL.jsp";
//				else if (action.equals("personal")) dest = "/JSTL/PersonalJSTL.jsp";
//				else if (action.equals("clientes")) dest = "/JSTL/ClientesJSTL.jsp";
//				else if (action.equals("personas")) dest = personas(request, response, "JSTL");
			} else if (origen.equals("MenuJSTL.jsp")) {
				action = request.getParameter("action");
				if (action.equals("sucursales")) dest = "/SucursalesJSF.jsp";
				else if (action.equals("personal")) dest = "/PersonalJSF.jsp";
				else if (action.equals("clientes")) dest = "/ClientesJSF.jsp";
				else if (action.equals("personas")) dest = personas(request, response, "JSF");
			}
		}
		if (dest==null) dest = "Error.jsp"; 

		disp = app.getRequestDispatcher(dest);
		disp.forward(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}   	  	  
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}   
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	} 
}