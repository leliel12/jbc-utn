package tsb.examenFinal.dtd.validator.element;

import tsb.examenFinal.util.StrOp;
import tsb.examenFinal.dtd.validator.tools.Box;
import tsb.examenFinal.dtd.dictionaries.*;
import tsb.examenFinal.util.Balance;
import tsb.examenFinal.util.Queue;
import tsb.examenFinal.util.StrCmp;
import tsb.examenFinal.dtd.validator.tools.Box.BoxContent;

/**
 *
 * validada un elemento xml contra su regla dtd
 */
public class ElementValidator {

    public static int ONLY_ONE = 0;
    public static int EMPTY = 1;
    public static int ANY = 2;
    public static int NONE = 3;
    private int elementCardinality; //0123
    public Queue<BoxContent> boxContent;
    private String[] rules;
    private String opt; //?*+
    private Dictionaries dic;
    private int cant;

    public ElementValidator(final Box box, final String dtdRule) {
        this.dic = Dictionaries.getInstance();
        this.setOptionality(dtdRule);
        this.setElementCardinality(dtdRule);
        this.setRules(dtdRule);
        this.setContent(box);
        if (this.rules == null) {
            cant = 0;
        } else {
            cant = this.boxContent.size() / this.rules.length;
        }
    }

    private void setOptionality(final String dtdRule) {
        String listOfSubElements = StrOp.split(dtdRule, " ", "<!ELEMENT", ">", '(', ')')[1];
        this.opt = listOfSubElements.substring(listOfSubElements.length() - 1);
        if (dic.getDictionaryType(this.opt) != DictionaryModel.SYMBOL) {
            this.opt = null;
        }
    }//set optionality

    private void setElementCardinality(final String dtdRule) {
        int idx = dtdRule.replaceAll(">", " ").split(" ").length - 1;
        String cardStr = dtdRule.replaceAll(">", " ").split(" ")[idx];
        if (cardStr.equals("ANY")) {
            this.elementCardinality = ElementValidator.ANY;
        } else if (cardStr.equals("EMPTY")) {
            this.elementCardinality = ElementValidator.EMPTY;
        } else {
            this.elementCardinality = ElementValidator.ONLY_ONE;
        }
    }//setElementCardinality

    private void setContent(final Box box) {
        this.boxContent = new Queue<BoxContent>();
        Box.BoxContent[] cnt = box.getElements();
        for (int i = 0; i < cnt.length; i++) {
            this.boxContent.add(cnt[i]);
        }
    }//setContent

    private void setRules(final String dtdRule) {
        String list = StrOp.split(dtdRule, " ", "<!ELEMENT", ">", '(', ')')[1];
        if (this.opt != null) {
            list = list.substring(0, list.length() - 1);
        }
        if (list.indexOf('(') != -1 && list.indexOf(')') != -1) {
            this.rules = StrOp.split(list, ",", "(", ")", '(', ')');
        } else {
            this.rules = null;
        }
    }//setRules

    private boolean validateCantidad() {
        boolean toReturn = true;
        if (cant != 0 || this.elementCardinality != ElementValidator.EMPTY) {
            if (rules != null && cant * this.rules.length != this.boxContent.size()) {
                toReturn = false;
            } else {
                if (opt == null) {
                    toReturn = (cant == 1);
                } else if (opt.equals("*")) {
                    toReturn = (cant >= 0);
                } else if (opt.equals("?")) {
                    toReturn = (cant == 0 || cant == 1);
                } else if (opt.equals("+")) {
                    toReturn = (cant >= 1);
                }
            }
        }
        return toReturn;
    }//validateCantidad

    @SuppressWarnings("unchecked")
    public boolean validate() {
        boolean toReturn = true;
        if (elementCardinality != ElementValidator.ANY || (elementCardinality != ElementValidator.EMPTY && boxContent.isEmpty())) {
            if (validateCantidad() == false) {
                toReturn = false;
            } else {
                Queue<BoxContent>[] groups = new Queue[cant];
                for (int i = 0; toReturn == true && i < groups.length; i++) {
                    groups[i] = new Queue<BoxContent>();
                    for (int j = 0; j < rules.length; j++) {
                        groups[i].push(boxContent.pop());
                    }
                    toReturn = validateGroup(groups[i]);
                }
            }
        }
        return toReturn;
    }

    private boolean validateGroup(final Queue<BoxContent> subGroup) {
        boolean toReturn = true;
        for (int i = 0; i < rules.length && toReturn; i++) {
            String rule = rules[i];
            BoxContent thing = subGroup.pop();
            toReturn = validateRule(rule, thing);
        }
        return toReturn;
    }

    private boolean validateRule(final String rule, final BoxContent thing) {
        boolean toReturn = true;
        String[] rdecomp = rule.replaceAll("[(]", " ").replaceAll("[)]", " ").trim().split("[|]");
        if (rdecomp[0].trim().equals("#PCDATA")) {
            if (validatePCDATA(thing) == false) {
                toReturn = false;
            }
        } else if (toReturn == true) {
            int i = 0;
            if (rdecomp[0].trim().equals("#PCDATA")) {
                i++; //corre uno el indice si ya utilizo #PCDATA
            }
            for (; i < rdecomp.length; i++) {
                toReturn = toReturn || (rdecomp[i].equals(thing.getContenido()) && thing.getTipoDato() == Box.ELEMENT);
            }
        }
        return toReturn;
    }

    private boolean validatePCDATA(final BoxContent thing) {
        boolean toReturn = true;
        String content = thing.getContenido();
        if (content.indexOf('"') != -1) {
            if (Balance.isBalanced(content, '"', '"')) {
                content = content.replace('"', ' ').trim();
            } else {
                toReturn = false;
            }
        }
        if (toReturn && thing.getTipoDato() != Box.CONTENT) {
            toReturn = false;
        } else if (toReturn && StrCmp.hasOnlyAlphaNumDashesAndSpaces(content.replace('@', '_').replace('.', '_')) == false) {
            toReturn = false;
        }
        return toReturn;
    }
}