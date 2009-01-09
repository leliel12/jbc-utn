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
        if (nr < this.nr) {
            throw new SpiderException("new nr must be >= old nr", SpiderException.FATAL_ERROR);
        }
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
        if (maxTf < this.maxTf) {
            throw new SpiderException("new maxTf must be >= old maxTf", SpiderException.FATAL_ERROR);
        }
        this.maxTf = maxTf;
    }

    public int hashCode(){
        return this.word.hashCode();
    }

}
