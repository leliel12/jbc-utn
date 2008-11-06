//Va sólo el establecimiento de conexión.
//La idea es que cada grupo construya SU propio SqlManager,
//como así también SU propia librería de clases.


package utn.frc.dlc.barg.db;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import utn.frc.dlc.javalib.base.Fecha;

/**
 *
 * @author scarafia
 */
public class SqlManager implements Serializable {

    private String driverName = null;               // "org.postgresql.Driver";
    private String url = null;                      // "jdbc:postgresql://localhost:5432/bargdb";
    private String resourceName = null;             // "[java:comp/env/]jdbc/<dataSourceName>";

    private void setContext() throws Exception {
    	if (this.ds == null) {
	        if (this.resourceName == null) throw new Exception("SQLManager ERROR: ResourceName (JNDI) NO especificado");
	        this.ctx = new InitialContext();
	        this.ds = (DataSource) this.ctx.lookup(resourceName);
    	}
    }
    
    public void connect() throws Exception {
    	if (this.cn == null) {
	        if (this.connectionMode == SINGLECONNECTIONMODE) {
	            Class.forName(driverName);
	            this.cn = DriverManager.getConnection(this.url, this.usr, this.pwd);
	        } else {
	            this.setContext();
	            this.cn = this.ds.getConnection();
	        }
		}
    }
    
    public void disconnect() throws Exception {
        if (this.stmt!=null) this.stmt.close(); this.stmt = null;
        if (this.pstmt!=null) this.pstmt.close(); this.pstmt = null;
        if (this.cstmt!=null) this.cstmt.close(); this.cstmt = null;
        if (this.cn!=null) this.cn.close(); this.cn = null;
        this.ds = null;
        if (this.ctx!=null) this.ctx.close(); this.ctx = null;
    }
    
    public void close() throws Exception {
    	this.disconnect();
    }
    
