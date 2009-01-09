/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import java.io.File;
import java.util.Hashtable;

/**
 *
 * @author juan
 */
public class PostNode {

    String word;
    Hashtable<WordLocation, WordLocation> locations;

    public PostNode(String word) {
        this.word = word;
        locations = new Hashtable<WordLocation, WordLocation>();
    }

    public boolean addLocation(File file, long tf) {
        WordLocation loc=new WordLocation(file);
        loc.setTf(tf);
        
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    public class WordLocation {

        private String fileAblutePath;
        private long tf;

        public WordLocation(File file) {
            this.setFileAbsolutePath(file);
        }

        public String getFileAbsolutePath() {
            return fileAblutePath;
        }

        public void setFileAbsolutePath(File file) {
            this.fileAblutePath = file.getAbsolutePath();
        }

        public long getTf() {
            return tf;
        }

        public void setTf(long tf) {
            this.tf = tf;
        }

        public int hashCode() {
            return fileAblutePath.hashCode();
        }
    }
}
