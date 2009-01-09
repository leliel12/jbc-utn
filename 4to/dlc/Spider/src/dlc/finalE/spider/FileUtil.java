/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import java.io.File;
import java.util.Vector;

/**
 *
 * @author juan
 */
abstract class FileUtil {

    synchronized static File[] getFilesOf(final File path, final boolean recursive) {
        Vector<File> toReturn = new Vector<File>();
        if (path.isDirectory()) {
            File[] content = path.listFiles();
            for (int i = 0; i < content.length; i++) {
                File file = content[i];
                if (file.isDirectory() && recursive) {
                    File[] subContent = getFilesOf(file, recursive);
                    toReturn = FileUtil.appendToCollection(toReturn, subContent);
                } else {
                    toReturn.add(file);
                }
            }
        } else {
            toReturn.add(path);
        }
        return toReturn.toArray(new File[toReturn.size()]);
    }

    synchronized static File[] appendToArray(final File[] original, final File[] toAppend) {
        Vector<File> toReturn = new Vector<File>();
        for (int i = 0; i < original.length; i++) {
            File file = original[i];
            toReturn.add(file);
        }
        for (int i = 0; i < toAppend.length; i++) {
            File file = toAppend[i];
            toReturn.add(file);
        }
        return toReturn.toArray(new File[toReturn.size()]);
    }

    synchronized static Vector<File> appendToCollection(final Vector<File> original, final File[] toAppend) {
        Vector<File> toReturn = new Vector(original);
        for (int i = 0; i < toAppend.length; i++) {
            File file = toAppend[i];
            toReturn.add(file);
        }
        return toReturn;
    }

    synchronized static boolean isMyHandler(final FileHandler handler, final File file) {
        String ext = handler.getFileHandleExtension();
        return file.getName().endsWith(ext);
    }
}
