/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.validator;

import dtd.exception.DTDException;
import dtd.syntax.DTDSyntaxValidator;

/**
 *
 * @author juan
 */
public class ItemValidator {

    private String name;
    private String element_interal;
    private String element_external;
    private String attlist_internal;
    private String attlist_external;

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
    
    public String getElementRule(boolean isInternal){
        String toReturn = this.element_external;
        if (isInternal) {
            toReturn = this.element_interal;
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

    private void setElement(String elementString, boolean isInternal) throws DTDException {
        if (isInternal) {
            if (this.element_interal != null) {
                throw new DTDException("Element Exists");
            } else {
                this.element_interal = elementString;
            }
        } else {
            if (this.element_external != null) {
                throw new DTDException("Element Exists");
            } else {
                this.element_external = elementString;
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
}
