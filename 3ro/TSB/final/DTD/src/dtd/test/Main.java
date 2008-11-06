/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.test;

import dtd.DTDValidatorGenerator;
import dtd.exception.DTDException;
import dtd.validator.DTDValidator;
import dtd.validator.attlist.Variable;

/**
 *
 * @author juan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DTDSimulator dsim = new DTDSimulator();
        DTDValidatorGenerator gen = new DTDValidatorGenerator();
        XMLSimulator xsim=new XMLSimulator();
        DTDValidator val=null;
        String rule = null;
        XMLSimulator.XMLStructure xml=null;
        boolean OK = true;
        try {
            //intento validar sintacticamente el dtd
            while ((rule = dsim.getNext()) != null) {
                gen.tryToGenerate(rule, OK);
            }
            
            //extraigo el validador generado del dtd validado sintacticamente
            gen.commit();
            val=gen.getDTDValidator();
            
            //recorro los valores del xml
            while((xml = xsim.getNext()) != null){
                //saco todos los datos del simulador
                int type=xml.type;
                String name=xml.name;
                String read=xml.read;
                Variable[] vars=xml.vars; 
                
                //verifico que tipo de simulacion es
                switch(type){
                    case XMLSimulator.START_DOCUMENT:
                        val.validateStartDocument();
                        break;
                    case XMLSimulator.START_ELEMENT:
                        val.validateStartElement(name, read, vars);
                        break;
                    case XMLSimulator.END_ELEMENT:
                        val.validateEndElement(name, read);
                        break;
                    case XMLSimulator.INSERT_CONTENT:
                        val.validateInsertContent(read);
                        break;
                    case XMLSimulator.END_DOCUMENT:
                        val.validateEndDocument();
                        
                }//switch
            }
        } catch (DTDException ex) {
            ex.printStackTrace();
        }
    }
}
