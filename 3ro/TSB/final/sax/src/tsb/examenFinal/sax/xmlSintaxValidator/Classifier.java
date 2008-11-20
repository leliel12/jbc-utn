package tsb.examenFinal.sax.xmlSintaxValidator;

/**
 * Valida el tipo de tag xml leido
 */
public class Classifier {

    /**
     * TAG_INIT = 0
     */
    public static final int TAG_INIT = 0;
    /**
     * TAG_END = 1
     */
    public static final int TAG_END = 1;
    public static final int TAG_AUTOCLOSE = 2;
    public static final int CONTENT = 3;
    public static final int XML_DOCUMENT_INFO = 4;
    public static final int COMMENTARY = 5;
    public static final int DTD_INFO = 6;
    public static final int INVALID = -1;
    /*
     * Variables Privadas
     */
    private ValidatorCommentary com;
    private ValidatorContent cont;
    private ValidatorDocumentInfo doc;
    private ValidatorTagAutoClose tclose;
    private ValidatorTagEnd tend;
    private ValidatorTagInit tinit;
    private ValidatorDTD dtdVal;

    public Classifier() {
        this.com = new ValidatorCommentary();
        this.cont = new ValidatorContent();
        this.doc = new ValidatorDocumentInfo();
        this.tclose = new ValidatorTagAutoClose();
        this.tend = new ValidatorTagEnd();
        this.tinit = new ValidatorTagInit();
        this.dtdVal = new ValidatorDTD();
    }

    /**
     * 
     * @param str cadena a evaluar
     * @return Classifier. el tipo de elemento evaluado -1 si es invalido; 
     */
    public int getClassify(String str) {
        if (com.validate(str)) {
            return Classifier.COMMENTARY;
        } else if (cont.validate(str)) {
            return Classifier.CONTENT;
        } else if (doc.validate(str)) {
            return Classifier.XML_DOCUMENT_INFO;
        } else if (tclose.validate(str)) {
            return Classifier.TAG_AUTOCLOSE;
        } else if (tend.validate(str)) {
            return Classifier.TAG_END;
        } else if (tinit.validate(str)) {
            return Classifier.TAG_INIT;
        } else if (dtdVal.validate(str)) {
            return Classifier.DTD_INFO;
        } else {
            return Classifier.INVALID;
        }
    }
}
