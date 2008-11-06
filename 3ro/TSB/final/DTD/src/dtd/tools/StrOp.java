/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.tools;

import java.util.ArrayList;

/**
 *
 * @author juan
 */
public abstract class StrOp {

    public static String getNameOfTag(final String str, final String init, final String end) {
        String name = removeInitEnd(str, init, end);
        name = name.trim();
        name = name.split(" ")[0];
        return name;
    }

    public static int getNumberOf(final String str, final char c) {
        int toReturn = 0;
        for (int i = 0; i < str.toCharArray().length; i++) {
            char c1 = str.charAt(i);
            if (c1 == c) {
                toReturn++;
            }
        }
        return toReturn;
    }

    public static String removeInitEnd(final String str, final String init, final String end) {
        String nstr = null;
        try {
            int idx0 = init.length();
            int idx1 = str.length() - end.length();
            nstr = str.substring(idx0, idx1);
        } catch (IndexOutOfBoundsException ex) {
            nstr = str;
        } finally {
            return nstr;
        }
    }

    /**
     * Separa una cadena de caracteres por el <code>regex</code> sugerido
     * omitiendo si esta adentro de <code>initOmit</code> y <code>endOmit</code>
     * La cadena <code>ejemplo</code> con contenido <code>la;;"perra;;de" mi;;esposa</code>
     * al ejetutar<br>CommonTask.split(ejemplo, ";;",'','','"','"')
     * se generarian las cadenas <br> la - "perra;;de" mi - esposa 
     * @param str String a separar
     * @param regex patron por el cual separar
     * @param initToRemove inicio del string que se pretende elminar
     * @param endToRemove fin de la cadena que se pretende eliminar
     * @param initOmit comienzo de caracteres dentro de los que se omite una separacion
     * @param endOmit fin de caracteres dentro de los que se omite una separacion
     * @return array con las cadenas separadas
     */
    public static String[] split(final String str, String regex, final String initToRemove, final String endToRemove, final char initOmit, final char endOmit) {
        String temp = removeInitEnd(str, initToRemove, endToRemove).trim();
        String[] array = temp.split(regex);
        String rgx = getRegexchar(regex);
        array = removeNullOrEmpty(array);
        boolean isEnd = false;
        while (isEnd == false) {
            isEnd = true;
            for (int i = 0; i < array.length && isEnd == true; i++) {
                if (Balance.isBalanced(array[i], initOmit, endOmit) == false && i != array.length - 1) {
                    isEnd = false;
                    array[i] += rgx + array[i + 1];
                    array[i + 1] = null;
                    array = removeNullOrEmpty(array);
                }
            }
        }
        return array;
    }

    private static String getRegexchar(String string) {
        String toReturn = "";
        char[] a = string.toCharArray();
        for (int i = 0; i < a.length; i++) {
            char c = a[i];
            if (c != '[' && c != ']') {
                toReturn += String.valueOf(c);
            }
        }
        return toReturn;
    }

    private static String[] removeNullOrEmpty(String[] array) {
        ArrayList<String> vector = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            String string = array[i];
            if (string != null && string.isEmpty() == false) {
                vector.add(string.trim());
            }
        }
        return vector.toArray(new String[vector.size()]);
    }
}
