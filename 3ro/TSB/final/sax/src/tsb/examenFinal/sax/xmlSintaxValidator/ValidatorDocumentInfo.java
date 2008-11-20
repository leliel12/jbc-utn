package tsb.examenFinal.sax.xmlSintaxValidator;

import tsb.examenFinal.util.Balance;
import tsb.examenFinal.util.StrCmp;
import tsb.examenFinal.util.StrOp;

/**
 *
 * verifica que el tag xml leido corresponde a la informacion del formato de archivo
 */
class ValidatorDocumentInfo implements ValidatorModel {

    private String str;
    private ValidatorVariables varVal;

    public ValidatorDocumentInfo() {this.varVal = new ValidatorVariables();
    }

    
    
    public boolean validate(String str) {
        Boolean ok = true;
        this.varVal.clear();
        try {
            this.str = str;
            if (StrCmp.isInitEnd(str, "<?xml", "?>") == false) {
                ok = false;
            } else if (Balance.isBalanced(str, '"', '"') == false) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "<", 1,true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, ">", 1,true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "?", 2,true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "-", 0,true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "!", 0,true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "/", 0,true) == true) {
                ok = false;
            } else if (this.variablesOK() == false) {
                ok = false;
            } else if (StrCmp.validateTagName(str, "<?xml", "?>") == true) {
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
        String[] array = StrOp.split(str, " ", "<?xml", "?>", '"', '"');
        for (int i = 0; i < array.length; i++) {
            String string = array[i];
            if (string != null && string.isEmpty() == false) {
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
