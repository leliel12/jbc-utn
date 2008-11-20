package tsb.examenFinal.dom;

import java.util.Vector;
import tsb.examenFinal.util.Attribute;

/**
 *
 * Objeto que representa a un xml
 */
public class XmlDocument {

    private String docInfo;
    private Element root;
    private Vector<Element> elements;

    public XmlDocument() {
        this.elements = new Vector<Element>();
    }

    public void addElement(Element element) {
        this.elements.add(element);
    }

    public Element[] getElements() {
        return this.elements.toArray(new Element[elements.size()]);
    }

    public Element getRootElement() {
        return root;
    }

    public void setRootElement(Element element) {
        this.root = element;
    }

    public String getDocInfo() {
        return docInfo;
    }

    public void setDocInfo(Attribute[] vars) {
        this.docInfo = "";
        for (int i = 0; i < vars.length; i++) {
            Attribute attribute = vars[i];
            this.docInfo += attribute.getName() + "=" + attribute.getValue() + " - ";
        }
    }
}
