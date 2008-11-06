/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dtd.dictionaries;

import java.util.Vector;

/**
 *
 * @author juan
 */
class SymbolDictionary implements DictionaryModel{
    private Vector<String> words;
    private int type;

    public SymbolDictionary() {
        words = new Vector<String>();
        this.type=SymbolDictionary.SYMBOL;
        charge();
    }

    public boolean exists(String word) {
        return words.contains(word);
    }

    private void charge() {
        words.add("+");
        words.add("?");
        words.add("*");
    }
    
    public int getDicType() {
        return this.type;
    }

}
