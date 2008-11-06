package dtd.test;

import java.util.ArrayList;

class DTDSimulator {

    private ArrayList<String> coleccion;

    public DTDSimulator() {
        super();
        this.coleccion = new ArrayList<String>();
        coleccion.add("<?xml encoding=\"UTF­8\"?>");
        coleccion.add("<!ELEMENT listin (persona)+>");
        coleccion.add("<!ELEMENT persona (nombre|item, email, relacion)+ >");
        coleccion.add("<!ATTLIST persona id ID #REQUIRED>");
        coleccion.add("<!ELEMENT nombre (#PCDATA | item | extra)>");
        coleccion.add("<!ELEMENT email (#PCDATA)>");
        coleccion.add("<!ELEMENT item (#PCDATA)>");
        coleccion.add("<!ELEMENT extra (#PCDATA)>");        
        coleccion.add("<!ELEMENT relacion EMPTY>");
        coleccion.add("<!ATTLIST relacion amigo-de IDREFS #IMPLIED enemigo-de IDREFS #IMPLIED>");
     
    }

    public String getNext() {
        String toReturn = null;
        if (this.coleccion.size() > 0) {
            toReturn = this.coleccion.get(0);
            this.coleccion.remove(0);
        }
        return toReturn;
    }
}
