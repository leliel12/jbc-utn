package utn.frc.dlc.barg.test;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import utn.frc.dlc.barg.db.DBPersonaTest;
import utn.frc.dlc.javalib.base.BaseClassTest;
import utn.frc.dlc.javalib.base.FechaTest;
import utn.frc.dlc.javalib.base.PersonaTest;
import utn.frc.dlc.javalib.base.TelefonoTest;
import utn.frc.dlc.javalib.db.DBKeyMapTest;
import utn.frc.dlc.javalib.db.SqlManagerTest;
import utn.frc.dlc.javalib.test.DLCJavaLibTest;

public class BArgTest extends TestCase {
	
	public static TestSuite suite() {
		TestSuite barg = new TestSuite("BArgTest");
		
		TestSuite javalib = new TestSuite("DLCJavaLibTest");

		TestSuite jlbase = new TestSuite("utn.frc.dlc.javalib.base");
		jlbase.addTestSuite(BaseClassTest.class);
		jlbase.addTest(BaseClassTest.suite());
		jlbase.addTest(FechaTest.suite());
		jlbase.addTest(PersonaTest.suite());
		jlbase.addTest(TelefonoTest.suite());
		
		TestSuite jldb = new TestSuite("utn.frc.dlc.javalib.db");
		jldb.addTest(SqlManagerTest.suite());
		jldb.addTest(DBKeyMapTest.suite());
		
		javalib.addTest(jlbase);
		javalib.addTest(jldb);
		
		TestSuite bargdb = new TestSuite("utn.frc.dlc.barg.db");
		bargdb.addTest(DBPersonaTest.suite());
		
		barg.addTest(javalib);
		barg.addTest(bargdb);
		
		return barg;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		junit.swingui.TestRunner.run(BArgTest.class);
	}
}
