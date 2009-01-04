/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author juan
 */
public interface FileHandler extends Iterable {

    public void readFile(File f) throws FileNotFoundException, SpiderException;
    public boolean isMyHandler(File f) throws SpiderException;
    public void clearIteratorBuffer()throws SpiderException;
}
