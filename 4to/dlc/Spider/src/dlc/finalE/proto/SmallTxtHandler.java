/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.proto;

import dlc.finalE.spider.FileHandler;
import dlc.finalE.spider.SpiderException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author juan
 */
public class SmallTxtHandler implements FileHandler {

    public static final String TXT_EXT = ".txt";

    public boolean isMyHandler(File f) throws SpiderException {
        String fName = f.getName().toLowerCase();
        return fName.endsWith(TXT_EXT);
    }

    public String[] getWords(File f) throws SpiderException {
        String text;
        try {
            text = this.toStringFile(f);
        } catch (IOException ex) {
            throw new SpiderException(ex, SpiderException.WARNING);
        }
        Vector<String> words = new Vector<String>();
        String[] splited = text.split("([ ]");
        for (int i = 0; i < splited.length; i++) {
            String toAdd = splited[i].trim();
            if (!toAdd.isEmpty()) {
                words.add(toAdd);
            }
        }
        return words.toArray(new String[words.size()]);
    }

    private String toStringFile(File f) throws IOException {
        FileReader reader = new FileReader(f);
        String text = "";
        int read = -1;
        do {
            read = reader.read();
            if (read != -1) {
                text += String.valueOf((char) read);
            }
        } while (read != -1);
        reader.close();
        return text;
    }
}
