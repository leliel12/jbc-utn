/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import java.io.File;
import java.util.HashSet;

/**
 *
 * @author juan
 */
class Indexer {

    private ConnectionHandler dbConnection;
    private HashSet<VocNode> vocabulary;

    Indexer(ConnectionHandler dBConnection) throws SpiderException {
        this.dbConnection = dBConnection;
        this.vocabulary=new HashSet<VocNode>();
        VocNode[] vocArray=dbConnection.getFullVocabulary();
        for (int i = 0; i < vocArray.length; i++) {
            VocNode vocNode = vocArray[i];
            this.vocabulary.add(vocNode);
        }
    }

    void addToDb(FileHandler handler, File file) throws SpiderException {
        handler.clearWordBuffer();
        handler.setFile(file);
        while(handler.hasNextWord()){
            String word=handler.getNextWord();

        }
    }
}
