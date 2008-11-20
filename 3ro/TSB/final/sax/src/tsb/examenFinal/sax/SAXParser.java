package tsb.examenFinal.sax;

import tsb.examenFinal.dtd.validator.DTDValidator;
import tsb.examenFinal.util.Attribute;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;
import tsb.examenFinal.dtd.DTDException;
import tsb.examenFinal.sax.xmlSintaxValidator.Classifier;
import tsb.examenFinal.io.XMLReader;
import tsb.examenFinal.sax.xmlSintaxValidator.Equilibrium;
import tsb.examenFinal.util.StrOp;

/**
 * El parser sax propiamente dicho
 * controla el flujo de datos desde los caracteres hacia los handlers y ls validaciones dtd
 */
public class SAXParser {
    //handlers

    private ContentHandlerModel contentH;
    private ErrorHandlerModel errorH;
    private File xmlFile;
    private Classifier classifier;
    private XMLReader reader;
    private Equilibrium equilibrium;
    private DTDValidator dtdValidator;

    public SAXParser() {
        this.classifier = new Classifier();
        this.equilibrium = new Equilibrium();
    }

    public ContentHandlerModel getContentHandler() {
        return contentH;
    }

    public void setContentHandler(ContentHandlerModel contentH) {
        this.contentH = contentH;
    }

    public ErrorHandlerModel getErrorHandler() {
        return errorH;
    }

    public void setErrorHandler(ErrorHandlerModel errorH) {
        this.errorH = errorH;
    }

    public File getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(File xmlFile) {
        this.xmlFile = xmlFile;
    }

    public void doParse() throws FileNotFoundException {
        this.equilibrium.clear();
        try {
            String read;
            this.reader = new XMLReader(xmlFile);
            this.startDocument();
            do {
                read = reader.getNext();
                if (read != null) {
                    classifyRead(read);
                }
            } while (read != null);
            if (equilibrium.isValid()) {
                this.endDocument();
            } else {
                throw new SAXException(SAXException.FATAL_ERROR, equilibrium.getCause());
            }
        } catch (SAXException ex) {
            classifierException(ex);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /* ****************************************
     * 
     * METODOS PRIVADOS
     * 
     * ****************************************/
    /*
    metodos para el content handler
     */
    private void classifyRead(String read) throws SAXException {
        int type = this.classifier.getClassify(read);
        if (contentH != null) {
            if (type == Classifier.INVALID) {
                throw new SAXException(SAXException.FATAL_ERROR, "Invalid Syntax: " + read);
            } else if (type == Classifier.TAG_INIT) {
                this.startElement(read);
            } else if (type == Classifier.TAG_END) {
                this.endElement(read);
            } else if (type == Classifier.TAG_AUTOCLOSE) {
                this.autoCloseElement(read);
            } else if (type == Classifier.CONTENT) {
                this.addContent(read);
            } else if (type == Classifier.XML_DOCUMENT_INFO) {
                this.setDocumentInfo(read);
            } else if (type == Classifier.DTD_INFO) {
                try {
                    //le paso el lector al dtd
                    DTDParser dtdParser = new DTDParser(this.reader);
                    //le pido el validador
                    this.dtdValidator = dtdParser.getDtdValidator();
                } catch (DTDException ex) {
                    this.classifierException(new SAXException(SAXException.ERROR, "DTD ERROR: "+ex.getMessage()));
                }//try-cath
            }//else if
        }
    }

    private void endDocument() {
        try {
            this.contentH.endDocument();
            if (this.dtdValidator != null) {
                this.dtdValidator.validateEndDocument();
            }
        } catch (DTDException ex) {
            this.classifierException(new SAXException(SAXException.ERROR, ex.getMessage()));
        } catch (SAXException ex) {
            this.classifierException(ex);
        }
    }

    private void startDocument() {
        try {
            this.contentH.startDocument();
            if (this.dtdValidator != null) {
                this.dtdValidator.validateStartDocument();
            }
        } catch (DTDException ex) {
            this.classifierException(new SAXException(SAXException.ERROR, ex.getMessage()));
        } catch (SAXException ex) {
            this.classifierException(ex);
        }
    }

    private void addContent(String read) {
        try {
            this.contentH.insertContent(read);
            if (this.dtdValidator != null) {
                this.dtdValidator.validateInsertContent(read);
            }
        } catch (DTDException ex) {
            this.classifierException(new SAXException(SAXException.ERROR, ex.getMessage()));
        } catch (SAXException ex) {
            this.classifierException(ex);
        }
    }

    private void autoCloseElement(String read) {
        String name = StrOp.getNameOfTag(read, "<", "/>");
        Attribute[] vars = this.getVariables(read, "<", "/>");
        this.equilibrium.add(read, Classifier.TAG_AUTOCLOSE);
        try {
            this.contentH.startElement(name, read, vars);
            this.contentH.endElement(name, read);
            if (this.dtdValidator != null) {
                this.dtdValidator.validateStartElement(name, read, vars);
                this.dtdValidator.validateEndElement(name, read);
            }
        } catch (DTDException ex) {
            this.classifierException(new SAXException(SAXException.ERROR, ex.getMessage()));
        } catch (SAXException ex) {
            this.classifierException(ex);
        }
    }

// no tiene DTD
    private void setDocumentInfo(String read) {
        Attribute[] vars = this.getVariables(read, "<?xml", "?>");
        try {
            this.contentH.documentInfo(vars);
        } catch (SAXException ex) {
            this.classifierException(ex);
        }
    }

    private void startElement(String read) {
        String name = StrOp.getNameOfTag(read, "<", ">");
        Attribute[] vars = this.getVariables(read, "<", ">");
        this.equilibrium.add(read, Classifier.TAG_INIT);
        try {
            this.contentH.startElement(name, read, vars);
            if (this.dtdValidator != null) {
                this.dtdValidator.validateStartElement(name, read, vars);
            }
        } catch (DTDException ex) {
            this.classifierException(new SAXException(SAXException.ERROR, ex.getMessage()));
        } catch (SAXException ex) {
            this.classifierException(ex);
        }
    }

    private void endElement(String read) {
        String name = StrOp.getNameOfTag(read, "</", ">");
        this.equilibrium.add(read, Classifier.TAG_END);
        try {
            this.contentH.endElement(name, read);
            if (this.dtdValidator != null) {
                this.dtdValidator.validateEndElement(name, read);
            }
        } catch (DTDException ex) {
            this.classifierException(new SAXException(SAXException.ERROR, ex.getMessage()));
        } catch (SAXException ex) {
            this.classifierException(ex);
        }
    }

    /*
     * metodos para el Error handler
     */
    private void classifierException(SAXException ex) {
        int type = ex.getType();
        if (errorH != null) {
            if (type == SAXException.ERROR) {
                this.error(ex);
            } else if (type == SAXException.FATAL_ERROR) {
                this.fatalError(ex);
            } else if (type == SAXException.WARNING) {
                this.warning(ex);
            }
        }
    }

    private void fatalError(SAXException ex) {
        try {
            errorH.fatalError(ex);
        } catch (SAXException ex1) {
            this.classifierException(ex1);
        }
    }

    private void error(SAXException ex) {
        try {
            errorH.error(ex);
        } catch (SAXException ex1) {
            this.classifierException(ex1);
        }
    }

    private void warning(SAXException ex) {
        try {
            errorH.warning(ex);
        } catch (SAXException ex1) {
            this.classifierException(ex1);
        }
    }

    private Attribute[] getVariables(String read, String init, String end) {
        String[] array = StrOp.split(read, " ", init, end, '"', '"');
        Vector<Attribute> variables = new Vector<Attribute>();
        for (int i = 0; i < array.length; i++) {
            String string = array[i];
            if (string.indexOf('=') != -1) {
                int idxEquals = string.indexOf('=');
                String name = string.substring(0, idxEquals);
                String value = string.substring(idxEquals + 1).replaceAll("\"", "");
                variables.add(new Attribute(name, value));
            }
        }
        return variables.toArray(new Attribute[variables.size()]);
    }
}
