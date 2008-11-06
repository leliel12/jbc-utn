/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.dictionaries;

/**
 *
 * @author juan
 */
public interface DictionaryModel {

    public static final int ERROR=-1;
    public static final int ELEMENT_OPTIONALITY = 0;
    public static final int DATA_TYPE = 1;
    public static final int ATT_OPTIONALITY = 2;
    public static final int SYMBOL = 3;

    public boolean exists(String word);

    public int getDicType();
}
