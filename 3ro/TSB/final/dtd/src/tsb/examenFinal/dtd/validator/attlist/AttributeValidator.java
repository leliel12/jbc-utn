package tsb.examenFinal.dtd.validator.attlist;

import tsb.examenFinal.util.Attribute;
import tsb.examenFinal.dtd.dictionaries.*;
import tsb.examenFinal.util.StrOp;
import tsb.examenFinal.util.StrCmp;

/**
 *
 * valida un atributo individual de un elemento contra su regla individual en un attlist
 */
class AttributeValidator {

    private Dictionaries dic;

    public AttributeValidator() {
        dic = Dictionaries.getInstance();
    }

    public boolean validate(String rule, Attribute var) {
        boolean toReturn = true;
        String dataType = StrOp.split(rule, " ", "", "", '(', ')')[1];
        if (dic.getDictionaryType(dataType) == DictionaryModel.DATA_TYPE) {
            toReturn = checkDataType(dataType, var);
        } else {
            toReturn = checkDefaultValues(dataType, var);
        }
        return toReturn;
    }

    private boolean checkDataType(String dataType, Attribute var) {
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

    private boolean checkDefaultValues(String dataType, Attribute var) {
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
