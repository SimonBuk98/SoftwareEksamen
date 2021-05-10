package dtu.calculator;

import java.util.ArrayList;

public class Oversigt {

	static ArrayList<Bruger> brugere = new ArrayList<>();
	static ArrayList<Projekt> projekter = new ArrayList<Projekt>();
	
	public Oversigt(){
		
	}


	public void tilføjMedarbjeder(Bruger bruger) {
		brugere.add(bruger);
	}

	public void tilføjProjekt(Projekt projekt) {
		projekter.add(projekt);
	}
	
	public void fjernProjekt(Projekt projekt) {
		projekter.remove(projekt);
	}

	//s193939
	public static boolean tjekMedarbejder(String initialer) {
		for (int i = 0; i < brugere.size(); i++) {
			if (brugere.get(i).initialer.equals(initialer)) {
				return true;
			}
		}
		return false;	
	}

	//s191252
	public static boolean tjekProjekt(String projekt){
		for (int i = 0; i < projekter.size(); i++) {
			if (projekter.get(i).navn.equals(projekt)) {
				return true;
			}
		}
		return false;
	}

	//s204501
	public Bruger fåMedarbejder(String initialer) {
		int j = 0;
		for (int i = 0; i < brugere.size(); i++) {
			if (brugere.get(i).initialer.equals(initialer)) {
				j = i;
				break;
			}
		}
		return brugere.get(j);
	}

	//s204497
	public static Projekt faProjekt(String navn) {
		int j = 0;
		for (int i = 0; i < projekter.size(); i++) {
			if (projekter.get(i).navn.equals(navn)) {
				j = i;
				break;
			}
		}
		return projekter.get(j);
	}

	public boolean tjekProjekt(Projekt projekt) {
		return projekter.contains(projekt);
	}
	
	public void printProjekter() {
		String alle = "";
		for (int i=0; i < projekter.size(); i++) {
			System.out.println(i + ": " + projekter.get(i).navn + "-" + projekter.get(i).projektnummer);
		}
	}
	
	//s193939
	public ArrayList<Bruger> ledigeBrugere(Projekt projekt) {
		ArrayList<Bruger> ledige = new ArrayList<Bruger>();
		for (int i = 0; i < brugere.size(); i++) {
			if (brugere.get(i).ledig(projekt) && !projekt.tjekForMedarbejder(brugere.get(i).initialer)) {
				ledige.add(brugere.get(i));
			}
		}
		
		return ledige;
	}
	
	//s191252
	public void printLedige(ArrayList<Bruger> ledige) {
		for (int i = 0; i < ledige.size(); i++) {
				System.out.println(i + ": " + ledige.get(i).initialer);
			}
		}

}
