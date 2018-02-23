package TD4;

import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestExplosivesJUnit4 {

	static int nb_inconclusive = 0;
	static int nb_fail = 0;

	Explosives e = new Explosives();

	public static void main(String args[]) {
		String testClass = "TestExplosivesJUnit4";
		org.junit.runner.JUnitCore.main(testClass);
	}

	private void handleJMLAssertionError(JmlAssertionError e) {
		if (e.getClass().equals(JmlAssertionError.PreconditionEntry.class)) {
			System.out.println("\n INCONCLUSIVE " + (new Exception().getStackTrace()[1].getMethodName()) + "\n\t "
					+ e.getMessage());
			nb_inconclusive++;
		} else {
			// test failure
			nb_fail++;
			fail("\n\t" + e.getMessage());
		}
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nb_inconclusive = 0;
		nb_fail = 0;
		org.jmlspecs.utils.Utils.useExceptions = true;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("\n inconclusive tests: " + nb_inconclusive + " -- failures : " + nb_fail);
	}

	@Ignore
	public void testSequence_0() {
		try {
			e = new Explosives();
			e.add_incomp("Prod_Nitro", "Prod_Glycerine");
			e.add_incomp("Prod_Dyna", "Prod_Mite");
			e.add_assign("Bat_1", "Prod_Dyna");
			e.add_assign("Bat_1", "Prod_Nitro");
			e.add_assign("Bat_2", "Prod_Mite");
			e.add_assign("Bat_1", "Prod_Glycerine");
		} catch (JmlAssertionError e) {
			handleJMLAssertionError(e);
		}
	}

	@Test
	public void testProp_1() {
		try {
			e = new Explosives();
			for (int i = 0; i < 25; i++) {
				e.add_incomp("Prod" + i, "Prod" + i + "'");
			}
		} catch (JmlAssertionError e) {
			handleJMLAssertionError(e);
		}
	}

	@Test
	public void testProp_2() {
		try {
			e = new Explosives();
			for (int i = 0; i < 30; i++) {
				e.add_assign("Bat", "Prod" + i);
			}
		} catch (JmlAssertionError e) {
			handleJMLAssertionError(e);
		}
	}

	@Test
	public void testProp_3() {
		try {
			e = new Explosives();
			for (int i = 0; i < 24; i++) {
				e.add_incomp("P" + i, "P" + i);

			}
		} catch (JmlAssertionError e) {
			handleJMLAssertionError(e);
		}
	}

	@Test
	public void testProp_4() {
		try {

			e = new Explosives();
			for (int i = 0; i < 30; i++) {
				e.add_assign("B" + i, "P" + i);
			}
		} catch (JmlAssertionError e) {
			handleJMLAssertionError(e);
		}
	}
	
	@Test
	public void testProp_5() {
		try {
			e = new Explosives();
			e.add_incomp("Prod" , "Prod" );
		} catch (JmlAssertionError e) {
			handleJMLAssertionError(e);
		}
	}	
	
	@Test
	public void testProp_7() {
		try {
			e = new Explosives();
			e.add_incomp("ProdA" , "ProdB" );
			e.add_assign("Bat1", "ProdA");
			e.add_assign("Bat1", "ProdB");
		} catch (JmlAssertionError e) {
			handleJMLAssertionError(e);
		}
	}
	
	

}
