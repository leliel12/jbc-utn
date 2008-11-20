package tsb.examenFinal.ui.models;

import java.io.File;
import java.io.FileNotFoundException;
import tsb.examenFinal.Info;
import tsb.examenFinal.dom.Element;
import tsb.examenFinal.dom.XmlDocument;
import tsb.examenFinal.sax.SAXParser;
import tsb.examenFinal.util.Attribute;

/**
 *
 * controlador de la ventana principal
 */
public class MainJFrameModel {

    private XmlDocument dom;
    private String filename;

    public void parseXmlFile(File f) throws FileNotFoundException {
        this.filename = f.getName();
        SAXParser sp = new SAXParser();
        ContentHandler ch = new ContentHandler();
        ErrorHandler eh = new ErrorHandler();
        sp.setXmlFile(f);
        sp.setContentHandler(ch);
        sp.setErrorHandler(eh);
        sp.doParse();
        dom = ch.getDom();

    }

    public String getDomText() {
        String toReturn = "ERROR";
        if (dom != null) {
            toReturn = dom.getDocInfo() + "\n\n";
            toReturn += dom.getRootElement().getName() + "\n";
            toReturn += getElements(dom.getElements(), "#");
        }
        return toReturn;
    }

    public String getWindowTitle() {
        String toReturn = Info.PROGRAM_NAME + " - ";
        if (dom == null) {
            toReturn += "<ERROR> ";
        }
        toReturn += this.filename;
        return toReturn;
    }

    private String getElements(Element[] elements, String numString) {
        String toReturn = "";
        for (int i = 0; i < elements.length; i++) {
            Element element = elements[i];
            toReturn += numString + element.getName() + "\n";
            if (element.getContent() != null) {
                toReturn += numString + element.getContent() + "\n";
            }
            if (element.getAttributes() != null && element.getAttributes().length > 0) {
                toReturn += numString + this.atributes2String(element.getAttributes())+"\n";
            }
            if (element.getElements() != null && element.getElements().length > 0) {
                toReturn+=this.getElements(element.getElements(), numString+"###")+"\n";
            }
        }
        return toReturn;
    }

    private String atributes2String(Attribute[] atts) {
        String toReturn = "";
        for (int i = 0; i < atts.length; i++) {
            Attribute attribute = atts[i];
            toReturn += attribute.toString() + " ";
        }
        return toReturn;
    }
}
