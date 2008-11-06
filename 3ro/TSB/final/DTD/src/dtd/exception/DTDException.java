/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dtd.exception;

/**
 *
 * @author juan
 */
public class DTDException extends Exception {

    /**
     * Creates a new instance of <code>DTDException</code> without detail message.
     */
    public DTDException() {
    }


    /**
     * Constructs an instance of <code>DTDException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DTDException(String msg) {
        super("DTD Exception: "+msg);
    }
}
