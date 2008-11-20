package tsb.examenFinal.sax;

/**
 *
 * Modelo del manejador de errores
 */
public interface ErrorHandlerModel {

    public void fatalError(SAXException ex) throws SAXException;

    public void error(SAXException ex) throws SAXException;
    
    public void warning(SAXException ex) throws SAXException;
    
}
