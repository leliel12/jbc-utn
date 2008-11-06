/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.syntax.element;

import dtd.syntax.tools.DTDTool;
import dtd.dictionaries.Dictionaries;
import dtd.dictionaries.DictionaryModel;
import dtd.syntax.SyntaxValidatorModel;
import dtd.tools.StrCmp;
import dtd.tools.StrOp;

/**
 *
 * @author juan
 */
public class ElementSyntaxValidator implements SyntaxValidatorModel {

    private Dictionaries dic;
    private ListSyntaxValidator lg;

    public ElementSyntaxValidator() {
        this.dic = Dictionaries.getInstance();
        this.lg = new ListSyntaxValidator();
    }

    public boolean verify(String str) {
        boolean toReturn = true;

        if (StrCmp.isInitEnd(str, "<!ELEMENT", ">") == false) {
            toReturn = false;
        } else if (StrCmp.isEmpty(str, "<!ELEMENT", ">") == true) {
            toReturn = false;
        } else if (StrCmp.hasMoreThan(str, "<>!", 1, false) == true) {
            toReturn = false;
        } else if (validateName(str) == false) {
            toReturn = false;
        } else if (validateSyntax(str) == false) {
            toReturn = false;
        } else if (validateList(str) == false) {
            toReturn = false;
        }
        return toReturn;
    }

    private boolean validateList(String str) {
        boolean toReturn = true;
        if (StrCmp.hasMoreThan(str, "()", 0, false) == true) {
            String list = DTDTool.extractList(str);
            if (list != null && this.lg.verify(list) == false) {
                toReturn = false;
            }
        }//si no tiene listas devuelve ok

        return toReturn;
    }

    private boolean validateSyntax(String str) {
        boolean toReturn = true;
        boolean hasList = (DTDTool.extractList(str) != null);
        String str1 = str;
        String[] array = StrOp.split(str1, " ", "<!ELEMENT", ">", '(', ')');
        if (dic.exists(array[0])) {
            toReturn = false;
        } else if (array.length == 1) {
            toReturn = false;
        } else if (array.length == 2 && dic.getDictionaryType(array[0]) == DictionaryModel.DATA_TYPE) {
            toReturn = false;
        } else if (array.length == 2 && dic.getDictionaryType(array[0]) == DictionaryModel.ELEMENT_OPTIONALITY) {
            toReturn = false;
        } else if (array.length == 2 && dic.getDictionaryType(array[1]) != DictionaryModel.ELEMENT_OPTIONALITY && hasList == false) {
            toReturn = false;
        } else if (array.length > 2 && hasList == false) {
            toReturn = false;
        } else if (dic.getDictionaryType(array[array.length - 1]) != DictionaryModel.ELEMENT_OPTIONALITY && hasList==false) {
            toReturn = false;
        }
        return toReturn;
    }

    private boolean validateName(final String str) {
        boolean toReturn = true;
        String name = StrOp.split(str, " ", "<!ELEMENT", ">", '(', ')')[0];
        if (StrCmp.hasMoreThan(name, "?-!/@$%&", 0, false) == true) {
            toReturn = false;
        }
        if (dic.exists(name) == true) {
            toReturn = false;
        }
        return toReturn;
    }
}
