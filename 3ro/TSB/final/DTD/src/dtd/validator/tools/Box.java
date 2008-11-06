/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.validator.tools;

import dtd.exception.DTDException;
import java.util.Vector;

/**
 *
 * @author juan
 */
public class Box {

    private Vector<BoxContent> content;
    public static final int CONTENT = 0;
    public static final int ELEMENT = 1;

    public Box() {
        this.content = new Vector<BoxContent>();
    }

    public void addValidateElement(String name, int type) throws DTDException {
        if (type != 0 && type != 1) {
            throw new DTDException("Invalid Type");
        }
        content.add(new BoxContent(name, type));
    }

    public void clear() {
        this.content.clear();
    }

    public BoxContent[] getElements() {
        BoxContent[] toReturn = new BoxContent[this.content.size()];
        toReturn = this.content.toArray(toReturn);
        return toReturn;
    }

    public class BoxContent {

        private String contenido;
        private int tipoDato;

        private BoxContent(String contenido, int tipoDato) {
            this.contenido = contenido;
            this.tipoDato = tipoDato;
        }

        public String getContenido() {
            return contenido;
        }

        public int getTipoDato() {
            return tipoDato;
        }
    }
}
