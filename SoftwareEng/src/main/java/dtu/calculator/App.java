package dtu.calculator;

import java.util.Calendar;
import java.util.Scanner;


public class App {

	static Scanner scanner = new Scanner(System.in);

	public static boolean loggedind;
	public static boolean tændt = true;
	public static Bruger bruger;
	public static Oversigt oversigt = new Oversigt();
	public static Menu menu;
	public static Fejlbesked fejlbesked = new Fejlbesked();
	

	public static void main(String[] args) {
	
		
		while (tændt) {
			while (!loggedind) {
				login();
			}
			while (loggedind) {
				menu.startMenu(bruger);
				options();
			}
		}
	}

	public static void login() {

		System.out.println("Login med dine initialer:");

		String login = scanner.nextLine();

		if (oversigt.tjekMedarbejder(login)) {
			loggedind = true;
			bruger = oversigt.fåMedarbejder(login);
		} else {
			System.out.println("Login fejlede, prøv igen");
		}
	}

	public static void opretBruger() {
		System.out.println("Indtast intialer for brugeren:");
		String initialer = scanner.nextLine();

		if (!oversigt.tjekMedarbejder(initialer)){
			oversigt.brugere.add(new Bruger(initialer));
		}
		else {
			fejlbesked.satFejlbesked("Disse intialer er allerede taget");
			fejlbesked.faFejlbesked();
		}
	}

	public static void opretProjekt(){
			System.out.println("Indtast nummer for projekt:");
			int nummer = scanner.nextInt();
			System.out.println("Indtast navn for projekt:");
			String navn = scanner.nextLine();
			oversigt.projekter.add(new Projekt(navn, nummer));
	}
	
	public void opretAktivitet(){
		System.out.println("Indtast nummer på aktivitetens projekt:");
		int nummer = scanner.nextInt();
		String  navn = scanner.next();
		if (oversigt.tjekProjekt(navn,nummer)) {

			if (bruger == oversigt.faProjekt(nummer).projektleder){
			System.out.println("Indtast navn på aktivitet:");
			String navn1 = scanner.nextLine();
			if (!oversigt.faProjekt(nummer).tjekAktivitet(navn1)) {
				oversigt.faProjekt(nummer).tilfojAktivitet(new Aktivitet(navn1));
			}

			else {
				fejlbesked.satFejlbesked("Der eksisterer allerede en aktivitet med navnet " + "\"" + navn + "\"");
				fejlbesked.faFejlbesked();
			}
			}

 			else {
				fejlbesked.satFejlbesked("Du er ikke projektleder på projekt " + nummer);
				fejlbesked.faFejlbesked();
			} 
			
		}
		else {
			fejlbesked.satFejlbesked("Projekt med nummer " + nummer + " eksisterer ikke");
			fejlbesked.faFejlbesked();
		}
	}

	public static void registrerTid(){
		System.out.println("Vælg en aktivitet at registrere tid på:");
		bruger.printAktiviteter();
		int valg = scanner.nextInt();
		System.out.println("Indtast hvor meget tid der skal registreres:");
		double tid = scanner.nextDouble();
		bruger.aktiviteter.get(valg).satTid(bruger, tid);
		
	}

	public static void satStatus(){
		System.out.println("Indtast ny status:");
		menu.status();
		bruger.status=scanner.nextInt();
	}

//	public static void anmodHjalp(){
//		System.out.println("Indtast initialer for brugeren der anmodes");
//		String hjalp = scanner.nextLine();
//		System.out.println("Indtast projekt");
//		int projekt = scanner.nextInt();
//		
//		if (Projekt.tjekForMedarbejder(hjalp)){
//			System.out.println("Indtast aktivitet");
//			String aktivitet = scanner.nextLine();
//			if (Projekt.tjekAktivitet(aktivitet)){
//				Aktivitet.tilfojMedarbejder(hjalp);
//			}
//		}
//		}
//		
//	}
//	

private static void sletAktivitet(){
	System.out.println("Hvilket af følgende projekter vil du slette en aktivitet fra?");
	bruger.printProjekter(bruger);
	int projekt = scanner.nextInt();
	System.out.println("Hvilken af følgende aktiviteter vil du slette?");
	bruger.projekter.get(projekt).printAktiviteter();
	
	if (!bruger.projekter.get(projekt).aktivitetsliste.isEmpty()) {
		int aktivitet = scanner.nextInt();
		bruger.projekter.get(projekt).fjernAktivitet(bruger.aktiviteter.get(aktivitet));
	}
}

private static void startSlutAktivitet(){
	System.out.println("Hvilket projekt hører aktiviteten under?");
	bruger.printProjekter(bruger);
	int projekt = scanner.nextInt();
	
	System.out.println("Hvilken aktivitet?");
	bruger.projekter.get(projekt).printAktiviteter();
	int aktivitet = scanner.nextInt();

	
	System.out.println("Angiv startdato DDMMYYYY");
	int start = scanner.nextInt();

	bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).start.set(Calendar.DAY_OF_MONTH, start/1000000);
	bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).start.set(Calendar.MONTH, (start/10000)%100);
	bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).start.set(Calendar.YEAR, start%10000);

	System.out.println("Angiv slutdato DDMMYYYY");
	int slut = scanner.nextInt();

	bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).slut.set(Calendar.DAY_OF_MONTH, slut/1000000);
	bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).slut.set(Calendar.MONTH, (slut/10000)%100);
	bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).slut.set(Calendar.YEAR, slut%10000);
	
	if (bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).slut.before(bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).start)) {
		
		bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).start = null;
		bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).slut = null;
		
		fejlbesked.satFejlbesked("Ugyldige datoer");
		System.out.println(fejlbesked.faFejlbesked());
	}

}

private static void startSlutProjekt(){
	System.out.println("Hvilket projekt?");
	bruger.printProjekter(bruger);
	int valg = scanner.nextInt();
	System.out.println("");
	
}

	private static void tilfojmedarbejder(String hjalp) {
		// TODO Auto-generated method stub
		
	}

	public static void options(){
		int valg = scanner.nextInt();

		if (valg == 1){
			opretBruger();
		}

		else if (valg == 2){
			opretProjekt();			
		}
		else if (valg == 3) {
			registrerTid();
		}
		else if (valg == 4) {
			bruger.printTid(bruger);
		}
		else if (valg == 5) {
			satStatus();
		}

		else if (valg == 7){
//			opretProjektAktivitet();
		}

		else if (valg == 9){
			sletAktivitet();
		}

		else if (valg == 13){
		System.out.println("Vil du angive budgetteret tid eller start/slut-dato?");
		System.out.println("1: budgetteret tid");
		System.out.println("2: start/slut-dato");
		int valg2 = scanner.nextInt();

		if (valg2 == 1) {

		}
		else if (valg2 == 2) {
			System.out.println("Vil du angive tid for et projekt eller en aktivitet?");
			int valg3 = scanner.nextInt();

			if (valg3 == 1) {
				startSlutAktivitet();
			}
			if (valg3 == 2) {
				startSlutProjekt();
			}
		}
		

		}
		

	}

}
