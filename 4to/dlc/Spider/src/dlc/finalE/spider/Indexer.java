/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import java.io.File;
import java.util.Vector;

/**
 *
 * @author juan
 */
class Indexer {

    private ConnectionHandler dbConnection;
    private Vector<VocNode> vocabulary;

    Indexer(ConnectionHandler dBConnection) throws SpiderException {
        this.dbConnection = dBConnection;
        this.vocabulary = new Vector<VocNode>();
        VocNode[] vocArray = dbConnection.getFullVocabulary();
        for (int i = 0; i < vocArray.length; i++) {
            VocNode vocNode = vocArray[i];
            this.vocabulary.add(vocNode);
        }
    }

    void addToDb(FileHandler handler, File file) throws SpiderException {
        String[] words = handler.getWords(file);
        for (String w : words) {
            long tf = this.getTF(w, words);
            words = this.remove(w, words);
            PostNode pn;
            if (dbConnection.existsPostOf(w)) {
                pn = dbConnection.getPostOf(w);
            } else {
                pn = new PostNode(w);
            }
            pn.addLocation(file, tf);
            dbConnection.add2PostList(pn);
            long maxTf = pn.getMaxTf();
            long nr = pn.getNR();
            VocNode vn = this.getVocNode(w);
            vn.incNr(nr);
            vn.setMaxTf(maxTf);
            this.dbConnection.add2Vocabulary(vn);
        }

    }

    private long getTF(String w, String[] words) {
        long tf = 0;
        for (String ow : words) {
            if (ow.equalsIgnoreCase(w)) {
                tf++;
            }
        }
        return tf;
    }

    private String[] remove(String w, String[] words) {
        Vector<String> newWords = new Vector<String>();
        for (String aw : words) {
            if (!aw.equalsIgnoreCase(w)) {
                newWords.add(aw);
            }
        }
        return newWords.toArray(new String[newWords.size()]);
    }

    private VocNode getVocNode(String w) {
        VocNode vn;
        int idx = this.vocabulary.indexOf(w);
        if (idx == -1) {
            vn = new VocNode(w);
        } else {
            vn = vocabulary.get(idx);
        }
        return vn;
    }
}
