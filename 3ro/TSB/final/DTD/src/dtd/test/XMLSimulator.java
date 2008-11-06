/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.test;

import java.util.ArrayList;
import dtd.validator.attlist.Variable;

/**
 *
 * @author juan
 */
public class XMLSimulator {

    public static final int START_ELEMENT = 0;
    public static final int END_ELEMENT = 1;
    public static final int INSERT_CONTENT = 2;
    public static final int START_DOCUMENT = 3;
    public static final int END_DOCUMENT = 4;
    private ArrayList<XMLStructure> coleccion;

    public XMLSimulator() {
        this.coleccion = new ArrayList<XMLStructure>();
        XMLStructure struct;

        struct = new XMLStructure("<?xml version=\"1.0\"?>", START_DOCUMENT, null);
        this.coleccion.add(struct);

        struct = new XMLStructure("listin", START_ELEMENT, null);
        this.coleccion.add(struct);

        Variable[] vars0 = {new Variable("sexo", "hombre"), new Variable("id", "ricky")};
        struct = new XMLStructure("persona", START_ELEMENT, vars0);
        this.coleccion.add(struct);

        struct = new XMLStructure("nombre", START_ELEMENT, null);
        this.coleccion.add(struct);

        struct = new XMLStructure("Ricky Martin", INSERT_CONTENT, null);
        this.coleccion.add(struct);

        struct = new XMLStructure("nombre", END_ELEMENT, null);
        this.coleccion.add(struct);

        struct = new XMLStructure("email", START_ELEMENT, null);
        this.coleccion.add(struct);

        struct = new XMLStructure("ricky@puerto-rico.com", INSERT_CONTENT, null);
        this.coleccion.add(struct);

        struct = new XMLStructure("email", END_ELEMENT, null);
        this.coleccion.add(struct);

        Variable[] vars1 = {new Variable("amigo-de", "leatitia")};
        struct = new XMLStructure("relacion", START_ELEMENT, vars1);
        this.coleccion.add(struct);

        struct = new XMLStructure("relacion", END_ELEMENT, null);
        this.coleccion.add(struct);

        struct = new XMLStructure("persona", END_ELEMENT, null);
        this.coleccion.add(struct);

        Variable[] vars2 = {new Variable("sexo", "mujer"), new Variable("id", "leatitia")};
        struct = new XMLStructure("persona", START_ELEMENT, vars2);
        this.coleccion.add(struct);

//        struct = new XMLStructure("nombre", START_ELEMENT, null);
//        this.coleccion.add(struct);
//
//        struct = new XMLStructure("Leatitia Casta", INSERT_CONTENT, null);
//        this.coleccion.add(struct);
//
//        struct = new XMLStructure("nombre", END_ELEMENT, null);
//        this.coleccion.add(struct);
        
        struct = new XMLStructure("item", START_ELEMENT, null);
        this.coleccion.add(struct);

        struct = new XMLStructure("cosa", INSERT_CONTENT, null);
        this.coleccion.add(struct);

        struct = new XMLStructure("item", END_ELEMENT, null);
        this.coleccion.add(struct);        

        struct = new XMLStructure("email", START_ELEMENT, null);
        this.coleccion.add(struct);

        struct = new XMLStructure("castal@micasa.com", INSERT_CONTENT, null);
        this.coleccion.add(struct);

        struct = new XMLStructure("email", END_ELEMENT, null);
        this.coleccion.add(struct);

        Variable[] vars4 = {new Variable("amigo-de", "leatitia")};
        struct = new XMLStructure("relacion", START_ELEMENT, vars4);
        this.coleccion.add(struct);

        struct = new XMLStructure("relacion", END_ELEMENT, null);
        this.coleccion.add(struct);

        struct = new XMLStructure("persona", END_ELEMENT, null);
        this.coleccion.add(struct);

        //nueva persona
//         Variable[] vars3 = {new Variable("sexo", "hombre"), new Variable("id", "Pancho")};
//        struct = new XMLStructure("persona", START_ELEMENT, vars3);
//        this.coleccion.add(struct);
//
//        struct = new XMLStructure("nombre", START_ELEMENT, null);
//        this.coleccion.add(struct);
//
//        struct = new XMLStructure("El Cho", INSERT_CONTENT, null);
//        this.coleccion.add(struct);
//
//        struct = new XMLStructure("nombre", END_ELEMENT, null);
//        this.coleccion.add(struct);
//
//        struct = new XMLStructure("email", START_ELEMENT, null);
//        this.coleccion.add(struct);
//
//        struct = new XMLStructure("choto@travesti.com", INSERT_CONTENT, null);
//        this.coleccion.add(struct);
//
//        struct = new XMLStructure("email", END_ELEMENT, null);
//        this.coleccion.add(struct);
//
//        struct = new XMLStructure("persona", END_ELEMENT, null);
//        this.coleccion.add(struct);
        ////////////////////////////////////////////////////////////////////////

        struct = new XMLStructure("listin", END_ELEMENT, null);
        this.coleccion.add(struct);

        struct = new XMLStructure("listin", END_DOCUMENT, null);
        this.coleccion.add(struct);
    }

    public XMLStructure getNext() {
        XMLStructure toReturn = null;
        if (this.coleccion.size() > 0) {
            toReturn = this.coleccion.get(0);
            this.coleccion.remove(0);
        }
        return toReturn;
    }

    public class XMLStructure {

        String name;
        int type;
        Variable[] vars;
        String read;

        public XMLStructure(String str, int type, Variable[] vars) {
            this.name = str;
            this.type = type;
            this.vars = vars;
            this.read = str;
        }
    }
}
