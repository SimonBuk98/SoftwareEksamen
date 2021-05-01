package dtu.calculator;

import java.util.ArrayList;

public class Bruger {
	
	public  String initialer;
	public boolean projektleder;
	public double tidsforbrug;
	public  ArrayList<Aktivitet> aktiviteter = new ArrayList<Aktivitet>();
	public  ArrayList<Projekt> projekter = new ArrayList<Projekt>();
	public int status;

	public Bruger(String initialer){
		this.initialer = initialer;
	}

	public String faInitialer() {
		return initialer;
	}
	
	public double faTidforbrug(){
		return tidsforbrug;
	}

	public int faStatus(){
		return status;
	}
	
	public boolean projektleder(){
		return projektleder;
	}

	public void tilfojAktivitet(Aktivitet aktivitet){
		aktiviteter.add(aktivitet);
	}

	public  void tilfojProjekt(Projekt projekt){
		projekter.add(projekt);
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

	public void printProjekter(Bruger bruger){
		for (int i = 0; i < projekter.size(); i++){
			if (projekter.get(i).projektleder == bruger) {
			System.out.println(i + ": " + projekter.get(i).navn);
			}
		}	
	}
	
	public void printTid(Bruger bruger) {
		for (int i = 0; i < aktiviteter.size(); i++){
			System.out.println("Registreret tid på " + aktiviteter.get(i).navn + ": " + aktiviteter.get(i).tjekMedarbejder(bruger));
		}	
	}
	

}
