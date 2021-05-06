package dtu.calculator;

import java.util.Calendar;
import java.util.Scanner;

public class App {

	static Scanner scanner = new Scanner(System.in);

	public static boolean loggedind;
	public static boolean tændt = true;
	public static Bruger bruger;
	public static Oversigt oversigt = new Oversigt();
	public static Menu menu = new Menu();
	public static Fejlbesked fejlbesked = new Fejlbesked();

	public static void main(String[] args) {

		opretBruger();
		opretProjekt();
		opretAktivitet();

		while (tændt) {
			while (!loggedind) {
				login();
			}
			while (loggedind) {
				System.out.println("");
				menu.startMenu(bruger);
				System.out.println("");
				options();
			}
		}
	}

	public static void login() {

		System.out.println("Login med dine initialer:");

		String login = scanner.next();

		if (oversigt.tjekMedarbejder(login)) {
			loggedind = true;
			bruger = oversigt.fåMedarbejder(login);
		} else {
			System.out.println("Login fejlede, prøv igen");
		}
	}

	public static void opretBruger() {
		System.out.println("Indtast intialer for brugeren:");
		String initialer = scanner.next();
		

		if (!oversigt.tjekMedarbejder(initialer)) {
			oversigt.brugere.add(new Bruger(initialer));
		} else {
			fejlbesked.satFejlbesked("Disse intialer er allerede taget");
			System.out.println(fejlbesked.faFejlbesked());
		}
	}
	
	private static void projektleder() {
		System.out.println("Hvem vil du udnævne til projektleder?");
		String initialer = scanner.next();
		if (oversigt.tjekMedarbejder(initialer)) {
			System.out.println("Vælg et projekt:");
			oversigt.printProjekter();
			int projektnummer = scanner.nextInt();
			oversigt.faProjekt(projektnummer).projektleder = oversigt.fåMedarbejder(initialer);
			oversigt.fåMedarbejder(initialer).projektleder = true;
			
			oversigt.faProjekt(projektnummer).tilfojmedarbejder(bruger);
			bruger.tilfojProjekt(oversigt.faProjekt(projektnummer));
		}
		else {
			fejlbesked.satFejlbesked("Brugeren " + initialer + " eksisterer ikke");
			System.out.println(fejlbesked.faFejlbesked());
		}
	}

	public static void opretProjekt() {
		System.out.println("Indtast nummer for projekt:");
		int nummer = scanner.nextInt();
		System.out.println("Indtast navn for projekt:");
		String navn = scanner.next();
		oversigt.projekter.add(new Projekt(navn, nummer));
	}

	public static void opretAktivitet() {
		System.out.println("Projekter:");
		oversigt.printProjekter();
		System.out.println("Vælg et projekt til aktiviteten");
		int projekt = scanner.nextInt();
		if (oversigt.tjekProjekt(oversigt.projekter.get(projekt).navn, oversigt.projekter.get(projekt).projektnummer)) {

			if (bruger == oversigt.projekter.get(projekt).projektleder) {
				System.out.println("Indtast navn på aktivitet:");
				String navn1 = scanner.next();
				if (!oversigt.projekter.get(projekt).tjekAktivitet(navn1)) {
					oversigt.projekter.get(projekt).tilfojAktivitet(new Aktivitet(navn1));
				}

				else {
					fejlbesked.satFejlbesked("Der eksisterer allerede en aktivitet med navnet " + "\"" + oversigt.projekter.get(projekt).navn + "\"");
					System.out.println(fejlbesked.faFejlbesked());
				}
			}

			else {
				fejlbesked.satFejlbesked("Du er ikke projektleder på projekt " + oversigt.projekter.get(projekt).projektnummer);
				System.out.println(fejlbesked.faFejlbesked());
			}

		} else {
			fejlbesked.satFejlbesked("Der eksisterer ikke et projekt med nummeret " + oversigt.projekter.get(projekt).projektnummer + " og navnet " + oversigt.projekter.get(projekt).navn);
			System.out.println(fejlbesked.faFejlbesked());
		}
	}

	public static void registrerTid() {
		System.out.println("Vælg en aktivitet at registrere tid på:");
		bruger.printAktiviteter();
		int valg = scanner.nextInt();
		System.out.println("Indtast hvor meget tid der skal registreres:");
		double tid = scanner.nextDouble();
		bruger.aktiviteter.get(valg).satTid(bruger, tid);
	}

	public static void satStatus() {
		System.out.println("Indtast ny status:");
		menu.status();
		bruger.satStatus(scanner.nextInt());
		System.out.println("Indtast start-dato DDMMYYYY:");
		bruger.statusFra(scanner.nextInt());
		System.out.println("Indtast slut-dato DDMMYYYY:");
		bruger.statusTil(scanner.nextInt());
	}

	public static void anmodHjalp() {
		System.out.println("Indtast initialer for brugeren der anmodes");
		String hjalp = scanner.next();
		if (oversigt.tjekMedarbejder(hjalp)) {

			System.out.println("Hvilken aktivitet anmoder du om hjælp til?");
			bruger.printAktiviteter();
			int aktivitet = scanner.nextInt();

			oversigt.fåMedarbejder(hjalp).tilfojAktivitet(bruger.aktiviteter.get(aktivitet));
		} else {
			fejlbesked.satFejlbesked("Der eksisterer ikke en bruger med intialerne " + hjalp);
			System.out.println(fejlbesked.faFejlbesked());
		}
	}

	private static void sletAktivitet() {
		System.out.println("Hvilket af følgende projekter vil du slette en aktivitet fra?");
		bruger.projektlederFor(bruger);
		int projekt = scanner.nextInt();

		if (!bruger.projekter.get(projekt).aktivitetsliste.isEmpty()) {
			System.out.println("Hvilken af følgende aktiviteter vil du slette?");
			bruger.projekter.get(projekt).printAktiviteter();
			int aktivitet = scanner.nextInt();
			bruger.projekter.get(projekt).fjernAktivitet(bruger.aktiviteter.get(aktivitet));
		} else {
			fejlbesked.satFejlbesked("Projektet " + bruger.projekter.get(projekt).navn + " er tomt for aktiviteter");
		}
	}

	private static void sletProjekt() {
		System.out.println("Hvilket af følgende projekter vil du slette?");
		bruger.projektlederFor(bruger);
		int projekt = scanner.nextInt();

		oversigt.fjernProjekt(bruger.projekter.get(projekt));
		bruger.fjernProjekt(bruger.projekter.get(projekt));

	}

	private static void startSlutAktivitet() {
		System.out.println("Hvilket projekt (projektnummer) hører aktiviteten under?");
		bruger.projektlederFor(bruger);
		int projekt = scanner.nextInt();

		System.out.println("Hvilken aktivitet?");
		bruger.projekter.get(projekt).printAktiviteter();
		int aktivitet = scanner.nextInt();

		System.out.println("Angiv startdato DDMMYYYY");
		int start = scanner.nextInt();

		bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).start.set(Calendar.DAY_OF_MONTH, start / 1000000);
		bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).start.set(Calendar.MONTH, (start / 10000) % 100);
		bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).start.set(Calendar.YEAR, start % 10000);

		System.out.println("Angiv slutdato DDMMYYYY");
		int slut = scanner.nextInt();

		bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).slut.set(Calendar.DAY_OF_MONTH, slut / 1000000);
		bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).slut.set(Calendar.MONTH, (slut / 10000) % 100);
		bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).slut.set(Calendar.YEAR, slut % 10000);

		if (bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).slut
				.before(bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).start)) {

			bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).start = null;
			bruger.projekter.get(projekt).aktivitetsliste.get(aktivitet).slut = null;

			fejlbesked.satFejlbesked("Ugyldige datoer");
			System.out.println(fejlbesked.faFejlbesked());
		}

	}

	private static void startSlutProjekt() {
		System.out.println("Hvilket projekt (projektnummer)?");
		bruger.projektlederFor(bruger);
		int projekt = scanner.nextInt();

		System.out.println("Angiv startdato DDMMYYYY");
		int start = scanner.nextInt();

		bruger.projekter.get(projekt).start.set(Calendar.DAY_OF_MONTH, start / 1000000);
		bruger.projekter.get(projekt).start.set(Calendar.MONTH, (start / 10000) % 100);
		bruger.projekter.get(projekt).start.set(Calendar.YEAR, start % 10000);

		System.out.println("Angiv slutdato DDMMYYYY");
		int slut = scanner.nextInt();

		bruger.projekter.get(projekt).slut.set(Calendar.DAY_OF_MONTH, slut / 1000000);
		bruger.projekter.get(projekt).slut.set(Calendar.MONTH, (slut / 10000) % 100);
		bruger.projekter.get(projekt).slut.set(Calendar.YEAR, slut % 10000);

		if (bruger.projekter.get(projekt).slut.before(bruger.projekter.get(projekt).start)) {

			bruger.projekter.get(projekt).start = null;
			bruger.projekter.get(projekt).slut = null;

			fejlbesked.satFejlbesked("Ugyldige datoer");
			System.out.println(fejlbesked.faFejlbesked());
		}

	}

	private static void brugerProjekt() {
		System.out.println("Vælg et projekt:");
		
		System.out.println(bruger.projektlederFor(bruger).size());
		System.out.println(bruger.projekter.size());
		
		bruger.printProjektlederFor(bruger.projektlederFor(bruger));
		int projekt = scanner.nextInt();
		
		System.out.println("Hvilken bruger vil du tilføje?");
		oversigt.printLedige(oversigt.ledigeBrugere(bruger.projektlederFor(bruger).get(projekt)));
		int tilføj = scanner.nextInt();
		
		Bruger ledig = oversigt.ledigeBrugere(bruger.projektlederFor(bruger).get(projekt)).get(tilføj); 

			if (bruger.projektlederFor(ledig).get(projekt).tjekForMedarbejder(ledig.initialer)) {
				fejlbesked.satFejlbesked("Brugeren " + ledig.initialer + " er allerede en del af projektet "
						+ bruger.projektlederFor(bruger).get(projekt).navn);
				System.out.println(fejlbesked.faFejlbesked());
			} else {
				bruger.projektlederFor(bruger).get(projekt).tilfojmedarbejder(ledig);
				ledig.tilfojProjekt(bruger.projektlederFor(bruger).get(projekt));
			}
	}

	private static void brugerAktivitet() {

		System.out.println("Hvilken bruger vil du tilføjer?");
		String initialer = scanner.next();

		if (oversigt.tjekMedarbejder(initialer)) {

			System.out.println("Vælg et projekt:");
			bruger.printProjektlederFor(bruger.projektlederFor(bruger));
			int projekt = scanner.nextInt();

			if (!bruger.projektlederFor(bruger).get(projekt).tjekForMedarbejder(initialer)) {

				System.out.println(
						"Brugeren kan ikke tilføjes til aktiviteten uden at være en del af det tilhørende projekt");
				System.out.println("Vil du tilføje " + initialer + " til projektet "
						+ bruger.projekter.get(projekt).navn + " (J/N)?");
				char valg = scanner.next().charAt(0);

				if (valg == 'N' || valg == 'n') {
					return;
				}

				bruger.projektlederFor(bruger).get(projekt).tilfojmedarbejder(oversigt.fåMedarbejder(initialer));
				oversigt.fåMedarbejder(initialer).tilfojProjekt(bruger.projektlederFor(bruger).get(projekt));
			}

			System.out.println("Vælg en aktivitet:");
			bruger.projektlederFor(bruger).get(projekt).printAktiviteter();
			int aktivitet = scanner.nextInt();

			if (oversigt.fåMedarbejder(initialer)
					.tjekAktivitet(bruger.projektlederFor(bruger).get(projekt).aktivitetsliste.get(aktivitet).navn)) {

				fejlbesked.satFejlbesked("Brugeren er allerde en del af aktiviteten");
				System.out.println(fejlbesked.faFejlbesked());

			} else {

				oversigt.fåMedarbejder(initialer)
						.tilfojAktivitet(bruger.projektlederFor(bruger).get(projekt).aktivitetsliste.get(aktivitet));
			}

		} else {

			fejlbesked.satFejlbesked("Brugeren " + initialer + " eksisterer ikke");
			System.out.println(fejlbesked.faFejlbesked());
		}

	}

	private static void budget() {

		System.out.println("Hvilket projekt vil du sætte budgetteret tid for?");
		bruger.projektlederFor(bruger);
		int projekt = scanner.nextInt();

		System.out.println("Hvor mange timer skal projektet budgetteres til?");
		double timer = scanner.nextDouble();

		bruger.projekter.get(projekt).budgetteretTid = timer;
	}

	private static void opfolgning() {

		System.out.println("Hvilket projekt vil du indhente opfølgning om?");
		bruger.projektlederFor(bruger);
		int projekt = scanner.nextInt();

		bruger.projekter.get(projekt).opfolgning();

	}

	public static void options() {
		int valg = scanner.nextInt();

		//test mulighed
		if (valg == 0) {
			System.out.println(oversigt.brugere.size());
		}
		
		
		if (valg == 1) {
			opretBruger();
		}else if (valg == 2) {
			projektleder();
		} else if (valg == 3) {
			opretProjekt();
		} else if (valg == 4) {
			registrerTid();
		} else if (valg == 5) {
			bruger.printTid(bruger);
		} else if (valg == 6) {
			satStatus();
		} else if (valg == 7) {
			anmodHjalp();
		} else if (valg == 8) {
			loggedind = false;
		} else if (valg == 9) {
			loggedind = false;
			tændt = false;
		} else if (valg == 10) {
			opretAktivitet();
		} else if (valg == 11) {
			sletAktivitet();
		} else if (valg == 12) {
			sletProjekt();
		} else if (valg == 13) {
			brugerProjekt();
		} else if (valg == 14) {
			brugerAktivitet();
		} else if (valg == 15) {
			opfolgning();
		} else if (valg == 16) {
			System.out.println("Vil du angive budgetteret tid eller start/slut-dato?");
			System.out.println("1: budgetteret tid");
			System.out.println("2: start/slut-dato");
			int valg2 = scanner.nextInt();

			if (valg2 == 1) {
				budget();
			} else if (valg2 == 2) {
				System.out.println("Vil du angive tid for et projekt eller en aktivitet?");
				System.out.println("1: aktivitet");
				System.out.println("2: projekt");
				
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
