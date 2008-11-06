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
class ElementOptionalityDictionary implements DictionaryModel {

    private Vector<String> words;
    private int type;

    public ElementOptionalityDictionary() {
        words = new Vector<String>();
        this.type=ElementOptionalityDictionary.ELEMENT_OPTIONALITY;
        charge();
    }

    public boolean exists(String word) {
        return words.contains(word);
    }
    
    public int getDicType() {
        return this.type;
    }

    private void charge() {
        words.add("ANY");
        words.add("EMPTY");
    }
}
