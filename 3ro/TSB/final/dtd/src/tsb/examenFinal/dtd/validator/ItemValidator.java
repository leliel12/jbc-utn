/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb.examenFinal.dtd.validator;

import tsb.examenFinal.dtd.DTDException;
import tsb.examenFinal.dtd.syntax.DTDSyntaxValidator;

/**
 *
 * contiene las reglas correspondientes a un elemento
 */
public class ItemValidator {

    private String name;
    private String element_interal;
    private String element_external;
    private String attlist_internal;
    private String attlist_external;
    //banderas correspondientes a identificar si las reglas fueron utilizadas
    private boolean used_element_internal;
    private boolean used_element_external;
    //banderas correspondientes a identificar si las reglas deben usarse
    private boolean mustBe_element_internal;
    private boolean mustBe_element_external;
    private boolean empty_internal;
    private boolean empty_external;
    private boolean ast;

    public ItemValidator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean validate(String toValidate) {
        return true;
    }

    public String getAttListRule(boolean isInternal) {
        String toReturn = this.attlist_external;
        if (isInternal) {
            toReturn = this.attlist_internal;
        }
        return toReturn;
    }

    public String getElementRule(boolean isInternal) {
        String toReturn = null;
        if (isInternal) {
            toReturn = this.element_interal;
            this.used_element_internal = (toReturn != null);
        } else {
            toReturn = this.element_external;
            this.used_element_external = (toReturn != null);
        }
        return toReturn;
    }

    public void addRule(String rule, boolean isInternal, int type) throws DTDException {
        switch (type) {
            case DTDSyntaxValidator.ATTLIST:
                this.setAttlist(rule, isInternal);
                break;
            case DTDSyntaxValidator.ELEMENT:
                this.setElement(rule, isInternal);
                break;
            default:
                break;
        }
        showMsg(rule, isInternal, type);
    }

    private boolean getMustBeUsed(String elementString) {
        boolean toReturn = false;
        try {
            char c = ' ';
            for (int i = elementString.length() - 2; i >= 0 && c == ' '; i--) {
                c = elementString.charAt(i);
            }
            switch (c) {
                case '+':
                    toReturn = true;
                    break;
                case ')':
                    toReturn = true;
                    break;
                case '*':
                    this.ast = true;
                    break;
                default:
                    toReturn = false;
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            toReturn = false;
        }
        return toReturn;
    }

    private boolean getEmptyFlag(String elementString) {
        return (elementString.indexOf("EMPTY") != -1);
    }

    private void setElement(String elementString, boolean isInternal) throws DTDException {
        if (isInternal) {
            if (this.element_interal != null) {
                throw new DTDException("Element Exists");
            } else {
                this.element_interal = elementString;
                this.mustBe_element_internal = getMustBeUsed(elementString);
                this.empty_internal = getEmptyFlag(elementString);
            }
        } else {
            if (this.element_external != null) {
                throw new DTDException("Element Exists");
            } else {
                this.element_external = elementString;
                this.mustBe_element_external = getMustBeUsed(elementString);
                this.empty_external = getEmptyFlag(elementString);
            }
        }
    }

    private void setAttlist(String attlistString, boolean isInternal) throws DTDException {
        if (isInternal) {
            if (this.attlist_internal != null) {
                throw new DTDException("attList Exists");
            } else {
                this.attlist_internal = attlistString;
            }
        } else {
            if (this.attlist_external != null) {
                throw new DTDException("attList Exists");
            } else {
                this.attlist_external = attlistString;
            }
        }
    }

    private void showMsg(String rule, boolean internal, int type) {
        String tn = ">>>>>>>>>>>>> " + this.name;
        if (internal) {
            tn += " (INTERNAL ";
        } else {
            tn += "(EXTERNAL ";
        }
        switch (type) {
            case DTDSyntaxValidator.ATTLIST:
                tn += "ATTLIST RULE) ";
                break;
            case DTDSyntaxValidator.ELEMENT:
                tn += "ELEMENT RULE) ";
                break;
            default:
                break;
        }
        tn += rule;
        System.out.println(tn);
    }

    public boolean correctlyUsedRule() {
        boolean toReturn = true;

        if (!this.empty_internal && !this.empty_external) {
            toReturn = ((this.mustBe_element_internal == this.used_element_internal) &&
                    (this.mustBe_element_external == this.used_element_external))||(this.ast);
        } else {
            toReturn = true;
        }
        return toReturn;
    }
}
