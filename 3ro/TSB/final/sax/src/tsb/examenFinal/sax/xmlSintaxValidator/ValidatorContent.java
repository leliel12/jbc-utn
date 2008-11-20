package tsb.examenFinal.sax.xmlSintaxValidator;

import tsb.examenFinal.util.StrCmp;

/**
 *
 * verifica que el tag xml leido corresponde a contenido
 */
class ValidatorContent implements ValidatorModel {


    public boolean validate(String str) {
        boolean ok = true;
        try {
            if (StrCmp.isInitEnd(str, "<", ">") == true) {
                ok = false;
            } else if (StrCmp.hasEqualThan(str, ">", 0, true) == false) {
                ok = false;
            } else if (StrCmp.hasEqualThan(str, "<", 0,true) == false) {
                ok = false;
            }
        } catch (Exception e) {
            ok = false;
        } finally {
            return ok;
        }
    }
}
