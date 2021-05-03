package dtu.calculator;

import java.util.ArrayList;
import java.util.Calendar;

public class Projekt {

	String navn;
	int projektnummer;

	static Bruger projektleder;
	

	static ArrayList<Bruger> medarbejderliste = new ArrayList<Bruger>();
	static ArrayList<Aktivitet> aktivitetsliste = new ArrayList<Aktivitet>();

	Calendar start;
	Calendar slut;

	double budgetteretTid;

	Fejlbesked fejl = new Fejlbesked();

	public Projekt(String navn, int projektnummer) {
		this.navn = navn;
		this.projektnummer = projektnummer;
	}

	public void tilfojmedarbejder(Bruger brugeren) {
		medarbejderliste.add(brugeren);
	}

	public static boolean tjekForMedarbejder(String bruger) {
		for (int i = 0; i < medarbejderliste.size(); i++) {
			if (bruger == medarbejderliste.get(i).initialer) {
				return true;
			}
		}
		return false;
	}

	public static boolean tjekAktivitet(String navn) {
		for (int i = 0; i < aktivitetsliste.size(); i++) {
			if (aktivitetsliste.get(i).navn == navn) {
				return true;
			}
		}
		return false;
	}

	public static Aktivitet fåAktivitet(String aktivitetsnavn) {
		int j = 0;
		for (int i = 0; i < aktivitetsliste.size(); i++) {
			if (aktivitetsliste.get(i).navn == aktivitetsnavn) {
				j = i;
				break;
			}
		}
		return aktivitetsliste.get(j);
	}

	public void printAktiviteter() {
		if (!aktivitetsliste.isEmpty()) {
			for (int i = 0; i < aktivitetsliste.size(); i++) {
				System.out.println(i + ": " + aktivitetsliste.get(i).navn);
			}
		}

		else {
			fejl.satFejlbesked("Projektet er tomt");
			System.out.println(fejl.faFejlbesked());
		}
	}

	public void tilfojAktivitet(Aktivitet aktiviteten) {
		aktivitetsliste.add(aktiviteten);
	}

	public void fjernAktivitet(Aktivitet fjern) {
		aktivitetsliste.remove(fjern);
	}

	public void opfolgning() {
		double sum = 0;
		System.out.println("Tidsforbrug for aktiviteter under projektet " + navn + ":");
		for (int i = 0; i < aktivitetsliste.size(); i++) {
		
			System.out.println(aktivitetsliste.get(i).navn + ": " + aktivitetsliste.get(i).sumTid() + " timer");
			sum = sum + aktivitetsliste.get(i).sumTid();
		}
		System.out.println("Samlet tidsforbrug for projekt: " + sum + " timer");
		System.out.println("Forventet restarbejder: " + (budgetteretTid-sum) + " timer");
	}

}
