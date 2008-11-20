package tsb.examenFinal.sax.xmlSintaxValidator;



/**
 *
 * verifica que el tag xml leido corresponde a un inicio de dtd
 */
class ValidatorDTD implements ValidatorModel {

    public boolean validate(String str) {
        boolean ok = true;
        try {
            ok = str.equals("<!DOCTYPE");
        } catch (Exception e) {
            ok = false;
        } finally {
            return ok;
        }
    }
}
