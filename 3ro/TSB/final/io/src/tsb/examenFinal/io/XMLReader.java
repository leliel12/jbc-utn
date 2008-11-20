package tsb.examenFinal.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * Lector de bajo nivel que sabe separar las partes de un xml
 */
public class XMLReader {

    private File file;
    private BufferedReader reader;
    private boolean eof;

    public XMLReader(String path) throws FileNotFoundException{
        this(new File(path));
    }

    public XMLReader(File xmlFile) throws FileNotFoundException {
        this.file = xmlFile;
        boolean s = true;
        this.reader = new BufferedReader(new FileReader(file));
        this.eof = false;
    }

    public boolean eof() {
        return this.eof;
    }

    public boolean close() {
        boolean s = true;
        try {
            reader.close();
        } catch (IOException ex) {
            s = false;
        } finally {
            return s;
        }
    }

    public char getChar() throws IOException{
        return (char) reader.read();
    }

    public String getNext() {
        String toReturn = "";
        int i = 0;
        char c0 = (char) -1;
        char c1 = (char) -1;
        try {
            do {
                i = reader.read();
                c0 = (char) i;
            } while (i != -1 && (String.valueOf("\n").charAt(0) == c0 || String.valueOf("\t").charAt(0) == c0 || String.valueOf(" ").charAt(0) == c0));
            toReturn = toReturn + String.valueOf(c0) + omitQuotes(c0);
            boolean end = false;
            do {
                switch (c0) {
                    case '<':
                        i = reader.read();
                        c1 = (char) i;
                        toReturn = toReturn + String.valueOf(c1) + omitQuotes(c1);
                        end = (c1 == '>' || toReturn.equals("<!DOCTYPE "));
                        reader.mark(1);
                        break;
                    default:
                        reader.mark(1);
                        i = reader.read();
                        c1 = (char) i;
                        if (c1 != '<') {
                            toReturn = toReturn + String.valueOf(c1) + omitQuotes(c1);
                        } else {
                            end = true;
                        }
                        break;
                }
            } while (!end && i != -1 && eof == false);
            this.resetMarks();
        } catch (Exception ex) {
        } finally {
            if (i == -1) {
                this.eof = true;
                toReturn = null;
            }
            if (toReturn != null) {
                toReturn = toReturn.trim();
            }
            return toReturn;
        }
    }

    /* **********************************
     * METODOS DE SOPORTE
     ************************************/
    private void resetMarks() {
        try {
            reader.reset();
        } catch (IOException ex) {
        }
    }

    private String omitQuotes(char c0) throws IOException {
        String toReturn = "";
        int i = 0;
        boolean localEnd = false;
        if (c0 == '"' && i == -1) {
            i = reader.read();
            while (eof == false && localEnd == false) {
                char c1 = (char) i;
                toReturn += String.valueOf(c1);
                if (c1 != '"') {
                    i = reader.read();
                } else {
                    localEnd = true;
                }
                if (i == -1) {
                    eof = true;
                }
            }
        }
        return toReturn;
    }
}