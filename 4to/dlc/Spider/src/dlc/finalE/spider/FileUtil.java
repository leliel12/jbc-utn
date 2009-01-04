/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author juan
 */
public abstract class FileUtil {

    public synchronized static File[] getContent(File path, boolean recursive) {
        ArrayList<File> toReturn = new ArrayList<File>();
        File[] listOfFiles = path.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isDirectory() && recursive) {
                getContent(file, recursive);
            }
            toReturn.add(file);
        }
        return toReturn.toArray(new File[toReturn.size()]);
    }
}
