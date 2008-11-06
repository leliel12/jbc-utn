package utn.frc.dlc.barg.db;

import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.List;

import utn.frc.dlc.javalib.base.Direccion;
import utn.frc.dlc.javalib.base.Documento;
import utn.frc.dlc.javalib.base.Persona;
import utn.frc.dlc.javalib.base.Sexo;
import utn.frc.dlc.javalib.base.Telefono;
import utn.frc.dlc.javalib.db.DBKeyMap;
import utn.frc.dlc.javalib.db.SqlManager;

public abstract class DBPersona {
	private static DBKeyMap<Sexo, String> kmSexo = null;
	private static DBKeyMap<String, Integer> kmNacionalidad = null;
	
    public DBPersona() {
    }
    
    private static void setKeyMaps() {
    	if (kmSexo==null) {
    		kmSexo = new DBKeyMap<Sexo, String>();
    		kmSexo.setKeys(Sexo.Masculino, "M");
    		kmSexo.setKeys(Sexo.Femenino, "F");
    		kmSexo.setKeys(null, "N");
    	}
    	if (kmNacionalidad==null) {
    		kmNacionalidad = new DBKeyMap<String, Integer>();
    		kmNacionalidad.setKeys("Argentina", 1);
    	}
    }
    
    /**
     * 
     * @param sql
     * @param idPersona
     * @return
     * @throws Exception
     */
    public static Persona loadDB(SqlManager sql, Integer idPersona) throws Exception {
        if (sql==null) throw new Exception("DBPersona Error: SqlManager NO especificado");
        if (idPersona==null) throw new Exception("DBPersona Error: Identificador NO especificado");
        setKeyMaps();
        Persona p = null;
        String selectQuery = "SELECT * " +
                              "FROM persona p " +
                              "WHERE p.idpersona = " + idPersona;
        ResultSet rs = sql.executeQuery(selectQuery);
        if (rs.first()) {
            p = new Persona();
            p.setAttribute("idpersona", idPersona);
            p.setApellido(rs.getString("apellido"));
            p.setNombre(rs.getString("nombre"));
            String tipodoc = rs.getString("tipodoc");
            String nrodoc = rs.getString("nrodoc");
            Documento doc = new Documento(tipodoc, nrodoc);
            p.setDocumento(doc);
            Sexo s = kmSexo.getObjectKey(rs.getString("sexo"));
            p.setSexo(s);
            p.setFecNac(rs.getDate("fecnac"));
            String nacionalidad = kmNacionalidad.getObjectKey(rs.getInt("idnacionalidad"));
            p.setAttribute("nacionalidad", nacionalidad);
            p.setAttribute("observaciones", rs.getString("observaciones"));
        }
        rs.close();
        return p;
    }
    
    /**
     * 
     * @param sql
     * @param tipoDoc
     * @param nroDoc
     * @return
     * @throws Exception
     */
    public static Persona loadDB(SqlManager sql, String tipoDoc, String nroDoc) throws Exception {
        if (sql==null) throw new Exception("DBPersona Error: SqlManager NO especificado");
        if (tipoDoc==null) throw new Exception("DBPersona Error: Tipo de documento NO especificado");
        if (nroDoc==null) throw new Exception("DBPersona Error: Documento NO especificado");
        setKeyMaps();
        Persona p = null;
        String selectQuery = "SELECT * " +
                              "FROM persona p " +
                              "WHERE p.tipodoc = '" + tipoDoc + "' " +
                              "AND p.nrodoc = '" + nroDoc + "'";
        ResultSet rs = sql.executeQuery(selectQuery);
        if (rs.first()) {
            p = new Persona();
            p.setAttribute("idpersona", rs.getInt("idpersona"));
            p.setApellido(rs.getString("apellido"));
            p.setNombre(rs.getString("nombre"));
            Documento doc = new Documento(tipoDoc, nroDoc);
            p.setDocumento(doc);
            Sexo s = kmSexo.getObjectKey(rs.getString("sexo"));
            p.setSexo(s);
            p.setFecNac(rs.getDate("fecnac"));
            String nacionalidad = kmNacionalidad.getObjectKey(rs.getInt("idnacionalidad"));
            p.setAttribute("nacionalidad", nacionalidad);
            p.setAttribute("observaciones", rs.getString("observaciones"));
        }
        rs.close();
        return p;
    }
    
    /**
     * 
     * @param sql
     * @param persona
     * @throws Exception
     */
    public static void saveDBError(SqlManager sql, Persona persona) throws Exception {
        if (sql==null) throw new Exception("DBPersona ERROR: SqlManager NO especificado");
        if (persona == null) throw new Exception("DBPersona ERROR: Persona NO especificada");
        setKeyMaps();
        String s = kmSexo.getDBKey(persona.getSexo());
        Integer idNacionalidad = kmNacionalidad.getDBKey((String)persona.getAttribute("nacionalidad"));
        String statement = "SELECT fn_savepersona(" +
                              persona.getAttribute("idpersona") + ", " +
                              "'" + persona.getDocumento().getTipo() + "', " +
                              "'" + persona.getDocumento().getNro() + "', " +
                              "'" + persona.getApellido() + "', " +
                              "'" + persona.getNombre() + "', " +
                              "'" + s + "', " +
                              "'" + persona.getFecNac("yyyyMMdd") + "', " +
                              idNacionalidad + "::SMALLINT, " +
                              "'" + persona.getAttribute("observaciones") + "')";
        System.out.println(statement);
        ResultSet rs = sql.executeQuery(statement);
        rs.close();
        rs = null;
    }
    
    /**
     * 
     * @param sql
     * @param persona
     * @return
     * @throws Exception
     */
    public static Integer saveDB(SqlManager sql, Persona persona) throws Exception {
        if (sql==null) throw new Exception("DBPersona Error: SqlManager NO especificado");
        if (persona==null) throw new Exception("DBPersona Error: Persona NO especificada");
        setKeyMaps();
        String statement = "SELECT fn_savepersona(idpersona, tipodoc, nrodoc, apellido, nombre, sexo, fecnac, idnacionalidad, observaciones)";
        statement = "SELECT fn_savepersona(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        sql.prepare(statement);
        sql.setInt(1, (Integer) persona.getAttribute("idpersona"));
        String t = null, n = null;
        Documento d = persona.getDocumento();
        if (d!=null) {
        	t = d.getTipo();
        	n = d.getNro();
        }
        sql.setString(2, t);
        sql.setString(3, n);
        sql.setString(4, persona.getApellido());
        sql.setString(5, persona.getNombre());
        String s = kmSexo.getDBKey(persona.getSexo());
        sql.setString(6, s);
        sql.setString(7, persona.getFecNac("yyyyMMdd"));
        Integer idNacionalidad = kmNacionalidad.getDBKey((String) persona.getAttribute("nacionalidad"));
        sql.setSmallInt(8, idNacionalidad);
        sql.setString(9, (String) persona.getAttribute("observaciones"));
        ResultSet rs = sql.executeQuery();
        Integer idPersona = null;
        if (rs.first()) idPersona = rs.getInt(1);
        rs.close();
        return idPersona;
    }
    
    /**
     * 
     * @param idPersona
     * @throws Exception
     */
    public static void deleteDB(SqlManager sql, Integer idPersona) throws Exception {
        if (sql==null) throw new Exception("DBPersona Error: SqlManager NO especificado");
        if (idPersona==null) throw new Exception("DBPersona Error: Identificador NO especificado");
        String statement = "SELECT pr_deletepersona(?)";
        sql.prepare(statement);
        sql.setInt(1, idPersona);
        sql.execute();
    }
    
    
    public static void loadDirecciones(Persona persona, SqlManager sql) throws Exception {
        if (sql==null) throw new Exception("DBPersona Error: SqlManager NO especificado");
        if (persona == null) throw new Exception("DBPersona Error: Persona NO especificada");
        setKeyMaps();
        String selectQuery = "SELECT * " +
                              "FROM personadireccion pd " +
                              "WHERE pd.idpersona = " + persona.getAttribute("idpersona") + " " +
                              "ORDER BY pd.orden";
        ResultSet rs = sql.executeQuery(selectQuery);
        Direccion dir = null;
        if (rs.first()) do {
        	dir = new Direccion();
        	dir.setCp(rs.getString("direccioncp"));
        	dir.setLocalidad(rs.getString("direccionlocalidad"));
        	dir.setCalle(rs.getString("direccioncalle"));
        	dir.setNro(rs.getString("direccionnro"));
        	dir.setPuerta(rs.getString("direccionpuerta"));
        	dir.setAttribute("tipodir", rs.getString("tipo"));
            persona.addDireccion(dir);
        } while (rs.next());
        rs.close();
    }
    
    public static boolean saveDirecciones(Persona persona, SqlManager sql) throws Exception {
        if (sql==null) throw new Exception("DBPersona ERROR: SqlManager NO especificado");
        if (persona == null) throw new Exception("DBPersona ERROR: Persona NO especificada");

        int id = (Integer) persona.getAttribute("idpersona");
        sql.executeUpdate("DELETE FROM personadireccion " +
    			"WHERE idpersona = " + id);

        boolean ok = true;
    	List direcciones = persona.getDirecciones();
        if (direcciones==null || direcciones.size()==0) return ok;
        String statement = "SELECT fn_savedireccionpersona(idpersona, orden, tipo, direccioncp, direccionlocalidad, direccioncalle, direccionnro, direccionpuerta, observaciones)";
        statement = "SELECT fn_savedireccionpersona(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int size = direcciones.size();
        sql.prepare(statement);
        Direccion d;
        for (int i=0; i<size; i++) {
        	d = (Direccion) direcciones.get(i);
            sql.setInt(1, (Integer) persona.getAttribute("idpersona"));
            sql.setInt(2, i);
            sql.setString(3, (String) d.getAttribute("tipodir"));
            sql.setString(4, d.getCp());
            sql.setString(5, d.getLocalidad());
            sql.setString(6, d.getCalle());
            sql.setString(7, d.getNro());
            sql.setString(8, d.getPuerta());
            sql.setString(9, (String) d.getAttribute("observaciones"));
            ok = (ok && sql.execute());
        }
        return ok;
    }
    
    public static void loadTelefonos(Persona persona, SqlManager sql) throws Exception {
        if (sql==null) throw new Exception("DBPersona ERROR: SqlManager NO especificado");
        if (persona == null) throw new Exception("DBPersona ERROR: Persona NO especificada");
        String selectQuery = "SELECT * " +
                              "FROM personatelefono pt " +
                              "WHERE pt.idpersona = " + persona.getAttribute("idpersona") + " " +
                              "ORDER BY pt.orden";
        ResultSet rs = sql.executeQuery(selectQuery);
        Telefono tel = null;
        if (rs.first()) do {
        	tel = new Telefono(rs.getString("telefono"));
            persona.addTelefono(tel);
        } while (rs.next());
        rs.close();
    }

    public static boolean saveTelefonos(Persona persona, SqlManager sql) throws Exception {
        if (sql==null) throw new Exception("DBPersona ERROR: SqlManager NO especificado");
        if (persona == null) throw new Exception("DBPersona ERROR: Persona NO especificada");

        int id = (Integer) persona.getAttribute("idpersona");
        sql.executeUpdate("DELETE FROM personatelefono " +
    			"WHERE idpersona = " + id);

        boolean ok = true;
    	List<Telefono> telefonos = persona.getTelefonos();
        if (telefonos==null || telefonos.size()==0) return ok;
        String statement = "SELECT fn_savetelefonopersona(idpersona, orden, tipo, telefono, observaciones)";
        statement = "SELECT fn_savedireccionpersona(?, ?, ?, ?, ?)";
        int size = telefonos.size();
        sql.prepare(statement);
        Telefono t;
        for (int i=0; i<size; i++) {
        	t = telefonos.get(i);
            sql.setInt(1, (Integer) persona.getAttribute("idpersona"));
            sql.setInt(2, i);
            sql.setString(3, (String) t.getAttribute("tipotel"));
            sql.setString(4, t.toString());
            sql.setString(9, (String) t.getAttribute("observaciones"));
            ok = (ok && sql.execute());
        }
        return ok;
    }
    
    public static void loadPrivilegios(Persona persona, SqlManager sql) throws Exception {
        if (sql==null) throw new Exception("DBPersona ERROR: SqlManager NO especificado");
        if (persona == null) throw new Exception("DBPersona ERROR: Persona NO especificada");
        String selectQuery = "SELECT * " +
                              "FROM v_usuarioprivilegio up " +
                              "WHERE up.idpersona = " + persona.getAttribute("idpersona") + " " +
                              "ORDER BY up.permit desc";
        ResultSet rs = sql.executeQuery(selectQuery);
        Hashtable<String, String> privilegios = new Hashtable<String, String>();
        String priv = null, permit = null;
        if (rs.first()) do {
        	priv = rs.getString("cgoprivilegio");
        	permit = rs.getString("permit");
        	privilegios.put(priv, permit);
        } while (rs.next());
        rs.close();
        persona.setAttribute("privilegios", privilegios);
    }
}
