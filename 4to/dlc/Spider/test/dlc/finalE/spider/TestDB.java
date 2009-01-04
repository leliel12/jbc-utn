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
public abstract class TestDB {

    private static File testDb;

    public static File getTestDb() {
        if (testDb == null) {
            initializeTestDb();
        }
        return testDb;
    }

    private static void initializeTestDb() {
        testDb = new File(System.getProperty("java.io.tmpdir") + File.separator + "testSpider.db4o");
    }
}
