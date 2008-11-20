/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb.examenFinal.sax.xmlSintaxValidator;

import tsb.examenFinal.util.StrCmp;
import tsb.examenFinal.util.StrOp;

/**
 *
 * verifica que el tag xml leido corresponde a un tag de final de elemtno
 */
class ValidatorTagEnd implements ValidatorModel {

    String str;

    public boolean validate(String str) {
        boolean ok = true;
        this.str = str;
        try {
            if (StrCmp.isInitEnd(str, "</", ">") == false) {
                ok = false;
            } else if (StrCmp.isEmpty(str, "</", ">") == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "<", 1,true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, ">", 1,true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, ">", 1,true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "?", 0,true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "-", 0,true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "!", 0,true) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "/", 1,true) == true) {
                ok = false;
            } else if (StrCmp.validateTagName(str, "</", ">") == false) {
                ok = false;
            } else if (validateNotVariable(str, "</", ">") == false) {
                ok = false;
            }
        } catch (Exception e) {
            ok = false;
        } finally {
            return ok;
        }
    }

    private boolean validateNotVariable(String str, String init, String end) {
        return StrOp.split(str, " ", init, end, '"', '"').length == 1;
    }
}
