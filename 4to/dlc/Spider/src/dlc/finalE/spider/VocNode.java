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

    public long getNr() {
        return nr;
    }

    public long getMaxTf() {
        return maxTf;
    }

    public void setMaxTf(long maxTf) throws SpiderException {
        if (this.maxTf < maxTf) {
            this.maxTf = maxTf;
        }
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
    public int hashCode() {
        return this.word.hashCode();
    }

    void incNr(long nr) {
        this.nr += nr;
    }

    public int getId(){
        return this.hashCode();
    }
}
