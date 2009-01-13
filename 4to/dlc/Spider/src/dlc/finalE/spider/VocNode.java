/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

/**
 *
 * @author juan
 */
public class VocNode {

    private long nr;
    private long maxTf;
    private String word;

    public VocNode(String word) {
        this.word = word;
    }

    /**
     * @return the nr
     */
    public long getNr() {
        return nr;
    }

    /**
     * @param nr the nr to set
     */
    public void setNr(long nr) throws SpiderException {
        this.nr = nr;
    }

    /**
     * @return the maxTf
     */
    public long getMaxTf() {
        return maxTf;
    }

    /**
     * @param maxTf the maxTf to set
     */
    public void setMaxTf(long maxTf) throws SpiderException {
        this.maxTf = maxTf;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VocNode other = (VocNode) obj;
        if ((this.word == null) ? (other.word != null) : !this.word.equals(other.word)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode(){
        return this.word.hashCode();
    }

}
