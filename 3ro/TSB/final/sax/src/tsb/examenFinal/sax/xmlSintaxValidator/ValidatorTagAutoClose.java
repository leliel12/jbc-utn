package tsb.examenFinal.sax.xmlSintaxValidator;

import tsb.examenFinal.util.Balance;
import tsb.examenFinal.util.StrCmp;
import tsb.examenFinal.util.StrOp;

/**
 *
 * verifica que el tag xml leido corresponde a un tag que se cierra solo
 */
class ValidatorTagAutoClose implements ValidatorModel {

    private String str;
    private ValidatorVariables varVal;

    public ValidatorTagAutoClose() {
        this.varVal = new ValidatorVariables();
    }

    public boolean validate(String str) {
        boolean ok = true;
        this.str = str;
        this.varVal.clear();
        try {
            if (StrCmp.isInitEnd(str, "<", "/>") == false) {
                ok = false;
            } else if (StrCmp.isEmpty(str, "<", "/>") == true) {
                ok = false;
            } else if (Balance.isBalanced(str, '"', '"') == false) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "<", 1, true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, ">", 1, true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "?", 0, true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "-", 0, true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "!", 0, true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "/", 1, true) == true) {
                ok = false;
            } else if (this.variablesOK() == false) {
                ok = false;
            } else if (StrCmp.validateTagName(str, "<", "/>") == false) {
                ok = false;
            }
        } catch (Exception e) {
            ok = false;
        } finally {
            return ok;
        }
    }

    private boolean variablesOK() {
        boolean ok0 = true;
        String[] array = StrOp.split(str, " ", "<", "/>", '"', '"');
        for (int i = 0; i < array.length; i++) {
            String string = array[i];
            if (i != 0 && string != null && string.isEmpty() == false) {
                boolean ok1 = this.varVal.validate(string);
                if (ok1 == false) {
                    ok0 = false;
                    i = array.length;
                }
            }
        }
        return ok0;
    }
}
