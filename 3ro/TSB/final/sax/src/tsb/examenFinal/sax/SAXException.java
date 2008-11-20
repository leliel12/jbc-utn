package tsb.examenFinal.sax;

/**
 *
 * Exepcion generica que mantiene el estandar sax
 * de Warning Error y 
 * Fatal error
 */
public class SAXException extends Exception {

    public static int WARNING = 0;
    public static int ERROR = 1;
    public static int FATAL_ERROR = 2;
    private int type;

    /**
     * Creates a new instance of <code>MorfenioSaxException</code> without detail message.
     */
    public SAXException(int type) {
        this.setType(type);
    }

    /**
     * Constructs an instance of <code>MorfenioSaxException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public SAXException(int type, String msg) {
        super(msg);
        this.setType(type);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        if (type < 0 || type > 2) {
            this.type = 1;
        }
        this.type = type;
    }
}
