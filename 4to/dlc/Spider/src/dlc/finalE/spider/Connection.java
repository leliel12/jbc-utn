/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import java.io.File;

/**
 *
 * @author juan
 */
public class Connection {

    private static String dbPath;
    private static Connection instance;
    private ObjectContainer database;

    public static synchronized void setDBFile(File dbFile) throws SpiderException {
        if (dbPath != null) {
            throw new SpiderException("dbfile is already defined");
        }
        Connection.dbPath = dbFile.getAbsolutePath();
    }

    public static synchronized Connection getConnection() throws SpiderException {
        if (Connection.instance == null) {
            if (dbPath == null) {
                throw new SpiderException("data base file is not defined. please use setDBFile");
            }
            instance = new Connection();
        }
        return Connection.instance;
    }

    private Connection() {
        this.database = Db4o.openFile(Connection.dbPath);
    }


}
