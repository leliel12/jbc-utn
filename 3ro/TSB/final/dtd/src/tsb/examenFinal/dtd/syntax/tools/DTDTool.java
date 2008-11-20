package tsb.examenFinal.dtd.syntax.tools;

import tsb.examenFinal.util.Balance;
import tsb.examenFinal.util.StrOp;
import java.util.Stack;

/**
 *
 * algunos metodos comunes para la validacion sintactica de un dtd
 */
public abstract class DTDTool {

    public static int getNumberOutOfPar(final String str, final char c) {
        int n = -1;
        boolean balanced = Balance.isBalanced(str, '(', ')');
        if (balanced) {
            n = 0;
        }
        char[] array = str.toCharArray();
        Stack<Character> stk = new Stack<Character>();
        for (int i = 0; balanced && i < array.length; i++) {
            char d = array[i];
            if (d == '(') {
                stk.push(d);
            }
            if (stk.isEmpty() == false && d == ')') {
                if (stk.peek() == '(') {
                    stk.pop();
                } else {
                    stk.push(d);
                }
            }
            if (stk.isEmpty() && d == c) {
                n++;
            }
        }
        return n;
    }

    public static String extractList(final String str) {
        String toReturn = null;
        String workString = StrOp.removeInitEnd(str, "<!ELEMENT", ">");
        if (balancedAndUnique(workString) == true) {
            int close = -1;
            int open = -1;
            for (int i = 0; i < workString.length(); i++) {
                char c = workString.charAt(i);
                if (c == '(' && open == -1) {
                    open = i;
                }
                if (c == ')') {
                    close = i;
                }
            }
            try {
                if (workString.charAt(close + 1) == '*' || workString.charAt(close + 1) == '?' || workString.charAt(close + 1) == '+') {
                    close += 2;
                } else {
                    close++;
                }
            } catch (IndexOutOfBoundsException ex) {
                close++;
            }
            toReturn = workString.substring(open, close);
        }
        return toReturn;
    }

    private static boolean balancedAndUnique(String str) {
        boolean toReturn = true;
        Stack<String> stk = new Stack<String>();
        int root = 0;
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            if (s.equals("(") == true) {
                stk.push(s);
            } else if (s.equals(")") == true) {
                if (stk.isEmpty() == false &&stk.peek().equals("(") == true) {
                    stk.pop();
                    if (stk.isEmpty()) {
                        root++;
                    }
                } else {
                    stk.push(s);
                }
            }
        }
        toReturn = stk.isEmpty() && (root == 1);
        return toReturn;
    }
}
