/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.validator.attlist;

import dtd.tools.StrOp;
import java.util.ArrayList;
import dtd.dictionaries.Dictionaries;

/**
 *
 * @author juan
 */
public class AttListValidator {

    private Dictionaries dic;
    private String[] rules;

    public AttListValidator(final String dtdRule) {
        dic = Dictionaries.getInstance();
        rules = this.getDefinitions(dtdRule);
    }

    public boolean validate(final Variable[] atts) {
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

    private boolean validateRule(String rule, Variable[] atts) {
        int numberOfValidations = 0;
        AttributeValidator validator = new AttributeValidator();
        String ruleName = rule.split(" ")[0];
        for (int i = 0; i < atts.length; i++) {
            Variable variable = atts[i];
            if (ruleName.equals(variable.getName()) == true && validator.validate(rule, variable) == true) {
                numberOfValidations++;
            }
        }
        return validateCardinality(numberOfValidations, rule);
    }
}
