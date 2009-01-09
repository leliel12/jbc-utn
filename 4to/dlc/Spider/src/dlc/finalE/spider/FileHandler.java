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

    public void setFile(File f) throws SpiderException;

    public String getNextWord() throws SpiderException;

    public boolean hasNextWord() throws SpiderException;

    public String getFileHandleExtension();

}
