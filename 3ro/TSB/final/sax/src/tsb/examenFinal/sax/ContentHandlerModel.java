
package tsb.examenFinal.sax;

import tsb.examenFinal.util.Attribute;

/**
 *
 *  modelo del manejador de contenidos
 */
public interface ContentHandlerModel {

    public void startDocument() throws SAXException;

    public void endDocument() throws SAXException;

    public void startElement(String name, String read, Attribute[] vars) throws SAXException;

    public void endElement(String name, String read) throws SAXException;
    
    public void insertContent(String content)throws SAXException;
    
    public void documentInfo(Attribute[] vars)throws SAXException;
    
}
