package dtu.calculator;

import java.util.ArrayList;

public class Bruger {
	
	public  String initialer;
	public boolean projektleder;
	public  ArrayList<Aktivitet> aktiviteter = new ArrayList<Aktivitet>();
	public  ArrayList<Projekt> projekter = new ArrayList<Projekt>();
	public ArrayList<Status> status = new ArrayList<Status>();

	public Bruger(String initialer){
		this.initialer = initialer;
	}
	
	public void satStatus(int status) {
		this.status.add(new Status(status));
	}
	
	public void statusFra(int fra) {
		int dag = fra / 1000000;
		int måned = (fra / 10000) % 100;
		int år = fra % 10000;
		status.get(status.size()-1).fra = Status.dato(dag, måned, år);
	}
	
	public void statusTil(int til) {
		int dag = til / 1000000;
		int måned = (til / 10000) % 100;
		int år = til % 10000;
		status.get(status.size()-1).til = Status.dato(dag, måned, år);
	}
	
	public boolean ledig(Projekt projekt) {
		boolean ledig = true;
		for (int i = 0; i < status.size(); i++) {
			if (!status.get(i).tjekLedighed(projekt)) {
				ledig = false;
			}
		}
		return ledig;
	}

	public String faInitialer() {
		return initialer;
	}
	
	public boolean projektleder(){
		return projektleder;
	}

	public void tilfojAktivitet(Aktivitet aktivitet, Bruger bruger){
		aktiviteter.add(aktivitet);
		aktivitet.tider.add(new Tidsforbrug(bruger));
	}

	public  void tilfojProjekt(Projekt projekt){
		projekter.add(projekt);
	}
	
	public void fjernProjekt(Projekt projekt) {
		projekter.remove(projekt);
	}
	
	public boolean tjekAktivitet(String aktivitet) {
		for(int i = 0; i < aktiviteter.size(); i++){
			if (aktivitet == aktiviteter.get(i).navn){
				return true;
			}
		}
		return false;
	}
	public void printAktiviteter(){
		for (int i = 0; i < aktiviteter.size(); i++){
			System.out.println(i + ": " + aktiviteter.get(i).navn);
		}	
	}
	
	public void printProjekter(){
		for (int i = 0; i < projekter.size(); i++){
			System.out.println(i + ": " + projekter.get(i).navn);
		}	
	}

	public ArrayList<Projekt> projektlederFor(Bruger bruger){
		ArrayList<Projekt> samlet = new ArrayList<Projekt>();
		for (int i = 0; i < projekter.size(); i++){
			if (projekter.get(i).projektleder == bruger) {
			samlet.add(projekter.get(i));
			}
		}
		return samlet;
	}
	
	public void printProjektlederFor(ArrayList<Projekt> projekter){
		for (int i = 0; i < projekter.size(); i++){
			System.out.println(i + ": " + projekter.get(i).navn);
		}	
	}
	
	public void printTid(Bruger bruger) {
		for (int i = 0; i < aktiviteter.size(); i++){
			System.out.println("Registreret tid på " + aktiviteter.get(i).navn + ": " + aktiviteter.get(i).tjekMedarbejder(bruger));
		}	
	}
	

}
