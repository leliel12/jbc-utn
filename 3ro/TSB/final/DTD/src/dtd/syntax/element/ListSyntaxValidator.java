/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.syntax.element;

import dtd.dictionaries.Dictionaries;
import dtd.syntax.tools.DTDTool;
import dtd.tools.StrOp;

/**
 *
 * @author juan
 */
public class ListSyntaxValidator {

    private Dictionaries dic;
    private ComponentSyntaxValidator cv;

    public ListSyntaxValidator() {
        this.dic = Dictionaries.getInstance();
        this.cv = new ComponentSyntaxValidator();
    }

    public boolean verify(final String toValidate) {
        boolean toReturn = true;
        String toWork = extractOpt(toValidate);
        if (toWork == null) {
            toReturn = false;
        } else {
            String[] array = StrOp.split(toWork, ",", "", "", '(', ')');
            if (validateSplit(toWork, array.length - 1) == false) {
                    toReturn = false;
            }
            for (int i = 0; toReturn && i < array.length; i++) {
                String string = array[i];
                if (cv.Verify(string) == false) {
                    toReturn = false;
                }
            }
        }
        return toReturn;
    }//otro comm

    private String extractOpt(final String toValidate) {
        String toReturn = null;
        int init = 1;
        int end = toValidate.length() - 1;
        char ec = toValidate.charAt(toValidate.length() - 1);
        if (ec == '*' || ec == '+' || ec == '?') {
            toReturn = toValidate.substring(init, end - 1);
        } else if (ec == ')') {
            toReturn = toValidate.substring(init, end);
        }
        return toReturn;
    }

    private boolean validateSplit(final String str, final int i) {
        return DTDTool.getNumberOutOfPar(str, ',') == i;
    }
}
