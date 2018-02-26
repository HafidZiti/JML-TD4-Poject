package TD4;

import java.util.Arrays;

// Based on a B specification from Marie-Laure Potet.

public class Explosives {
	public int nb_inc = 0;
	public String[][] incomp = new String[50][2];
	public int nb_assign = 0;
	public String[][] assign = new String[30][2];

	/*
	 @ public invariant // Prop 1 
	 @ (0 <= nb_inc && nb_inc < 50); 
	 @*/

	/*
	 @ public invariant // Prop 2
	 @ (0 <= nb_assign && nb_assign < 30);
	 @*/

	/*
	 @ public invariant // Prop 3
	 @ (\forall int i; 0 <= i && i < nb_inc;
	 @ incomp[i][0].startsWith("Prod") && incomp[i][1].startsWith("Prod"));
	 @*/

	/*
	 @ public invariant // Prop 4
	 @ (\forall int i; 0 <= i && i < nb_assign;
	 @ assign[i][0].startsWith("Bat") && assign[i][1].startsWith("Prod"));
	 @*/

	/*
	 @ public invariant // Prop 5
	 @ (\forall int i; 0 <= i && i < nb_inc;!(incomp[i][0]).equals(incomp[i][1])); 
	 @
	 */

	 /*@ public invariant // Prop 6
    @ (\forall int i; 0 <= i && i < nb_inc; 
    @        (\exists int j; 0 <= j && j < nb_inc; 
    @           (incomp[i][0]).equals(incomp[j][1]) 
    @              && (incomp[j][0]).equals(incomp[i][1]))); 
    @*/
	
  /*@ public invariant // Prop 7
    @ (\forall int i; 0 <= i &&  i < nb_assign; 
    @     (\forall int j; 0 <= j && j < nb_assign; 
    @        (i != j && (assign[i][0]).equals(assign [j][0])) ==>
    @        (\forall int k; 0 <= k && k < nb_inc;
    @           (!(assign[i][1]).equals(incomp[k][0])) 
    @              || (!(assign[j][1]).equals(incomp[k][1])))));
    @*/

  /*@requires nb_inc+2 < 50;
	@requires !prod1.equals(prod2);
	@requires prod1.startsWith("Prod") && prod2.startsWith("Prod");
	@*/
	public void add_incomp(String prod1, String prod2) {
		incomp[nb_inc][0] = prod1;
		incomp[nb_inc][1] = prod2;
		incomp[nb_inc + 1][1] = prod1;
		incomp[nb_inc + 1][0] = prod2;
		nb_inc = nb_inc + 2;

	}

    /*@requires (nb_assign+1 < 30) ;
	@requires (bat.startsWith("Bat") && prod.startsWith("Prod"));
	@requires (\forall int i; 0 <= i && i < nb_assign;(\forall int j; 0 <= j && j < nb_inc;
	@ ((assign[i][0].equals(bat) && incomp[j][0].equals(assign[i][1]))
	@ ==> ( !prod.equals(incomp[j][1]))))) ;
	@*/
	public void add_assign(String bat, String prod) {
		assign[nb_assign][0] = bat;
		assign[nb_assign][1] = prod;
		nb_assign = nb_assign + 1;

	}

	/*
	@ requires (prod1.startsWith("Prod") && prod2.startsWith("Prod")) ;
	@ ensures (\result == true) ==>(\forall int i; 0 <= i && i < nb_inc;
	@ !(incomp[i][0].equals(prod1) && incomp[i][1].equals(prod2))) ;
	@ ensures (\result == false) ==>(\exists int i; 0 <= i && i < nb_inc; 
	@ incomp[i][0].equals(prod1) && incomp[i][1].equals(prod2)) ;
	@*/
	public /* @ pure @ */ boolean compatible(String prod1, String prod2) {
		for (int i = 0; i < nb_inc; i++) {
			if (incomp[i][0].equals(prod1) && incomp[i][1].equals(prod2))
				return false;
		}

		return true;
	}


  /*@ requires (prod.startsWith("Prod")) ;
	@ ensures (\result.startsWith("Bat")) ;
    @ ensures (\forall int i; 0 <= i &&  i < nb_assign;(assign[i][0].equals(\result)) ==> (compatible(assign[i][1],prod)));
    @*/
	public /* @ pure @ */ String FindBat(String prod) {

		String[] tous_les_Batiments = new String[30];

		String[] tous_les_Batiments_incompa = new String[30];

		int all_bats = 0;
		int all_bats_incompa = 0;

		for (int i = 0; i < nb_assign; i++) {
			for (int j = 0; j < nb_assign; j++) {
				if (!(assign[i][0].equals(assign[j][0]))) {
					if (!Arrays.asList(tous_les_Batiments).contains(assign[i][0])) {
						tous_les_Batiments[all_bats] = assign[i][0];
						all_bats++;
					}
				}
			}

				if (!compatible(prod, assign[i][1])) {
					if (!Arrays.asList(tous_les_Batiments_incompa).contains(assign[i][0])) {
						tous_les_Batiments_incompa[all_bats_incompa] = assign[i][0];
						all_bats_incompa++;
					}
				}
		}

		// voir ce qu'il a donné 
		for (int j2 = 0; j2 < all_bats; j2++) {
			System.out.println("All Bat " + tous_les_Batiments[j2]);
		}
		for (int j2 = 0; j2 < all_bats_incompa; j2++) {
			System.out.println("Bat incompatible " + tous_les_Batiments_incompa[j2]);
		}
		
		for (int i=0; i < all_bats; i++){
			boolean compatibilite = true;
			for (int j=0; j < all_bats_incompa; j++)
				if (tous_les_Batiments_incompa[j].equals(tous_les_Batiments[i]))
					compatibilite = false;
			if (compatibilite)
				return tous_les_Batiments[i];
		}
		return "Bat_" + ++all_bats;
	}

	public void skip() {
	}
}
