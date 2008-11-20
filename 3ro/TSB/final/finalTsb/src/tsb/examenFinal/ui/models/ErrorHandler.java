package tsb.examenFinal.ui.models;

import tsb.examenFinal.ui.ErrorJDialog;
import tsb.examenFinal.sax.SAXException;
import tsb.examenFinal.sax.ErrorHandlerModel;

/**
 *
 * Implentacion del error
 */
public class ErrorHandler implements ErrorHandlerModel {

    public void fatalError(SAXException ex) throws SAXException {
        String title = "FATAL ERROR";
        String msg = ex.getMessage();
        String stk = this.convertStakTraceString(ex);
        this.printConsole(title + ": ", msg, stk);
        new ErrorJDialog(stk, title, msg);
    }

    public void error(SAXException ex) throws SAXException {
        String title = "ERROR";
        String msg = ex.getMessage();
        String stk = this.convertStakTraceString(ex);
        this.printConsole(title + ": ", msg, stk);
        new ErrorJDialog(stk, title, msg);
    }

    public void warning(SAXException ex) throws SAXException {
        String title = "WARNING";
        String msg = ex.getMessage();
        String stk = this.convertStakTraceString(ex);
        this.printConsole(title + ": ", msg, stk);
        new ErrorJDialog(stk, title, msg);
    }

    private String convertStakTraceString(SAXException ex) {
        StackTraceElement[] stk = ex.getStackTrace();
        String toReturn = "";
        for (int i = 0; i < stk.length; i++) {
            StackTraceElement stackTraceElement = stk[i];
            toReturn += "\n" + stackTraceElement.toString();
        }
        return toReturn;
    }

    private void printConsole(String type, String msg, String stkTrace) {
        String toPrint = type + "\n\t";
        toPrint += msg;
        System.err.println(toPrint + "\n" + stkTrace);
    }
}
