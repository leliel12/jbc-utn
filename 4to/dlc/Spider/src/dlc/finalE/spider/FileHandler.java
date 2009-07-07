/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import java.io.File;

/**
 *
 * @author juan
 */
public interface FileHandler {

    public String[] getWords(File f) throws SpiderException;

    public boolean isMyHandler(File f)throws SpiderException;

}
