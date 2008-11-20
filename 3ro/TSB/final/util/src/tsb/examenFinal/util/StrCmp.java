package tsb.examenFinal.util;

import java.util.regex.Pattern;

/**
 *
 * Metodos para comparar cadenas
 */
public abstract class StrCmp {

    private static final int EQUAL = 0;
    private static final int LESS = 1;
    private static final int MORE = 2;

    public static boolean hasOnlyAlphaNumDashesAndSpaces(final String toValidate) {
        boolean toReturn= true;
        String[] toWork = toValidate.split(" ");
        for (int i = 0; i < toWork.length; i++) {
            String string = toWork[i].trim();
            toReturn=toReturn && StrCmp.hasOnlyAlphaNumAndDashes(string);
        }
        return toReturn;
    }
    public static boolean hasOnlyAlphaNumAndDashes(final String toValidate) {
        return StrCmp.validateRegex(toValidate, "^[A-Za-z0-9_\\-]+$");
    }
    
    public static boolean hasOnlyAlphaNum(final String toValidate) {
        return StrCmp.validateRegex(toValidate, "^[A-Za-z0-9]+$");
    }

    public static boolean validateTagName(final String str, final String init, final String end) {
        String toWork=StrOp.removeInitEnd(str, init, end).split(" ")[0];
        return StrCmp.hasOnlyAlphaNumAndDashes(toWork);
    }

    private static boolean validateRegex(final String toValidate, final String regex) {
        Pattern p = Pattern.compile(regex);
        return p.matcher(toValidate).matches();
    }

    public static boolean isInitEnd(final String str, final String init, final String end) {
        return (str.startsWith(init) && str.endsWith(end));
    }

    public static boolean hasMoreThan(final String str, final String chars, final int value, final boolean ignoreCase) {
        return StrCmp.evaluateNumber(str, chars, value, ignoreCase, StrCmp.MORE);
    }

    public static boolean isEmpty(final String str, final String init, final String end) {
        return StrOp.removeInitEnd(str, init, end).isEmpty();
    }

    public static boolean hasLessThan(final String str, final String chars, final int value, final boolean ignoreCase) {
        return StrCmp.evaluateNumber(str, chars, value, ignoreCase, StrCmp.LESS);
    }

    public static boolean hasEqualThan(final String str, final String chars, final int value, final boolean ignoreCase) {
        return StrCmp.evaluateNumber(str, chars, value, ignoreCase, StrCmp.EQUAL);
    }

    private static boolean evaluateNumber(final String str, final String chars, final int value, final boolean ignoreCase, final int flag) {
        boolean toReturn = true;
        int[] array = StrCmp.getNumberOfCharsOutOfQuotes(str, chars, ignoreCase);
        for (int i = 0; i < array.length; i++) {
            int j = array[i];
            switch (flag) {
                case StrCmp.EQUAL:
                    toReturn = toReturn && (j == value);
                    break;
                case StrCmp.LESS:
                    toReturn = toReturn && (j < value);
                    break;
                case StrCmp.MORE:
                    toReturn = toReturn && (j > value);
                    break;
                default:
                    break;
            }
        }
        return toReturn;
    }

    private static int[] getNumberOfCharsOutOfQuotes(final String str, final String chars, final boolean ignoreCase) {
        int[] toReturn = new int[chars.length()];
        char[] array = null;
        String toWork = null;
        if (ignoreCase) {
            toWork = str.toLowerCase();
            array = chars.toLowerCase().toCharArray();
        } else {
            array = chars.toCharArray();
            toWork = str;
        }
        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            toReturn[i] = StrCmp.getNumberOutOfQuotes(toWork, c);
        }
        return toReturn;
    }
    
    private static int getNumberOutOfQuotes(final String str, char char0) {
        char[] array = str.toCharArray();
        boolean inQuote = false;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '"') {
                inQuote = !inQuote;
            }
            if (array[i] == char0 && inQuote == false) {
                count++;
            }
        }
        return count;
    }
}
