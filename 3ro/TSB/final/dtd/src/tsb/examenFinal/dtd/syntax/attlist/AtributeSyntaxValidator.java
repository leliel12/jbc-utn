package tsb.examenFinal.dtd.syntax.attlist;

import tsb.examenFinal.dtd.dictionaries.Dictionaries;
import tsb.examenFinal.dtd.dictionaries.DictionaryModel;
import tsb.examenFinal.dtd.syntax.tools.DTDTool;
import tsb.examenFinal.util.StrCmp;
import tsb.examenFinal.util.StrOp;

/**
 *
 * Validada de la sintaxis de una relga de un atributo de un attlist
 */
class AtributeSyntaxValidator {

    private final int LIST = 0;
    private final int DATA_TYPE = 1;
    private final int ERROR = -1;
    private final int REQ = 2;
    private int typeW1;
    private int typeW2;
    private int typeW3;
    private Dictionaries dic;
    private String[] posibleValues;

    public AtributeSyntaxValidator() {
        dic = Dictionaries.getInstance();
    }

    public boolean verify(String[] rules) {
        boolean toReturn = true;
        for (int i = 0; i < rules.length; i++) {
            String rule = rules[i];
            if (this.validateRule(rule) == false) {
                toReturn = false;
                break;
            }
        }
        return toReturn;
    }

    private boolean validatePosibleValues() {
        boolean toReturn = true;
        for (int i = 0; i < posibleValues.length; i++) {
            String string = posibleValues[i];
            if (StrCmp.hasOnlyAlphaNumAndDashes(string) == false) {
                toReturn = false;
            }
        }
        return toReturn;
    }

    private boolean validateRule(String rule) {
        boolean toReturn = true;
        String[] list = StrOp.split(rule, " ", "", "", '(', ')');
        if (list.length == 3 || list.length == 4) {
            String w0 = list[0];
            String w1 = list[1];
            String w2 = list[2];
            String w3 = null;
            if (list.length == 4) {
                w3 = list[3];
            }
            if (validateW0(w0) == false) {
                toReturn = false;
            } else if (validateW1andW2(w1, w2) == false) {
                toReturn = false;
            } else if (w3 != null && validateW3(w3) == false) {
                toReturn = false;
            }
        } else {
            toReturn = false;
        }
        return toReturn;
    }

    private boolean validateSplit(final String str, int i) {
        String s = StrOp.removeInitEnd(str, "(", ")");
        return DTDTool.getNumberOutOfPar(s, '|') == i;
    }

    private boolean validateW0(String w0) {
        boolean toReturn = true;
        if (StrCmp.hasOnlyAlphaNumAndDashes(w0) == false) {
            toReturn = false;
        } else if (this.dic.exists(w0) == true) {
            toReturn = false;
        }
        return toReturn;
    }

    private boolean validateW1andW2(String w1, String w2) {
        boolean toReturn = true;
        if (validateW1(w1) == false) {
            toReturn = false;
        } else if (validateW2(w2) == false) {
            toReturn = false;
        }
        return toReturn;
    }

    private boolean validateW1(String w1) {
        boolean toReturn = true;
        this.typeW1 = getType(w1);
        if (this.typeW1 == this.ERROR) {
            toReturn = false;
        } else if (this.typeW1 == this.REQ) {
            toReturn = false;
        }
        if (this.typeW1 == this.LIST) {
            //solo se cargan los posibles valores si es del tipo list
            this.posibleValues = StrOp.split(w1, "[|]", "(", ")", '"', '"');
            if (validateSplit(w1, this.posibleValues.length - 1) == false) {
                toReturn = false;
            } else if (StrOp.getNumberOf(w1, '|') != this.posibleValues.length - 1) {
                toReturn = false;
            } else if (this.posibleValues != null && validatePosibleValues() == false) {
                toReturn = false;
            }
        }
        return toReturn;
    }

    private boolean validateW2(String w2) {
        boolean toReturn = true;
        this.typeW2 = this.getType(w2);
        if (this.typeW2 == this.ERROR) {
            toReturn = false;
        } else if (this.typeW1 == this.DATA_TYPE && this.typeW2 != this.REQ) {
            toReturn = false;
        } else if (this.typeW1 == this.LIST && validateDefault(w2) == false) {
            toReturn = false;
        }
        return toReturn;
    }

    private boolean validateW3(String w3) {
        boolean toReturn = true;
        this.typeW3 = this.getType(w3);
        if (w3 != null) {
            if (this.typeW3 == this.ERROR) {
                toReturn = false;
            } else if (this.typeW3 != this.REQ) {
                toReturn = false;
            } else if (this.typeW2 == this.REQ) {
                toReturn = false;
            }
        }
        return toReturn;
    }

    private boolean validateDefault(String w2) {
        boolean toReturn = true;
        if (this.typeW2 != this.REQ) {
            toReturn = false;
            for (int i = 0; i < posibleValues.length; i++) {
                String string = posibleValues[i];
                if (string.equals(w2) == true) {
                    toReturn = true;
                    break;
                }
            }
        }
        return toReturn;
    }

    private int getType(String w) {
        int toReturn = this.ERROR;
        boolean b = true;
        b = (toReturn == this.ERROR);
        b = b && StrCmp.hasEqualThan(w, "()", 1, true);
        b = b && (this.dic.exists(w) == false);
        if (b == true) {
            toReturn = this.LIST;
        }
        b = (toReturn == this.ERROR);
        b = b && dic.getDictionaryType(w) == DictionaryModel.ATT_OPTIONALITY;
        if (b == true) {
            toReturn = this.REQ;
        }
        b = (toReturn == this.ERROR);
        b = b && dic.getDictionaryType(w) == DictionaryModel.DATA_TYPE;
        if (b == true) {
            toReturn = this.DATA_TYPE;
        }
        return toReturn;
    }
}
