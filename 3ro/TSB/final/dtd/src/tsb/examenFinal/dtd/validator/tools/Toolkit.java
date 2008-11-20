package tsb.examenFinal.dtd.validator.tools;

import tsb.examenFinal.util.StrCmp;

/**
 *
 * retorna el tipo de dato de un contenido variable o wawawa
 */
public abstract class Toolkit {

    public static final int PCDATA = 1;
    public static final int CDATA = 0;
    public static final int NON_TYPE = 2;

    public static int getDataType(String toValidate) {
        int toReturn = Toolkit.CDATA;
        if (StrCmp.hasOnlyAlphaNum(toValidate) == true) {
            toReturn = Toolkit.PCDATA;
        }
        return toReturn;
    }
}
