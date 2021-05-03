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

	public static boolean tjekMedarbejder(String initialer) {
		for (int i = 0; i < brugere.size(); i++) {
			if (brugere.get(i).initialer.equals(initialer)) {
				return true;
			}
		}
		return false;	
	}

	public static boolean tjekProjekt(String projekt ,int nummer){
		for (int i = 0; i < projekter.size(); i++) {
			if (projekter.get(i).projektnummer == nummer && projekter.get(i).navn.equals(projekt)) {
				return true;
			}
		}
		return false;
	}

	public Bruger fåMedarbejder(String initialer) {
		int j = 0;
		for (int i = 0; i < brugere.size(); i++) {
			if (brugere.get(i).initialer == initialer) {
				j = i;
				break;
			}
		}
		return brugere.get(j);
	}

	public static Projekt faProjekt(int nummer) {
		int j = 0;
		for (int i = 0; i < projekter.size(); i++) {
			if (projekter.get(i).projektnummer == nummer) {
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

}
