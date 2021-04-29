package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Projekt {

	String navn;
	int projektnummer;

	static Bruger projektleder;

	static ArrayList<Bruger> medarbejderliste = new ArrayList<Bruger>();
	static ArrayList<Aktivitet> aktivitetsliste = new ArrayList<Aktivitet>();

	GregorianCalendar start;
	GregorianCalendar slut;

	public Projekt(String navn, int projektnummer) {
		this.navn = navn;
		this.projektnummer = projektnummer;
	}

	public void satTid(GregorianCalendar start, GregorianCalendar slut) {

	}

	public void tilfojmedarbejder(Bruger brugeren) {

	}

	public static boolean tjekForMedarbejder(String bruger){
		for(int i = 0; i <= medarbejderliste.size(); i++){
			if (bruger == medarbejderliste.get(i).bruger){
				return true;
			}
		}
	}

    public static boolean tjekAktivitet(String navn){
		for (int i = 0; i < aktivitetsliste.size(); i++) {
			if (aktivitetsliste.get(i).navn.toLowerCase() == navn.toLowerCase()) {
				return true;
			}
		}
		return false;
	}

	public void tilfojAktivitet(Aktivitet aktiviteten) {

	}

	public void fjernAktivitet(Aktivitet fjern) {

	}

	public void indhendtOpfolgning() {

	}

}
