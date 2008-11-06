/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.validator.attlist;

import dtd.dictionaries.*;
import dtd.tools.StrOp;
import dtd.tools.StrCmp;

/**
 *
 * @author juan
 */
class AttributeValidator {

    private Dictionaries dic;

    public AttributeValidator() {
        dic = Dictionaries.getInstance();
    }

    public boolean validate(String rule, Variable var) {
        boolean toReturn = true;
        String dataType = StrOp.split(rule, " ", "", "", '(', ')')[1];
        if (dic.getDictionaryType(dataType) == DictionaryModel.DATA_TYPE) {
            toReturn = checkDataType(dataType, var);
        } else {
            toReturn = checkDefaultValues(dataType, var);
        }
        return toReturn;
    }

    private boolean checkDataType(String dataType, Variable var) {
        boolean toReturn = false;
        if (dataType.equals("#PCDATA") || dataType.equals("ID") || dataType.equals("IDREFS")) {
            toReturn = StrCmp.hasOnlyAlphaNumAndDashes(var.getValue());
        } else if (dataType.equals("CDATA")) {
            toReturn = true;
        } else if (dataType.equals("NMTOKEN")) {
            toReturn = StrCmp.hasOnlyAlphaNumAndDashes(var.getValue());
            if (toReturn == false) {
                toReturn = hasOneOf(var.getValue(), ":.");
            }
        }
        return toReturn;
    }

    private boolean checkDefaultValues(String dataType, Variable var) {
        boolean toReturn = false;
        String[] posibleValues = dataType.substring(1, dataType.length() - 1).split("[|]");
        for (int i = 0; i < posibleValues.length && toReturn == false; i++) {
            String string = posibleValues[i];
            toReturn = (string.equals(var.getValue()));
        }
        return toReturn;
    }

    private boolean hasOneOf(String value, String toFind) {
        char[] aValues = value.toCharArray();
        char[] aToFind = toFind.toCharArray();
        boolean toReturn = false;
        for (int i = 0; !toReturn && i < aToFind.length; i++) {
            char tf = aToFind[i];
            for (int j = 0; !toReturn && j < aValues.length; j++) {
                char v = aToFind[j];
                if (v == tf) {
                    toReturn = true;
                }
            }
        }
        return toReturn;
    }
}
