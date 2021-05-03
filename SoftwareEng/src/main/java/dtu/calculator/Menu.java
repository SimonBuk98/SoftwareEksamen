package dtu.calculator;

import java.util.Scanner;

public class Menu {

	Scanner scanner = new Scanner(System.in);

	public void startMenu(Bruger id){

		System.out.println("Vælg en mulighed:");
		System.out.println("1 for at oprette en bruger");
		System.out.println("2 for at oprette et projekt");
		System.out.println("3 for at registrere tid");
		System.out.println("4 for at tjekke registreret tid");
		System.out.println("5 for at opdatere din status");
		System.out.println("6 for at anmode om hjælp");

		if (id.projektleder) {
			System.out.println("Projektleder-menu:");
			System.out.println("7 for at oprette en aktivitet");
			System.out.println("8 for at slette en aktivitet");
			System.out.println("9 for at slette et projekt");
			System.out.println("10 for at tilføje medarbejder til projekt");
			System.out.println("11 for at tilføje medarbejder til aktivitet");
			System.out.println("12 for at indhente opfølgning");
			System.out.println("13 for at sætte estimeret tid for projekt");
		
		


	}
}
	public void status(){
		System.out.println("1 - aktiv");
		System.out.println("2 - optaget");
		System.out.println("3 - ferie");
		System.out.println("4 - sygdom");
		System.out.println("5 - orlov");

	}

}
