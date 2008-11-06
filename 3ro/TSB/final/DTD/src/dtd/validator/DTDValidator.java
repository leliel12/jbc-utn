/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.validator;

import dtd.exception.DTDException;
import dtd.syntax.DTDSyntaxValidator;
import dtd.validator.attlist.Variable;
import dtd.validator.attlist.AttListValidator;
import dtd.validator.element.ElementValidator;
import dtd.validator.tools.Box;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author juan
 */
public class DTDValidator {

    private ArrayList<ItemValidator> rules;
    private Stack<Box> stk;

    public DTDValidator() {
        rules = new ArrayList<ItemValidator>();
        stk = new Stack<Box>();
    }

    public synchronized void validateStartDocument() throws DTDException {
    }

    public synchronized void validateEndDocument() throws DTDException {
        boolean valid = stk.empty();
        if (!valid) {
            throw new DTDException("INVALID END DOCUMENT");
        }
    }

    public synchronized void validateStartElement(String name, String read, Variable[] vars) throws DTDException {
        boolean valid = false;
        //  AttListValidator val;
        stk.add(new Box());
        String in = getRule(name, true, DTDSyntaxValidator.ATTLIST);
        String ex = getRule(name, false, DTDSyntaxValidator.ATTLIST);
        if (in != null && new AttListValidator(in).validate(vars)) {
            valid = true;
        } else if (ex != null && new AttListValidator(ex).validate(vars)) {
            valid = true;
        } else if (vars == null && in == null && ex == null) {
            valid = true;
        }
        if (!valid) {
            String emsg = "INVALID START ELEMENT: " + name + " " + read;
            for (int i = 0; vars != null && i < vars.length; i++) {
                if (i == 0) {
                    emsg = "\nVARIABLES:\n\t";
                }
                Variable variable = vars[i];
                emsg += variable.toString() + "\n\t";
            }
            throw new DTDException(emsg);
        }
    }

    public synchronized void validateEndElement(String name, String read) throws DTDException {
        boolean valid = false;
        Box box = stk.pop();
        String in = getRule(name, true, DTDSyntaxValidator.ELEMENT);
        String ex = getRule(name, false, DTDSyntaxValidator.ELEMENT);
        if (in != null && new ElementValidator(box, in).validate()) {
            valid = true;
        } else if (ex != null && new ElementValidator(box, ex).validate()) {
            valid = true;
        }
        if (stk.isEmpty() == false) {
            stk.peek().addValidateElement(name, Box.ELEMENT);
        }
        if (!valid) {
            throw new DTDException("INVALID END ELEMENT: " + name + " " + read);
        }
    }

    public synchronized void validateInsertContent(String content) throws DTDException {
        stk.peek().addValidateElement(content, Box.CONTENT);
    }

    public synchronized void addRule(String rule, boolean isInternal, int type) throws DTDException {
        boolean exist = false;
        ItemValidator itemValidator = null;
        String name = rule.split(" ")[1];
        for (Iterator<ItemValidator> it = rules.iterator(); exist == false && it.hasNext();) {
            itemValidator = it.next();
            if (itemValidator.getName().equals(name)) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            itemValidator = new ItemValidator(name);
            rules.add(itemValidator);
        }
        itemValidator.addRule(rule, isInternal, type);
    }

    private String getRule(String name, boolean isInternal, int type) {
        String toReturn = null;
        for (Iterator<ItemValidator> it = rules.iterator(); toReturn == null && it.hasNext();) {
            ItemValidator itemValidator = it.next();
            if (itemValidator.getName().equals(name)) {
                if (type == DTDSyntaxValidator.ATTLIST) {
                    toReturn = itemValidator.getAttListRule(isInternal);
                } else {
                    toReturn = itemValidator.getElementRule(isInternal);
                }
            }
        }
        return toReturn;
    }
}
