package tsb.examenFinal.sax.xmlSintaxValidator;

import tsb.examenFinal.util.StrCmp;
import tsb.examenFinal.util.StrOp;

/**
 * verifica que el tag xml leido corresponde a un comentario
 */
class ValidatorCommentary implements ValidatorModel {

    private String str;

    public boolean validate(String str) {
        Boolean ok = true;
        this.str = str;
        try {
            if (StrCmp.isInitEnd(str, "<!--", "-->") == false) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "<", 1,true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, ">", 1,true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "!", 1,true) == true) {
                ok = false;
            } else if (validateDoubleDash() == false) {
                ok = false;
            }
        } catch (Exception e) {
            ok = false;
        } finally {
            return ok;
        }
    }

    private boolean validateDoubleDash() {
        char[] aux = StrOp.removeInitEnd(str, "<!--", "-->").toCharArray();
        boolean dash = false;
        boolean ok = true;
        for (int i = 0; i < aux.length; i++) {
            char c = aux[i];
            if (c == '-' && dash) {
                ok = false;
            } else if (c == '-' && !dash) {
                dash = true;
            } else if (c != '-' && dash) {
                dash = false;
            }
        }
        return ok;
    }
}
