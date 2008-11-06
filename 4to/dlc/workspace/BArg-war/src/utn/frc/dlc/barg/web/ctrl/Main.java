package utn.frc.dlc.barg.web.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utn.frc.dlc.barg.db.DBPersona;
import utn.frc.dlc.javalib.base.Direccion;
import utn.frc.dlc.javalib.base.Documento;
import utn.frc.dlc.javalib.base.Fecha;
import utn.frc.dlc.javalib.base.Persona;
import utn.frc.dlc.javalib.db.SqlManager;

/**
 * Servlet implementation class for Servlet: Main
 *
 */
 public class Main extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	 
	SqlManager sql = null;
	Persona persona = null;

	 
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
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
	
	private void infoOutput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<table border=\"1\">");
		out.println("<tr><th>Elemento/Variable/Parámetro</th><th>Valor</th></tr>");

		String origen = request.getParameter("origen");
		if (origen == null) origen = "desconocido";
		out.println("<tr><td>origen:</td><td>" + origen + "</td></tr>");
		
		Enumeration pnames = request.getParameterNames();
		String pn = null; String pv = null;
		while (pnames.hasMoreElements()) {
			pn = (String)pnames.nextElement();
			pv = request.getParameter(pn);
			out.println("<tr><td>Parameter::" + pn + ":</td><td>" + pv + "</td></tr>");
		}
		
		Enumeration anames = request.getAttributeNames();
		String an = null; String av = null;
		while (anames.hasMoreElements()) {
			an = (String)anames.nextElement();
			av = (String)request.getAttribute(an);
			out.println("<tr><td>Attribute::" + an + ":</td><td>" + av + "</td></tr>");
		}
		
		String cp = request.getContextPath();
		out.println("<tr><td>contextPath:</td><td>" + cp + "</td></tr>");
		
		Enumeration hnames = request.getHeaderNames();
		String hn = null; String hv = null;
		while (hnames.hasMoreElements()) {
			hn = (String)hnames.nextElement();
			hv = request.getHeader(hn);
			out.println("<tr><td>Header::" + hn + ":</td><td>" + hv + "</td></tr>");
		}
		
		String sn = request.getServerName();
		out.println("<tr><td>serverName:</td><td>" + sn + "</td></tr>");
		int sp = request.getServerPort();
		out.println("<tr><td>serverPort:</td><td>" + sp + "</td></tr>");

		out.println("</table>");
	}
	
	private void loadPersona(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
		String usr = request.getParameter("usr");
		String passwd = request.getParameter("passwd");
		
		this.sql = new SqlManager();
		
		// Usando pool
		this.sql.setConnectionMode(SqlManager.POOLCONNECTIONMODE);
		this.sql.setResourceName("jdbc/pgds");
		
		// Usando conexión simple
		this.sql.setConnectionMode(SqlManager.SINGLECONNECTIONMODE);
		this.sql.setDriverName("org.postgresql.Driver");
		this.sql.setUrl("jdbc:postgresql://naboo:5432/bargdb");
		this.sql.setUserName("webApp");
		this.sql.setPassword("webApp");
		
		this.sql.connect();
		
		this.sql.prepare("SELECT * " +
				"FROM v_usuario u " +
				"WHERE u.usr = ? " +
				"AND u.passwd = ?");
		this.sql.setString(1, usr);
		this.sql.setString(2, passwd);
		
		ResultSet rs = this.sql.executeQuery();
		int idPersona = 0;
		if (rs.first()) {
			idPersona = rs.getInt("idpersona");
			this.persona = DBPersona.loadDB(this.sql, idPersona);
			DBPersona.loadDirecciones(this.persona, this.sql);
			DBPersona.loadTelefonos(this.persona, this.sql);
			DBPersona.loadPrivilegios(this.persona, this.sql);
		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("Usuario/contraseña inválidos");
		}
		rs.close();
		this.sql.close();
	}
	
	private void personaOutput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if (this.persona!=null) {
			out.println("<br>");
			out.println("Apellido: " + this.persona.getApellido() + "<br>");
			out.println("Nombre: " + this.persona.getNombre() + "<br>");
			Documento doc = this.persona.getDocumento();
			out.println("Documento: (" + doc.getTipo() + ")" + doc.getNro() + "<br>");
			out.println("Sexo: " + this.persona.getSexo() + "<br>");
			out.println("Fecha de nacimiento: " + this.persona.getFecNac(Fecha.SPANISH, "dd 'de' MMMM 'de' yyyy") + "<br>");
			out.println("<br>");
			out.println("<H4>Direcciones:</H4><br>");
			List l = this.persona.getDirecciones();
			Direccion d = null;
			int c = l.size();
			for (int i=0; i<c; i++) {
				d = (Direccion) l.get(i);
				out.println(d.getAttribute("tipodir") + ":<br>");
				out.println(d.getCalle() + " " + d.getNro() + " " + d.getPuerta() + "<br>");
				out.println(d.getCp() + " - " + d.getLocalidad() + "<br>");
				out.println("<br>");
			}
			out.println("<H4>Teléfonos:</H4><br>");
			l = this.persona.getTelefonos();
			c = l.size();
			for (int i=0; i<c; i++) {
				out.println(l.get(i) + "<br>");
			}
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Hashtable privilegios = (Hashtable)this.persona.getAttribute("privilegios");
		String login = (String) privilegios.get("LOGIN");
		if (login!=null && login.equals("S")) {
			// Subo persona y privilegios a la sesión
			request.getSession().setAttribute("persona", this.persona);
			request.getSession().setAttribute("privilegios", privilegios);
			RequestDispatcher disp;
			ServletContext app = this.getServletContext();
			String menu = null;
			
//	 -----------------------------------------------------------
//			Comentar / descomentar una de las siguientes líneas
//	 -----------------------------------------------------------
			menu = "/Scriptlet/MenuScriptlet.jsp";
//	 -----------------------------------------------------------
//			menu = "/JSTL/MenuJSTL.jsp";
//	 -----------------------------------------------------------
			
			request.getSession().setAttribute("sql", sql);
			disp = app.getRequestDispatcher(menu);
			disp.forward(request, response);
		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(this.persona.toString() + " NO tiene privilegio de acceso..." +
					"<BR>" + "Diríjase al administrador.");
			this.sql.close();
		}
	}
	
	private void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//	 -----------------------------------------------------------
//			Comentar / descomentar uno de los siguientes grupos
//	 -----------------------------------------------------------
//			this.infoOutput(request, response);
//	 -----------------------------------------------------------
//			this.loadPersona(request, response);
//			this.personaOutput(request, response);
//	 -----------------------------------------------------------
			this.loadPersona(request, response);
			this.login(request, response);
//	 -----------------------------------------------------------
		} catch (Exception e) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(e.getMessage());
			e.printStackTrace();
		}
	}   	  	    

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return "Main Servlet: " + super.toString();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	} 
}