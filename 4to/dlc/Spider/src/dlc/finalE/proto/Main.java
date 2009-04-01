/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.proto;

import dlc.finalE.spider.ConnectionHandler;
import dlc.finalE.spider.SpiderException;
import java.io.File;
import java.util.ArrayList;
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
        String path = System.getProperty("user.home") + File.separator + "testSpider.db4o";
        
    }

    private static void print(Object msg) {
        String str = msg.toString();
        System.out.println(str);
    }
}
