/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import java.io.File;

/**
 *
 * @author juan
 */
class Indexer {

    private ConnectionHandler dbConnection;

    Indexer(ConnectionHandler dBConnection) {
        this.dbConnection = dBConnection;
    }

    void addToDb(FileHandler handler, File file) {
        
    }
}
