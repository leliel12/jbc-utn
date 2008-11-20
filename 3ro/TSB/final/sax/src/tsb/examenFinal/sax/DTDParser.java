/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb.examenFinal.sax;

import java.io.FileNotFoundException;
import tsb.examenFinal.dtd.validator.DTDValidator;
import tsb.examenFinal.io.DTDReader;
import tsb.examenFinal.io.InvalidReadException;
import tsb.examenFinal.io.XMLReader;
import tsb.examenFinal.dtd.DTDException;
import tsb.examenFinal.dtd.DTDValidatorGenerator;

/**
 *
 * toma el control del lector de bajo nivel
 * lo encapsula en un DTDReader de bajo nive;
 * solicita las reglas internas y externas leidas
 * genera el validador.
 */
class DTDParser {

    private DTDReader dtdReader;
    private String[] externalRules;
    private String[] internalRules;
    private DTDValidator dtdValidator;

    public DTDParser(XMLReader reader) throws DTDException {
        try {
            this.dtdReader = new DTDReader(reader);
            this.externalRules = this.dtdReader.getExternalRules();
            this.internalRules = this.dtdReader.getInternalRules();
            this.tryToGenerate();
        } catch (InvalidReadException ex) {
            String msg = ex.getMessage();
            throw new DTDException(msg);
        } catch (FileNotFoundException ex) {
            String msg = ex.getMessage();
            throw new DTDException(msg);
        }
    }

    public DTDValidator getDtdValidator() {
        return dtdValidator;
    }

    private synchronized void tryToGenerate() throws DTDException {
        DTDValidatorGenerator dtdValidatorGenerator = new DTDValidatorGenerator();
        for (int i = 0; externalRules != null && i < externalRules.length; i++) {
            String rule = externalRules[i];
            dtdValidatorGenerator.tryToGenerate(rule, false);
        }
        for (int i = 0; internalRules != null && i < internalRules.length; i++) {
            String rule = internalRules[i];
            dtdValidatorGenerator.tryToGenerate(rule, true);
        }
        dtdValidatorGenerator.commit();
        this.dtdValidator = dtdValidatorGenerator.getDTDValidator();
    }
}
