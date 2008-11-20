package tsb.examenFinal.dtd.dictionaries;

import java.util.Vector;

/**
 *
 * Diccionario con las palabras/simbolos reservados de de opcionalidad de elementos
 */
class ElementOptionalityDictionary implements DictionaryModel {

    private Vector<String> words;
    private int type;

    public ElementOptionalityDictionary() {
        words = new Vector<String>();
        this.type=ElementOptionalityDictionary.ELEMENT_OPTIONALITY;
        charge();
    }

    @Override
    public boolean exists(String word) {
        return words.contains(word);
    }
    
    @Override
    public int getDicType() {
        return this.type;
    }

    private void charge() {
        words.add("ANY");
        words.add("EMPTY");
    }
}
