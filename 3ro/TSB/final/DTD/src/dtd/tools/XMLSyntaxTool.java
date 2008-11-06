/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dtd.tools;

/**
 *
 * @author juan
 */
public abstract class XMLSyntaxTool {

    public static boolean validateTagName(final String str, final String init, final String end) {
        boolean ok = true;
        int countNames = 0;
        String str0 = StrOp.removeInitEnd(str, init, end);
        if (str0.charAt(0) == ' ') {
            ok = false;
        }
        String[] array = StrOp.split(str, " ", init, end, '"', '"');
        if (array[0].indexOf('"') != -1) {
            ok = false;
        }
        for (int i = 1; i < array.length; i++) {
            String string = array[i];
            if (string.indexOf('=') != -1) {
                countNames++;
            }
        }
        if (countNames > 1) {
            ok = false;
        }
        return ok;
    }
    
}
