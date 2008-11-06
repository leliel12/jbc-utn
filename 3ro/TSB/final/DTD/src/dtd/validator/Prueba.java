/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.validator;

import dtd.tools.StrOp;
import dtd.validator.tools.Box;

/**
 *
 * @author Administrador
 */
public class Prueba {

    public static boolean getCant(String rules, Box box) {
        boolean toReturn = true;
        String[] listRule;
        listRule = StrOp.split(rules, " ", "<ELEMENT", ">", '(', ')');
        if (listRule[1].indexOf('(') == -1 && box.getElements().length != 0) {
            toReturn = false;
        }
        listRule = StrOp.split(listRule[1], ",", "[(]", "[)]", '(', ')');
        for (int i = 0; i < listRule.length; i++) {
            String rule = listRule[i];
            char c = rule.charAt(rule.length() - 1);
            int cont = 0;
            String toCompare = rule;
            if (c == '?' || c == '*' || c == '+') {
                toCompare = toCompare.substring(0, toCompare.length() - 1);
            }
            for (int j = 0; j < box.getElements().length; j++) {
                String nombre = box.getElements()[j].getContenido();
                if (nombre.compareToIgnoreCase(toCompare) == 0) {
                    cont++;
                }
            }
            switch (c) {
                case '?':
                    toReturn = (cont == 0 || cont == 1);
                    break;
                case '*':
                    toReturn = (cont >= 0);
                    break;
                case '+':
                    toReturn = (cont >= 1);
                    break;
                default:
                    toReturn = (cont == 1);
                    break;
            }
        }//for
        return toReturn;
    }
}
