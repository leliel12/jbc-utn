/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import java.io.File;
import java.util.Vector;
import java.util.List;

/**
 *
 * @author juan
 */
public class WordLocation {

    private Vector<WordLocationNode> locations;

    public WordLocation() {
        this.locations = new Vector<WordLocationNode>();
    }

    public WordLocationNode[] getLocations() {
        return this.locations.toArray(new WordLocationNode[this.locations.size()]);
    }

    public WordLocationNode get(int idx) {
        return this.locations.get(idx);
    }

    public void addLocation(File file, long tf) {
        WordLocationNode newNode = new WordLocationNode(file, tf);
        if (!this.locations.contains(newNode)) {
            if (locations.size() == 0) {
                this.locations.add(newNode);
            } else {
                for (int i = 0; i < locations.size(); i++) {
                    WordLocationNode wordLocationNode = locations.get(i);
                    if (wordLocationNode.tf <= tf) {
                        int end = locations.size();
                        List<WordLocationNode> tail = locations.subList(i, end);
                        locations.removeAll(tail);
                        locations.add(newNode);
                        locations.addAll(tail);
                    }//if
                }//for
            }//else
        }//if
    }

    public int size() {
        return this.locations.size();
    }

    /*******************************************************************************
     *  PRIVATE CLASS
     *****************************************************************************/
    public class WordLocationNode {

        private final String fileAbsolutePath;
        private final long tf;

        public WordLocationNode(File file, long tf) {
            this.fileAbsolutePath = file.getAbsolutePath();
            this.tf = tf;
        }

        public String getFileAbsolutePath() {
            return fileAbsolutePath;
        }

        public long getTf() {
            return tf;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final WordLocationNode other = (WordLocationNode) obj;
            if ((this.fileAbsolutePath == null) ? (other.fileAbsolutePath != null) : !this.fileAbsolutePath.equals(other.fileAbsolutePath)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            return this.fileAbsolutePath.hashCode();
        }
    }//WordLocationNode
}//WordLocation

