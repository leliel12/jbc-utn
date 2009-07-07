/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import dlc.finalE.spider.WordLocation.WordLocationNode;
import java.io.File;

/**
 *
 * @author juan
 */
public class PostNode {

    private String word;
    private WordLocation locations;

    public PostNode(String word) {
        this.word = word;
        this.locations = new WordLocation();
    }

    public void addLocation(File file, long tf){
        this.locations.addLocation(file, tf);
    }

    public WordLocationNode[] getLocations(){
        return this.locations.getLocations();
    }

    public String getWord(){
        return this.word;
    }

     public int getNR(){
        return this.locations.size();
    }

    public long getMaxTf() {
        WordLocationNode maxTFNode = this.locations.get(0);
        return maxTFNode.getTf();
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

    @Override
    public int hashCode() {
        return this.word.hashCode();
    }

    public int getId(){
        return this.hashCode();
    }
}


