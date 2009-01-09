/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.proto;

import dlc.finalE.spider.ConnectionHandler;
import dlc.finalE.spider.SpiderException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan
 */
public class Main {

    private static File testDb;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
//        try {
//            testDb = new File(System.getProperty("java.io.tmpdir") + File.separator + "testSpider.db4o");
//            print(testDb);
//            Connection.setDBFile(testDb);
//            Connection.getConnection();
//        } catch (SpiderException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
            SmallTxtHandler th = new SmallTxtHandler();
            File f = new File("/home/juan/peopssoft001.txt");
            print(th.isMyHandler(f));
            th.setFile(f);
            while(th.hasNextWord()){
                print(th.getNextWord());
            }
        } catch (SpiderException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void print(Object msg) {
        String str = msg.toString();
        System.out.println(str);
    }

    
}
