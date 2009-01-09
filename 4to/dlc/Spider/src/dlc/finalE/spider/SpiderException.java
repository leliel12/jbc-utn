/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

/**
 *
 * @author juan
 */
public class SpiderException extends Exception {

    public SpiderException(Exception ex) {
        super("From Exception: " + ex.getClass().getName());
    }

    public SpiderException(Exception ex, String msg) {
        super("From Exception: " + ex.getClass().getName() + "\n" + msg);
    }

    public SpiderException() {
        super();
    }

    public SpiderException(String msg) {
        super(msg);
    }
}
