/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.syntax.element;

import dtd.syntax.tools.DTDTool;
import dtd.tools.StrCmp;
import dtd.tools.StrOp;

/**
 *
 * @author juan
 */
public class ComponentSyntaxValidator {

    private boolean pcdata;

    public boolean Verify(final String component) {
        pcdata = false;
        boolean toReturn = true;
        String[] array = StrOp.split(component, "[|]", "", "", '(', ')');
        if (validateSplit(array.length - 1, component) == false) {
            toReturn = false;
        }
        if (validateFirstAndUniquePCDATA(array) == false) {
            toReturn = false;
        }
        if (pcdata && toReturn) {
            array = removeFirst(array);
        }
        for (int i = 0; i < array.length; i++) {
            String string = array[i];
            if (isList(string) == true) {
                toReturn = toReturn && validateList(string);
            } else {
                toReturn = toReturn && validateElement(string);
            }
        }
        return toReturn;
    }

    private boolean isList(final String string) {
        boolean toReturn = true;
        char init = string.charAt(0);
        char end = string.charAt(string.length() - 1);
        if (end == '*' || end == '?' || end == '+') {
            end = string.charAt(string.length() - 2);
        }
        if (init != '(' && end != ')') {
            toReturn = false;
        }
        return toReturn;
    }

    private String[] removeFirst(final String[] array) {
        String[] toReturn = new String[array.length - 1];
        for (int i = 0; i < toReturn.length; i++) {
            toReturn[i] = array[i + 1];
        }
        return toReturn;
    }

    private boolean validateElement(final String string) {
        boolean toReturn = true;
        if (StrCmp.hasOnlyAlphaNumAndDashes(string) == false) {
            char end = string.charAt(string.length() - 1);
            String toWork = string.substring(0, string.length() - 1);
            if (StrCmp.hasOnlyAlphaNumAndDashes(toWork) == false) {
                toReturn = false;
            } else if (end != '*' && end != '?' && end != '+') {
                toReturn = false;
            }
        }
        return toReturn;
    }

    private boolean validateFirstAndUniquePCDATA(final String[] array) {
        boolean toReturn = true;
        for (int i = 0; i < array.length; i++) {
            String string = array[i];
            if ((string.equals("#PCDATA") && i != 0) || (string.equals("#PCDATA") && pcdata == true)) {
                toReturn = false;
            }
            if (string.equals("#PCDATA")) {
                pcdata = true;
            }
        }
        if (toReturn && pcdata) {
            toReturn = toReturn && validateInSublists(array);
        }
        return toReturn;
    }

    private boolean validateInSublists(final String[] array) {
        boolean toReturn = true;
        for (int i = 1; i < array.length; i++) {
            String string = array[i];
            if (StrCmp.isInitEnd(string, "(", ")") == true) {
                string = string.split(",")[0].split("[|]")[0];
                string = string.replaceAll("[(]", " ").trim();
                if (array[0].equals(string)) {
                    toReturn = false;
                }
            }
        }
        return toReturn;
    }

    private boolean validateList(final String string) {
        ListSyntaxValidator lv = new ListSyntaxValidator();
        return lv.verify(string);
    }

    private boolean validateSplit(int i, String component) {
        return DTDTool.getNumberOutOfPar(component, '|') == i;
    }
}
