package dtu.calculator;

import java.util.Scanner;

public class Fejlbesked {
	public String fejlbesked = "";

	public String faFejlbesked() {
		return fejlbesked;
	}

	public void satFejlbesked(String fejlbesked) {
		this.fejlbesked = fejlbesked;
	}

//s204501
	public int integer(Scanner console, int nedre, int øvre) {
		while (!console.hasNextInt()) {
			console.next();
			System.out.println("Ugyldigt valg - vælg en gyldig mulighed");
		}
		int input = console.nextInt();
		
		if (input < nedre || input > øvre) {
			System.out.println("Ugyldigt valg - vælg en gyldig mulighed");
			input = integer(console, nedre, øvre);
		}
		
		return input;
	}

}
