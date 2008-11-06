package utn.frc.dlc.barg.db;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import utn.frc.dlc.javalib.base.Documento;
import utn.frc.dlc.javalib.base.Fecha;
import utn.frc.dlc.javalib.base.Persona;
import utn.frc.dlc.javalib.base.Sexo;
import utn.frc.dlc.javalib.db.SqlManager;

public class DBPersonaTest extends TestCase {
	Persona persona = null;
	SqlManager sql = null;
	
	public DBPersonaTest(String sTest) {
		super(sTest);
	}
	
	public void setUp() throws Exception {
		persona = new Persona();
		
		sql = new SqlManager();
		sql.setConnectionMode(SqlManager.SINGLECONNECTIONMODE);
		sql.setDriverName("org.postgresql.Driver");
		sql.setUrl("jdbc:postgresql://naboo:5432/bargdb");
		sql.setUserName("webApp");
		sql.setPassword("webApp");
		sql.connect();
	}
	
	public void testSaveDB() throws Exception {
		int idPersona = -100;
		String tipoDoc = "DNI";
		String nroDoc = "DNICharly";
		Documento documento = new Documento(tipoDoc, nroDoc);
		String apellido = "García";
		String nombre = "Charly";
		Sexo sexo = Sexo.Masculino;
		Fecha fecnac = new Fecha();
		fecnac.setDate("19511023", "yyyyMMdd");
		
		persona.setAttribute("idpersona", idPersona);
		persona.setDocumento(documento);
		persona.setApellido(apellido);
		persona.setNombre(nombre);
		persona.setSexo(sexo);
		try {
			DBPersona.saveDB(sql, persona);
			fail("Error al guardar una persona sin fecha de nacimiento");
		} catch (Exception e) {
			persona.setFecNac(fecnac);
		}
		try {
			DBPersona.saveDB(sql, persona);
			fail("Error al guardar una persona sin nacionalidad");
		} catch (Exception e) {
			persona.setAttribute("nacionalidad", "Argentina");
		}
		DBPersona.saveDB(sql, persona);
	}
	
	public void testLoadDB() throws Exception {
		int idPersona = -200;
		persona = DBPersona.loadDB(sql, idPersona);
		if (persona!=null) fail("Error al cargar una persona que NO debería existir");
		idPersona = -100;
		persona = DBPersona.loadDB(sql, idPersona);
		String apellido = persona.getApellido();
		assertEquals(apellido, "García");
		String nacionalidad = (String) persona.getAttribute("nacionalidad");
		assertEquals(nacionalidad, "Argentina");
		Sexo sexo = persona.getSexo();
		assertEquals(sexo, Sexo.Masculino);
	}
	
	public void testSaveDBError() throws Exception {
		int idPersona = -100;
		persona = DBPersona.loadDB(sql, idPersona);
		String apellido = "Éste apellido debe provocar un 'ERROR'";
		persona.setApellido(apellido);
		try {
			DBPersona.saveDBError(sql, persona);
			fail("Error al guardar una persona con indicio de SQL Injection");
		} catch (Exception e) {
			DBPersona.saveDB(sql, persona);
		}
		persona = DBPersona.loadDB(sql, idPersona);
		assertEquals(persona.getApellido(), apellido);
		DBPersona.deleteDB(sql, -100);
	}
	
	public void tearDown() throws Exception {
		if (sql!=null) sql.close();
	}
	
	public static TestSuite suite() {
		TestSuite dbPTest = new TestSuite("DBPersona");
		
		dbPTest.addTest(new DBPersonaTest("testSaveDB"));
		dbPTest.addTest(new DBPersonaTest("testLoadDB"));
		dbPTest.addTest(new DBPersonaTest("testSaveDBError"));
		
		return dbPTest;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		junit.swingui.TestRunner.run(DBPersonaTest.class);
	}
}
