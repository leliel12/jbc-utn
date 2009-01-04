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

    private Vector<String> words;
    public static final String TXT_EXTENSION = ".txt";

    public SmallTxtHandler(File file) {
        this.words = new Vector<String>();
    }

    public boolean isMyHandler(File f) {
        String name=f.getName();
        name=name.toLowerCase();
        return name.endsWith(SmallTxtHandler.TXT_EXTENSION);
    }

    public Iterator iterator() {
        return words.iterator();
    }

    private void add(String[] splitedWords) {
        for (int i = 0; i < splitedWords.length; i++) {
            String word = splitedWords[i];
            word = erase(word, ".,!`\":';?");
            if (word.split("[ ]").length > 1) {
                this.add(word.split("[ ]"));
            } else {
                this.words.add(word);
            }
        }
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

    public void readFile(File file) throws FileNotFoundException, SpiderException {
        String read = "";
        FileReader reader = new FileReader(file);
        try {
            while (true) {
                char c = (char) reader.read();
                read += String.valueOf(c);
            }
        } catch (IOException ex) {
        }
        String[] splitedWords = read.split("[ ]");
        add(splitedWords);
    }

    public void clearIteratorBuffer() {
        this.words.clear();
    }
}
