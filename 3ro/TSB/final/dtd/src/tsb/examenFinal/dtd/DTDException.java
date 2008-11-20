package tsb.examenFinal.dtd;

/**
 *
 * Empaqueta errores generados en el moemento de verificar sintacticamente un dtd
 * o al validar con su correspondiente xml
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
