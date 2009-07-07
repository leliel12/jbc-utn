/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dlc.finalE.proto;

import dlc.finalE.spider.ConnectionHandler;
import dlc.finalE.spider.PostNode;
import dlc.finalE.spider.SpiderException;
import dlc.finalE.spider.VocNode;

/**
 *
 * @author juan
 */
public class FirebirdDB implements ConnectionHandler{

    public void add2Vocabulary(VocNode vocNode) throws SpiderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void add2PostList(PostNode postNode) throws SpiderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean existsPostOf(String word) throws SpiderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public VocNode[] getFullVocabulary() throws SpiderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PostNode getPostOf(String word) throws SpiderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void commit() throws SpiderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void close() throws SpiderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
