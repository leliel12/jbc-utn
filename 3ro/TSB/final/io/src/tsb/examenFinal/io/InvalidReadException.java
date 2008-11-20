package tsb.examenFinal.io;


/**
 * Exception dtd de bajo nivel que identifica algunas malformaciones
 * indetectables en niveles superiores
 */
public class InvalidReadException extends Exception {

    DTDReader outer;

    public InvalidReadException(String message, DTDReader outer) {
        super(message);
        this.outer = outer;
    }
}
