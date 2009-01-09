/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author juan
 */
public class Spider implements Runnable{

    private ConnectionHandler dBConnection;
    private HashSet<FileHandler> fileHandlers;
    private HashSet<String> notYetExploredPaths;

    public Spider(ConnectionHandler conection) {
        this.dBConnection = conection;
        this.fileHandlers = new HashSet<FileHandler>();
        this.notYetExploredPaths = new HashSet<String>();

    }

    @Override
    public void run(){

    }

    public synchronized boolean addPath2Explore(String path) {
        return this.notYetExploredPaths.add(path);
    }

    public synchronized boolean addFileHandler(FileHandler fh) {
        boolean added = false;
        boolean exist = false;
        String ext2add = fh.getFileHandleExtension().toLowerCase();
        for (Iterator<FileHandler> it = fileHandlers.iterator(); it.hasNext() && !exist;) {
            String ext = it.next().getFileHandleExtension().toLowerCase();
            exist = exist || (ext2add.equals(ext));
        }
        if (!exist) {
            added = this.fileHandlers.add(fh);
        }
        return added;
    }

    public synchronized boolean removeFileHandler(FileHandler fh) {
        return this.fileHandlers.remove(fh);
    }
}