/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.validator.tools;

import dtd.tools.StrCmp;

/**
 *
 * @author juan
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
