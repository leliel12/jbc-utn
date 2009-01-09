/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dlc.finalE.proto;

import dlc.finalE.spider.ErrorHandler;
import dlc.finalE.spider.SpiderException;

/**
 *
 * @author juan
 */
public class SmallErrorHandler implements ErrorHandler{

    public void warning(SpiderException ex) {
        print(ex.getMessage());
    }

    public void error(SpiderException ex) {
        print(ex.getMessage());
    }

    public void fatalError(SpiderException ex) {
        print(ex.getMessage());
        System.exit(666);
    }

    private void print(String exMsg){
        System.err.println(exMsg);
    }

}
