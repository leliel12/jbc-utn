/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bits.back;

import java.io.File;

/**
 *
 * @author juan
 */
public abstract class SufixGenerator {

    public static String getName(File f) {
        String ext = f.getName();
        ext = ext.substring(ext.length() - 4);
        if(ext.compareToIgnoreCase(".neg")==0){
            return quitarNeg(f);
        }
        Debug.out("Agrenado Extencion...");
        return (f.getName()+".neg");
    }

    private static String quitarNeg(File f) {
        String aux=f.getName();
        Debug.out("Quitando Extencion...");
        return aux.replaceAll(".neg", "");
    }
}
