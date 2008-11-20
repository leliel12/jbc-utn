package tsb.examenFinal.dtd.syntax;

import tsb.examenFinal.dtd.syntax.element.ElementSyntaxValidator;
import tsb.examenFinal.dtd.syntax.attlist.AttListSyntaxValidator;
import tsb.examenFinal.dtd.syntax.docInfo.ValidatorDocumentInfo;

/**
 *
 * validador sintactico de un dtd
 */
public class DTDSyntaxValidator {

    public static final int ERROR = -1;
    public static final int ATTLIST = 0;
    public static final int ELEMENT = 1;
    public static final int ENTITY = 2;
    public static final int DOC_INFO = 3;
    private ElementSyntaxValidator eVer;
    private AttListSyntaxValidator aVer;
    private ValidatorDocumentInfo dVer;

    public DTDSyntaxValidator() {
        eVer = new ElementSyntaxValidator();
        aVer = new AttListSyntaxValidator();
        dVer = new ValidatorDocumentInfo();
    }

    public int verify(String toValidate) {
        int toReturn = DTDSyntaxValidator.ERROR;
        try {
            if (eVer.verify(toValidate)) {
                toReturn = DTDSyntaxValidator.ELEMENT;
            } else if (aVer.verify(toValidate)) {
                toReturn = DTDSyntaxValidator.ATTLIST;
            } else if (dVer.validate(toValidate)) {
                toReturn = DTDSyntaxValidator.DOC_INFO;
            }
        } catch (Exception e) {
            toReturn = DTDSyntaxValidator.ERROR;
        } finally {
          //  printMSG(toValidate, toReturn);
            return toReturn;
        }
    }

    private void printMSG(String rule, int type) {
        String toPrint = "";
        switch (type) {
            case DTDSyntaxValidator.ELEMENT:
                toPrint = rule + " (ELEMENT)";
                break;
            case DTDSyntaxValidator.ATTLIST:
                toPrint = rule + " (ATTLIST)";
                break;
            case DTDSyntaxValidator.ERROR:
                toPrint = rule + " (ERROR)";
                break;
            case DTDSyntaxValidator.DOC_INFO:
                toPrint = rule + " (XMLDOCINFO)";
                break;
            default:
                toPrint = rule + " (SHIT!!!!)";
                break;
        }
        System.out.println(toPrint);
    }
}
