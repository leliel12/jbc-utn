
/**
 * Una interface para el reconocimiento de patrones en un String
 * @version enero 2006
 */
public interface ValoresPatron
{
    /**
     * Indica que se evalua en cualquier posicion del string
     */
    public static final int PATRON=0;
    
    /**
     * Indica que se evalua en el comienzo del string
     */
    public static final int PATRON_COMIENZO=1;
    
    /**
     * Indica que se evalua en el final del string
     */
    public static final int PATRON_FINAL=2;	
}