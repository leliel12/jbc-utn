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
class DataTypeDictionary implements DictionaryModel {

    private Vector<String> words;
    private int type;

    public DataTypeDictionary() {
        words = new Vector<String>();
        this.type=DataTypeDictionary.DATA_TYPE;
        charge();
    }

    public boolean exists(String word) {
        return words.contains(word);
    }

    private void charge() {
        words.add("#PCDATA");
        words.add("CDATA");
        words.add("NMTOKEN");
        words.add("ID");
        words.add("IDREFS");
    }

    public int getDicType() {
        return this.type;
    }
}
