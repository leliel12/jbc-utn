
package tsb.examenFinal.dom;

import java.util.Vector;
import tsb.examenFinal.util.Attribute;

/**
 *
 * Objeto que representa a un elemento xml
 */
public class Element {
    private String name;
    private Attribute[] att;
    private Vector<Element> elements;
    private String cont;

    public Element(String name, Attribute[] att) {
        this();
        this.name = name;
        this.att = att;
    }

    public Element() {
        elements =new Vector<Element>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attribute[] getAttributes() {
        return att;
    }

    public void setAttributes(Attribute[] att) {
        this.att = att;
    }

    public void addElement(Element element){
        this.elements.add(element);
    }

    public Element[] getElements(){
        return this.elements.toArray(new Element[elements.size()]);
    }

    /**
     * @return the cont
     */
    public String getContent() {
        return cont;
    }

    /**
     * @param cont the cont to set
     */
    public void setContent(String cont) {
        this.cont = cont;
    }





}
