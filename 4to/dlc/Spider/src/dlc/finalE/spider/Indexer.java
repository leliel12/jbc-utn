/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import java.io.File;
import java.util.Hashtable;

/**
 *
 * @author juan
 */
public class Indexer {


    private Hashtable<String, VocNode> vocabulary;

    public Indexer(File dbFile) {

    }

    public void add(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String string = words[i];
            string = normalize(string);
        }
    //normalizar
    //agregar al diccionario si no existe
    //agregar al posteo si no existe
    //grabar
    }

    public void save() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private String normalize(String toNormalize) {
        String toReturn = toNormalize.trim();
        toReturn = toReturn.toLowerCase();
        return toReturn;
    }
}
