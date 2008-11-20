package tsb.examenFinal.dtd.validator.attlist;

import tsb.examenFinal.util.Attribute;
import tsb.examenFinal.util.StrOp;
import java.util.ArrayList;
import tsb.examenFinal.dtd.dictionaries.Dictionaries;

/**
 *
 * valida el que los atributos de un elemento correspondan a su declaracion de attlist
 */
public class AttListValidator {

    private Dictionaries dic;
    private String[] rules;

    public AttListValidator(final String dtdRule) {
        dic = Dictionaries.getInstance();
        rules = this.getDefinitions(dtdRule);
    }

    public boolean validate(final Attribute[] atts) {
        boolean toReturn = true;
        for (int i = 0; i < rules.length && toReturn; i++) {
            String rule = rules[i];
            toReturn = validateRule(rule, atts);
        }
        return toReturn;
    }

    private String[] getDefinitions(String str) {
        ArrayList<String> toReturn = new ArrayList<String>();
        String[] list = StrOp.split(str, " ", "<!ATTLIST", ">", '(', ')');
        int j = 0;
        String toAdd = "";
        for (int i = 1; i < list.length; i++) {
            String string = list[i];
            if (j == 3) {
                if (dic.exists(string)) {
                    toAdd = toAdd + string + " ";
                } else {
                    i--;
                }
                toReturn.add(toAdd.trim());
                toAdd = "";
                j = 0;
            } else {
                toAdd = toAdd + string + " ";
                j++;
            }
        }
        if (toAdd.isEmpty() == false) {
            toReturn.add(toAdd.trim());
        }
        return toReturn.toArray(new String[toReturn.size()]);
    }

    private boolean validateCardinality(int n, String rule) {
        boolean toReturn = false;
        try {
            String[] ar = rule.split(" ");
            String cardConstant = ar[ar.length - 1];
            if (cardConstant.equals("#REQUIRED") == true) {
                toReturn = (n == 1);
            } else if (cardConstant.equals("#IMPLIED") == true) {
                toReturn = (n >= 0);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            toReturn = false;
        }
        return toReturn;
    }

    private boolean validateRule(String rule, Attribute[] atts) {
        int numberOfValidations = 0;
        AttributeValidator validator = new AttributeValidator();
        String ruleName = rule.split(" ")[0];
        for (int i = 0; i < atts.length; i++) {
            Attribute variable = atts[i];
            if (ruleName.equals(variable.getName()) == true && validator.validate(rule, variable) == true) {
                numberOfValidations++;
            }
        }
        return validateCardinality(numberOfValidations, rule);
    }
}
