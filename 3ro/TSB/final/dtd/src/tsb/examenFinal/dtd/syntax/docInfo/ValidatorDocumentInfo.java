package tsb.examenFinal.dtd.syntax.docInfo;

import tsb.examenFinal.util.Balance;
import tsb.examenFinal.util.StrCmp;
import tsb.examenFinal.util.StrOp;
import tsb.examenFinal.util.XMLSyntaxTool;



/**
 *
 * valida el docinfo de un dtd
 */
public class ValidatorDocumentInfo {

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
            } else if (StrCmp.hasMoreThan(str, "<>", 1,false) == true) {
                ok = false;
            
            } else if (StrCmp.hasMoreThan(str, "?", 2,false) == true) {
                ok = false;
            } else if (StrCmp.hasMoreThan(str, "-!/", 0,false) == true) {
                ok = false;
            } else if (this.variablesOK() == false) {
                ok = false;
            } else if (XMLSyntaxTool.validateTagName(str, "<?xml", "?>") == true) {
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
