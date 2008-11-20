package tsb.examenFinal.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import tsb.examenFinal.util.CollOp;
import tsb.examenFinal.util.StrOp;

/**
 *
 * Lector de bajo nivel de DTD que sabe ubicar archivos en el sistema
 * y generar arrays con las reglas identificadas
 */
public class DTDReader {

    private XMLReader xmlR;
    private String[] internal;
    private String[] external;

    public DTDReader(XMLReader xmlReader) throws FileNotFoundException, InvalidReadException {
        this.xmlR = xmlReader;
        charge();
    }

    public String[] getExternalRules() {
        return external;
    }

    public String[] getInternalRules() {
        return internal;
    }

    private void charge() throws FileNotFoundException, InvalidReadException {
        boolean end = false;
        boolean wInternalInit = false;
        boolean wInternalEnd = false;
        char r = ' ';
        String full = "";
        while (!end) {
            try {
                r = xmlR.getChar();
            } catch (IOException ex) {
                end = true;
            }
            if (r == '[') {
                wInternalInit = true;
            } else if (wInternalInit && r == ']') {
                wInternalEnd = true;
            } else if (wInternalEnd && r == '>') {
                end = true;
            } else if (!wInternalInit && r == '>') {
                end = true;
            }
            full += String.valueOf(r);
        }//while
        chargeExternal(full);
        chargeInternal(full);
        validate(full);
    }

    private String[] removeEmpty(String[] toRE) {
        Vector<String> vector = new Vector<String>();
        for (int i = 0; i < toRE.length; i++) {
            String rule = toRE[i];
            if (rule != null && rule.trim().isEmpty()==false) {
                vector.add(rule);
            }
        }
        return vector.toArray(new String[vector.size()]);
    }

    private void validate(String full) throws InvalidReadException {
        String msg = "";
        int idxSystem = full.indexOf("SYSTEM");
        int idxPublic = full.indexOf("PUBLIC");
        int idxInternal = full.indexOf("[");
        if (idxPublic != -1 && idxSystem != -1) {
            msg += "No puede existir SYSTEM y PUBLIC en el mismo doctype.\n";
        }
        if (idxSystem != -1 && this.external.length < 1) {
            msg += "Archivo de Externo de DTD Vacio\n";
        }
        if (idxInternal != -1 && this.internal.length < 1) {
            msg += "DTD Interno vacio";
        }
        if (!msg.isEmpty()) {
            throw new InvalidReadException(msg, this);
        }
    }

    private void chargeExternal(final String full) throws FileNotFoundException {
        String path = "";
        int idxSystem = full.indexOf("SYSTEM");
        int idxPublic = full.indexOf("PUBLIC");
        if (idxSystem != idxPublic) {
            Vector<String> vector = new Vector<String>();
            int idxInitPath = full.indexOf("\"");
            int idxEndPath = full.indexOf("\"", idxInitPath + 1);
            path = full.substring(idxInitPath, idxEndPath).trim();
            path = path.replaceAll("\"", "");
            XMLReader exReader = new XMLReader(path);
            String externalRead = "";
            do {
                externalRead = exReader.getNext();
                if (externalRead != null) {
                    vector.add(externalRead);
                }
            } while (externalRead != null);
            exReader.close();
            this.external = vector.toArray(new String[vector.size()]);
            this.external = removeEmpty(external);
        }

    }

    private void chargeInternal(final String full) {
        int idx0 = full.indexOf("[") + 1;
        int idx1 = full.indexOf("]");
        if (idx0 != -1 && idx1 != -1) {
            String[] toWork = {full.substring(idx0, idx1).trim()};
            toWork = splitSpaces(toWork);
            toWork = splitCR(toWork);
            toWork = splitMaxMin(toWork);
            toWork = removeEmpty(toWork);
            this.internal = toWork;
        }
    }

    private String[] splitMaxMin(final String[] toSplit) {
        Vector<String> toReturn = new Vector<String>();
        for (int i = 0; i < toSplit.length; i++) {
            String str = toSplit[i];
            int idx = str.indexOf("><");
            if (idx != -1) {
                String s0 = str.substring(0, idx + 1);
                String s1 = str.substring(idx + 1, str.length());
                toReturn.add(s0);
                toReturn.add(s1);
            } else {
                toReturn.add(str);
            }
        }//for
        return toReturn.toArray(new String[toReturn.size()]);
    }

    private String[] splitCR(final String[] toSplit) {
        Vector<String> toReturn = new Vector<String>();
        for (int i = 0; i < toSplit.length; i++) {
            String str = toSplit[i];
            String[] split = StrOp.split(str, "\n", "", "", '<', '>');
            toReturn = (Vector) CollOp.fillCollection(toReturn, split);
        }
        return toReturn.toArray(new String[toReturn.size()]);
    }

    private String[] splitSpaces(String[] toSplit) {
        Vector<String> toReturn = new Vector<String>();
        for (int i = 0; i < toSplit.length; i++) {
            String str = toSplit[i];
            String[] split = StrOp.split(str, " ", "", "", '<', '>');
            toReturn = (Vector) CollOp.fillCollection(toReturn, split);
        }
        return toReturn.toArray(new String[toReturn.size()]);
    }
}
