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

    public boolean add2Vocabulary(VocNode vocNode) throws SpiderException;

    public boolean add2PostList(PostNode postNode) throws SpiderException;

    public VocNode[] getFullVocabulary() throws SpiderException;

    public PostNode getPostOf(String word) throws SpiderException;

    public boolean commit() throws SpiderException;
}
