package tsb.examenFinal.dtd.syntax.attlist;

import tsb.examenFinal.dtd.dictionaries.Dictionaries;
import tsb.examenFinal.dtd.syntax.SyntaxValidatorModel;
import tsb.examenFinal.util.StrCmp;
import tsb.examenFinal.util.StrOp;
import java.util.ArrayList;

/**
 *
 * valida la sintaxis de un attlist
 */
public class AttListSyntaxValidator implements SyntaxValidatorModel {

    private Dictionaries dic;
    private AtributeSyntaxValidator adg;

    public AttListSyntaxValidator() {
        dic = Dictionaries.getInstance();
        adg = new AtributeSyntaxValidator();
    }

    public boolean verify(String str) {
        boolean toReturn = true;
        if (StrCmp.isEmpty(str, "<!ATTLIST", ">") == true) {
            toReturn = false;
        } else if (StrCmp.isInitEnd(str, "<!ATTLIST", ">") == false) {
            toReturn = false;
        } else if (StrCmp.hasMoreThan(str, "><", 1, true) == true) {
            toReturn = false;
        } else if (validateName(str) == false) {
            toReturn = false;
        } else if (validateSyntax(str) == false) {
            toReturn = false;
        }
        return toReturn;
    }

    private boolean validateName(String str) {
        boolean toReturn = true;
        String name = StrOp.split(str, " ", "<!ATTLIST", ">", '(', ')')[0];
        if (StrCmp.hasOnlyAlphaNumAndDashes(name) == false) {
            toReturn = false;
        }
        if (dic.exists(name) == true) {
            toReturn = false;
        }
        return toReturn;
    }

    private boolean validateSyntax(String str) {
        boolean toReturn = true;
        String[] list = getDefinitions(str);
        if (list == null || list.length == 0) {
            toReturn = false;
        } else if (this.adg.verify(list) == false) {
            toReturn = false;
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
}
