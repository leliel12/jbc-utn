/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

/**
 *
 * @author juan
 */
public interface ConnectionHandler {

    public void add2Vocabulary(VocNode vocNode) throws SpiderException;

    public void add2PostList(PostNode postNode) throws SpiderException;

    public boolean existsPostOf(String word) throws SpiderException;

    public VocNode[] getFullVocabulary() throws SpiderException;

    public PostNode getPostOf(String word) throws SpiderException;

    public void commit() throws SpiderException;

    public void close() throws SpiderException;

}
