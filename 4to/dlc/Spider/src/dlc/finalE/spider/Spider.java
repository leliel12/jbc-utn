/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author juan
 */
public class Spider implements Runnable {

    private ConnectionHandler dBConnection;
    private HashSet<FileHandler> fileHandlers;
    private HashSet<File> notYetExploredPaths;
    private Indexer indexer;
    private long sleepTime;
    private ErrorHandler errorHandler;

    public Spider(final ConnectionHandler connection, final ErrorHandler errorHandler) {
        this.dBConnection = connection;
        this.fileHandlers = new HashSet<FileHandler>();
        this.notYetExploredPaths = new HashSet<File>();
        this.sleepTime = 10000;
        this.errorHandler = errorHandler;
        indexer = new Indexer(this.dBConnection);
    }

    @Override
    public void run() {
        do {
            try {
                if (!this.notYetExploredPaths.isEmpty()) {
                    File[] toIndexArray = getFilesToIndex();
                    this.notYetExploredPaths.clear();
                    this.indexer(toIndexArray);
                }
                this.wait(this.sleepTime);
            } catch (SpiderException ex) {
                errorHandler(ex);
            } catch (InterruptedException ex) {
                errorHandler(new SpiderException("InterruptedException", SpiderException.FATAL_ERROR));
            }
        } while (true);
    }

    public synchronized long getSleepTime() {
        return sleepTime;
    }

    public synchronized boolean setSleepTime(final long threadSleep) {
        if (threadSleep >= 0) {
            this.sleepTime = threadSleep;
        }
        return (threadSleep >= 0);
    }

    public synchronized boolean addPath2Explore(final File toExplore) {
        return this.notYetExploredPaths.add(toExplore);
    }

    public synchronized boolean addFileHandler(final FileHandler fh) {
        boolean added = false;
        boolean exist = false;
        File exampleFile = null;
        try {
            exampleFile = fh.getExampleFile();
            for (Iterator<FileHandler> it = fileHandlers.iterator(); it.hasNext() && !exist;) {
                FileHandler fileHandler = it.next();
                exist = fileHandler.isMyHandler(exampleFile);
            }
            if (!exist) {
                this.fileHandlers.add(fh);
                added = true;
            }
        } catch (SpiderException ex) {
            errorHandler(ex);
        }
        return added;
    }

    public synchronized boolean removeFileHandler(final FileHandler fh) {
        return this.fileHandlers.remove(fh);
    }

    private void errorHandler(SpiderException ex) {
        byte exType = ex.getExceptionType();
        switch (exType) {
            case SpiderException.WARNING:
                this.errorHandler.warning(ex);
                break;
            case SpiderException.ERROR:
                this.errorHandler.error(ex);
                break;
            case SpiderException.FATAL_ERROR:
                this.errorHandler.fatalError(ex);
                break;
        }
    }

    private synchronized File[] getFilesToIndex() {
        File[] toReturn = new File[0];
        for (Iterator<File> it = notYetExploredPaths.iterator(); it.hasNext();) {
            File toExplore = it.next();
            File[] toAppend = FileUtil.getFilesOf(toExplore, true);
            toReturn = FileUtil.appendToArray(toReturn, toAppend);
        }
        return toReturn;
    }

    private void indexer(final File[] toIndex) throws SpiderException {
        for (int i = 0; i < toIndex.length; i++) {
            File file = toIndex[i];
            boolean indexed = false;
            for (Iterator<FileHandler> it = fileHandlers.iterator(); it.hasNext() && !indexed;) {
                FileHandler handler = it.next();
                if (handler.isMyHandler(file)) {
                    indexer.addToDb(handler, file);
                    indexed = true;
                }//if
            }//filehandlers
            if (!indexed) {
                throw new SpiderException("Not FileHandler for: " + file.getAbsolutePath(), SpiderException.WARNING);
            }
        }//files
    }//indexer method
}