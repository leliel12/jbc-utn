/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import dlc.finalE.spider.*;

/**
 *
 * @author juan
 */
public class VocNode {

    private int nr;
    private int maxTf;

    public VocNode(int nr, int maxTf) throws SpiderException {
        if (nr <= 0) {
            throw new SpiderException("nr must be > 0");
        }
        if (maxTf <= 0) {
            throw new SpiderException("maxTf must be > 0");
        }
        this.nr = nr;
        this.maxTf = maxTf;
    }

    /**
     * @return the nr
     */
    public int getNr() {
        return nr;
    }

    /**
     * @param nr the nr to set
     */
    public void setNr(int nr) throws SpiderException {
        if (nr < this.nr) {
            throw new SpiderException("new nr must be >= old nr");
        }
        this.nr = nr;
    }

    /**
     * @return the maxTf
     */
    public int getMaxTf() {
        return maxTf;
    }

    /**
     * @param maxTf the maxTf to set
     */
    public void setMaxTf(int maxTf) throws SpiderException {
        if (maxTf < this.maxTf) {
            throw new SpiderException("new maxTf must be >= old maxTf");
        }
        this.maxTf = maxTf;
    }
}
