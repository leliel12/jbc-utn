package tsb.examenFinal.ui.models;

import java.util.EmptyStackException;
import java.util.Stack;
import tsb.examenFinal.sax.ContentHandlerModel;
import tsb.examenFinal.sax.SAXException;
import tsb.examenFinal.util.Attribute;
import tsb.examenFinal.dom.Element;
import tsb.examenFinal.dom.XmlDocument;

/**
 *
 * implementacion del content handler que carga el dom
 */
public class ContentHandler implements ContentHandlerModel {

    private XmlDocument dom;
    private Stack<Element> stk;

    public ContentHandler() {
        stk = new Stack<Element>();
    }

    public void startDocument() throws SAXException {
        dom = new XmlDocument();
    }

    public void endDocument() throws SAXException {
        int a=1;
    }

    public void startElement(String name, String read, Attribute[] vars) throws SAXException {
        this.printConsole(read);
        try {
            Element element = new Element(name, vars);
            if (dom.getRootElement() == null) {
                dom.setRootElement(element);
            } else {
                if (stk.isEmpty()) {
                    dom.addElement(element);
                    stk.push(element);
                } else {
                    stk.peek().addElement(element);
                    stk.push(element);
                }
            }
        } catch (EmptyStackException ex) {
            dom=null;
            throw new SAXException(SAXException.ERROR, ex.getMessage());
        }
    }

    public void endElement(String name, String read) throws SAXException {
        this.printConsole(read);
        try {
            if (!stk.isEmpty()) {
                String stkName = stk.peek().getName();
                if (stkName.equals(name)) {
                    stk.pop();
                } else {
                    throw new SAXException(SAXException.ERROR, "no name");
                }
            }
        } catch (EmptyStackException ex) {
            dom=null;
            throw new SAXException(SAXException.ERROR, ex.getMessage());
        }
    }

    public void insertContent(String content) throws SAXException {
        this.printConsole(content);
        try {
            stk.peek().setContent(content);
        } catch (EmptyStackException ex) {
            throw new SAXException(SAXException.ERROR, ex.getMessage());
        }
    }

    public void documentInfo(Attribute[] vars) throws SAXException {
        this.printConsole(vars);
        dom.setDocInfo(vars);
    }

    public XmlDocument getDom() {
        return this.dom;
    }

    private void printConsole(String msg) {
        System.out.println(msg);
    }

    private void printConsole(Attribute[] docinfo) {
        String msg = "DocInfo:";
        for (int i = 0; i < docinfo.length; i++) {
            Attribute attribute = docinfo[i];
            msg += "\n\t" + attribute.toString();
        }
        System.out.println(msg);
    }
}
