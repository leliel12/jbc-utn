/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dtd.dictionaries;

import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author juan
 */
public class Dictionaries{

    private static Dictionaries instance;
    private Vector<DictionaryModel> dics;
    
    public synchronized static Dictionaries getInstance(){
        if(instance==null) instance=new Dictionaries();
        return instance;
    }

    private Dictionaries() {
        dics=new Vector<DictionaryModel>();
        dics.add(new DataTypeDictionary());
        dics.add(new AttOptionalityDictionary());
        dics.add(new ElementOptionalityDictionary());
        dics.add(new SymbolDictionary());
    }

    public boolean exists(String word) {
        boolean toReturn=false;
        for (Iterator<DictionaryModel> it = dics.iterator(); it.hasNext();) {
            DictionaryModel dic = it.next();
            if(dic.exists(word)){
                toReturn=true;
            }
        }
        return toReturn;
    }
    
    public int getDictionaryType(String word){
        int toReturn=DictionaryModel.ERROR;
        for (Iterator<DictionaryModel> it = dics.iterator(); it.hasNext();) {
            DictionaryModel dic = it.next();
            if(dic.exists(word)){
                toReturn=dic.getDicType();
            }
        }
        return toReturn;
    }
    
}
