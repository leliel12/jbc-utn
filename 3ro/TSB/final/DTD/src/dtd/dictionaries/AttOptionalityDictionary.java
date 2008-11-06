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
class AttOptionalityDictionary implements DictionaryModel {

    private Vector<String> words;
    private int type;

    public AttOptionalityDictionary() {
        words = new Vector<String>();
        this.type=AttOptionalityDictionary.ATT_OPTIONALITY;
        charge();
    }

    public boolean exists(String word) {
        return words.contains(word);
    }

    private void charge() {
        words.add("#REQUIRED");
        words.add("#IMPLIED");
    }
    
    public int getDicType() {
        return this.type;
    }
}
