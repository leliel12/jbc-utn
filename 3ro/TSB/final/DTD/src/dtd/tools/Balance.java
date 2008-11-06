/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.tools;

import java.util.Stack;

/**
 *
 * @author juan
 */
public abstract class Balance {

    public static boolean isBalanced(final String str, final char c0, final char c1) {
        boolean toReturn;
        if (c0 == c1) {
            toReturn = isSameBalanced(str, c0);
        } else {
            toReturn = isNotSameBalanced(str, c0, c1);
        }
        return toReturn;
    }

    private static boolean isSameBalanced(final String str, final char c0) {
        char[] array = str.toCharArray();
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            if (c == c0) {
                count++;
            }
        }
        int proc = (count / 2) * 2;
        return (count == proc);
    }

    private static boolean isNotSameBalanced(final String str, final char c0, final char c1) {
        char[] array = str.toCharArray();
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            if (c == c0) {
                stack.add(String.valueOf(c));
            } else if (c == c1) {
                if (stack.isEmpty() == false && stack.peek().charAt(0) == c0) {
                    stack.pop();
                } else {
                    stack.push(String.valueOf(c));
                }
            }
        }
        return stack.isEmpty();
    }
}
