/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.proto;

import dlc.finalE.spider.FileHandler;
import dlc.finalE.spider.SpiderException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author juan
 */
public class SmallTxtHandler implements FileHandler {

    public static final String TXT_EXT = ".txt";
    public Iterator<String> words;

    public void setFile(File f) throws SpiderException {
        try {
            FileReader reader = new FileReader(f);
            String text = "";
            try {
                int read = 0;
                do {
                    read = reader.read();
                    if (read != -1) {
                        text += String.valueOf((char) read);
                    }
                } while (read != -1);
            } catch (Exception ex) {
                reader.close();
            }
            text = erase(text, "[].,!`\":';?\n");
            this.words = toVector(text).iterator();
        } catch (FileNotFoundException ex) {
            throw new SpiderException("File Not Found", SpiderException.WARNING);
        } catch (IOException ex) {
            throw new SpiderException("File Not Found", SpiderException.WARNING);
        }
    }

    public String getFileHandleExtension() {
        return TXT_EXT;
    }

    public String getNextWord() throws SpiderException {
        return words.next();
    }

    public boolean hasNextWord() throws SpiderException {
        return words.hasNext();
    }

    private String erase(String word, String charsToBeErase) {
        String toWork = word;
        char[] charToErase = charsToBeErase.toCharArray();
        for (int i = 0; i < charToErase.length; i++) {
            char c = charToErase[i];
            toWork = toWork.replace(c, ' ');
        }
        toWork = toWork.trim();
        return toWork;
    }

    private Vector<String> toVector(String text) {
        Vector<String> toReturn = new Vector<String>();
        String[] splited = text.split("[ ]");
        for (int i = 0; i < splited.length; i++) {
            String toAdd = splited[i].trim();
            if (!toAdd.isEmpty()) {
                toReturn.add(toAdd);
            }
        }
        return toReturn;
    }
}
