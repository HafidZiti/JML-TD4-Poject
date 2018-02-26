package TD4;

import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestExplosivesFindBat {
	static int nb_inconclusive = 0;
	static int nb_fail = 0;

	Explosives e = new Explosives();

	public static void main(String args[]) {
		String testClass = "TestExplosivesFindBat";
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
	
	@Test
	public void TestFindBat1() {
		try{
			e=new Explosives();
			e.add_incomp("Prod_A","Prod_B");
			System.out.println("Batiment test 1 : " + e.FindBat("Prod_A"));	
		}   catch(JmlAssertionError e){
			handleJMLAssertionError(e);		
		} 
	}
	
	@Test
	public void testFindBat2() {
		try{
			e=new Explosives();
			e.add_incomp("Prod_A","Prod_B");
			e.add_assign("Bat_1","Prod_C");
			System.out.println("Batiment test 2 : " + e.FindBat("Prod_A"));	
		}   catch(JmlAssertionError e){
			handleJMLAssertionError(e);	
		} 
	}
	
	@Test
	public void testFindBat3() {
		try{
			e=new Explosives();
			e.add_incomp("Prod_A","Prod_B");
			e.add_incomp("Prod_C","Prod_B");
			e.add_incomp("Prod_A", "Prod_C");
			e.add_incomp("Prod_C", "Prod_D");
			e.add_assign("Bat_1","Prod_A");
			e.add_assign("Bat_2","Prod_B");
			System.out.println("Batiment pour Prod_D test 3 : " + e.FindBat("Prod_D"));	
			System.out.println("Batiment pour Prod_C test 3 : " + e.FindBat("Prod_C"));	
		}   catch(JmlAssertionError e){
			handleJMLAssertionError(e);	
		} 
	}
	

}
