/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.proto;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import dlc.finalE.spider.ConnectionHandler;
import dlc.finalE.spider.PostNode;
import dlc.finalE.spider.SpiderException;
import dlc.finalE.spider.VocNode;

/**
 *
 * @author juan
 */
public class DB4OConnection implements ConnectionHandler {

    private ObjectContainer database;

    public DB4OConnection(String dbFilePath) {
        this.database = Db4o.openFile(dbFilePath);
    }

    public void add2Vocabulary(VocNode vocNode) throws SpiderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void add2PostList(PostNode postNode) throws SpiderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public VocNode[] getFullVocabulary() throws SpiderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PostNode getPostOf(String word) throws SpiderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void commit() throws SpiderException {
        this.database.commit();
    }

    public void close() throws SpiderException {
        this.database.close();
    }
}
