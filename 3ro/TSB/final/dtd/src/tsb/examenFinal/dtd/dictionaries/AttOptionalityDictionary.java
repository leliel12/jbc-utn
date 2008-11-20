package tsb.examenFinal.dtd.dictionaries;

import java.util.Vector;

/**
 *
 * Diccionario con las palabras/simbolos reservados de opcionalidad de atributos
 */
class AttOptionalityDictionary implements DictionaryModel {

    private Vector<String> words;
    private int type;

    public AttOptionalityDictionary() {
        words = new Vector<String>();
        this.type=AttOptionalityDictionary.ATT_OPTIONALITY;
        charge();
    }

    @Override
    public boolean exists(String word) {
        return words.contains(word);
    }

    private void charge() {
        words.add("#REQUIRED");
        words.add("#IMPLIED");
    }
    
    @Override
    public int getDicType() {
        return this.type;
    }
}
