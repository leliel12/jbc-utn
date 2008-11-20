package tsb.examenFinal.dtd.dictionaries;

import java.util.Vector;

/**
 *
 * Diccionario con las palabras/simbolos reservados de tipos de datos
 */
class DataTypeDictionary implements DictionaryModel {

    private Vector<String> words;
    private int type;

    public DataTypeDictionary() {
        words = new Vector<String>();
        this.type=DataTypeDictionary.DATA_TYPE;
        charge();
    }

    @Override
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

    @Override
    public int getDicType() {
        return this.type;
    }
}
