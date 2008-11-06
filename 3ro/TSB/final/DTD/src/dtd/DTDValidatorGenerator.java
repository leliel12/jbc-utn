/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd;

import dtd.exception.DTDException;
import dtd.syntax.DTDSyntaxValidator;
import dtd.tools.StrOp;
import dtd.validator.DTDValidator;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author juan
 */
public class DTDValidatorGenerator {

    private DTDSyntaxValidator syntaxValidator;
    private DTDValidator toGenerate_DTDValidator;
    private ArrayList<String> allRules;
    private boolean commit = false;
    private String ruleWithError;

    public DTDValidatorGenerator() {
        syntaxValidator = new DTDSyntaxValidator();
        toGenerate_DTDValidator = new DTDValidator();
        this.allRules = new ArrayList<String>();
        ruleWithError = null;
    }

    public synchronized void tryToGenerate(final String readString, final boolean isInternal) throws DTDException {
        int type = syntaxValidator.verify(readString);
        switch (type) {
            case DTDSyntaxValidator.ELEMENT:
                toGenerate_DTDValidator.addRule(readString, isInternal, DTDSyntaxValidator.ELEMENT);
                break;
            case DTDSyntaxValidator.ATTLIST:
                toGenerate_DTDValidator.addRule(readString, isInternal, DTDSyntaxValidator.ATTLIST);
                break;
            case DTDSyntaxValidator.DOC_INFO:
                break;
            case DTDSyntaxValidator.ERROR:
                throw new DTDException("Invalid Syntax: " + readString);
            default:
                throw new DTDException("DTD KERNEL PANIC!");
        }
        this.allRules.add(readString);
    }

    public DTDValidator getDTDValidator() throws DTDException {
        if (!commit) {
            throw new DTDException("DTDValidator didn't commit");
        } 
        return this.toGenerate_DTDValidator;
    }

    public synchronized boolean commit() throws DTDException{
        commit = true;
        for (Iterator<String> it = allRules.iterator(); commit && it.hasNext();) {
            String rule = it.next();
            if (rule.startsWith("<!ELEMENT")) {
                commit = findParentOfComponentOfElement(rule);
            } else if (rule.startsWith("<!ATTLIST")) {
                commit = findParentOFAttlist(rule);
            }
        }
        if(!commit){
            this.ruleWithError="Not Find Parent: " + this.ruleWithError;
             throw new DTDException(this.ruleWithError);
        }
        return commit;
    }

    private boolean findParentOFAttlist(String rule) {
        boolean toReturn = false;
        String name = rule.split(" ")[1].trim();
        for (Iterator<String> it = allRules.iterator(); toReturn == false && it.hasNext();) {
            String rElement = it.next();
            if (rElement.startsWith("<!ELEMENT")) {
                String nameElement = rElement.split(" ")[1].trim();
                toReturn = (nameElement.equals(name));
            }
        }
        return toReturn;
    }

    private boolean findParentOfComponentOfElement(String rule) {
        boolean toReturn = true;
        String myName = rule.split(" ")[1].trim();
        if (rule.indexOf('(') != -1 && rule.indexOf(')') != -1) {
            String[] listOfRules = getListCommponentsOfRule(rule);
            for (int i = 0; toReturn == true && i < listOfRules.length; i++) {
                String lr = listOfRules[i];
                if (lr.equals("#PCDATA") == false) {
                    toReturn = (findListComponentParent(lr, myName));
                }
            }
        }
        if (!toReturn) {
            this.ruleWithError += rule;
        }
        return toReturn;
    }

    private boolean findListComponentParent(String nameToSeek, String myName) {
        boolean toReturn = false;
        for (Iterator<String> it = allRules.iterator(); toReturn == false && it.hasNext();) {
            String rule = it.next();
            String name = rule.split(" ")[1].trim();
            toReturn = (rule.startsWith("<!ELEMENT") && name.equals(myName) == false && name.equals(nameToSeek));
        }
        if (!toReturn) {
            this.ruleWithError = "\"" + nameToSeek + "\" in rule ";
        }
        return toReturn;
    }

    private String[] getListCommponentsOfRule(String rule) {
        ArrayList<String> toReturn = new ArrayList<String>();
        String list = StrOp.split(rule, " ", "<!ELEMENT", ">", '(', ')')[1];
        if (list.charAt(list.length() - 1) != ')') {
            list = list.substring(0, list.length() - 1);
        }
        String[] il = StrOp.split(list, ",", "(", ")", '(', ')');
        for (int i = 0; i < il.length; i++) {
            String string = il[i].trim();
            if (string.indexOf('|') == -1) {
                toReturn.add(string);
            } else {
                String[] il2 = string.split("[|]");
                for (int j = 0; j < il2.length; j++) {
                    String string1 = il2[j].trim();
                    toReturn.add(string1);
                }//for
            }//else
        }//for
        return toReturn.toArray(new String[toReturn.size()]);
    }//get
}
