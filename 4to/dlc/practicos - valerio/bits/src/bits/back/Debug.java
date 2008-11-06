/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bits.back;

import javax.swing.JTextArea;

/**
 *
 * @author juan
 */
public abstract class Debug {
    
    private final static String ERR="ERR> ";
    private final static String OUT="OUT> ";
    private static JTextArea ta=null;
    
    public static void setOut(JTextArea textArea){
        ta=textArea;
    }
    
    public static void out(String msg){
        System.out.println(msg);
        printInTextArea(msg, OUT);
    }
    
    public static void err(String msg){
        System.err.println(msg);
        printInTextArea(msg, ERR);
    }
    
    private static void printInTextArea(String msg, String prefix){
        if(ta!=null){
            String ante=ta.getText();
            if(ante.compareTo("")!=0) ante+="\n";
            String nuevo=ante + prefix +  msg;
            ta.setText(nuevo);
        }
    }

}
