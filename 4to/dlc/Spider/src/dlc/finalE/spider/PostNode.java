/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import java.io.File;
import java.util.HashSet;
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
        WordLocation loc = new WordLocation(file, tf);
        return false;
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PostNode other = (PostNode) obj;
        if ((this.word == null) ? (other.word != null) : !this.word.equals(other.word)) {
            return false;
        }
        return true;
    }

    public class WordLocation {

        private String fileAbsolutePath;
        private long tf;

        public WordLocation(File file, long tf) {
            this.setFileAbsolutePath(file);
            this.tf = tf;
        }

        public String getFileAbsolutePath() {
            return fileAbsolutePath;
        }

        public void setFileAbsolutePath(File file) {
            this.fileAbsolutePath = file.getAbsolutePath();
        }

        public long getTf() {
            return tf;
        }

        public void setTf(long tf) {
            this.tf = tf;
        }

        public int hashCode() {
            return fileAbsolutePath.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final String other = (String) obj;
            if ((this.fileAbsolutePath== null) ? (other != null) : !this.fileAbsolutePath.equals(other)) {
                return false;
            }
            return true;
        }
    }
}
