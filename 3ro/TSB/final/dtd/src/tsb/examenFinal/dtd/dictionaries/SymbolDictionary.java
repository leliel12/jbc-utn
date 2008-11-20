package tsb.examenFinal.dtd.dictionaries;

import java.util.Vector;

/**
 *
 * Diccionario con las palabras/simbolos reservados de cardinalidad de elemtos
 */
class SymbolDictionary implements DictionaryModel{
    private Vector<String> words;
    private int type;

    public SymbolDictionary() {
        words = new Vector<String>();
        this.type=SymbolDictionary.SYMBOL;
        charge();
    }

    @Override
    public boolean exists(String word) {
        return words.contains(word);
    }

    private void charge() {
        words.add("+");
        words.add("?");
        words.add("*");
    }
    
    @Override
    public int getDicType() {
        return this.type;
    }

}
