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

	public static boolean tjekMedarbejder(String initialer) {
		for (int i = 0; i < brugere.size(); i++) {
			if (brugere.get(i).initialer == initialer) {
				return true;
			}
		}
		return false;	
	}

	public static boolean tjekProjekt(String projekt ,int nummer){
		for (int i = 0; i < projekter.size(); i++) {
			if (projekter.get(i).projektnummer == nummer && projekter.get(i).navn == projekt) {
				return true;
			}
		}
		return false;
	}

	public Bruger fåMedarbejder(String initialer) {
		int j = 0;
		for (int i = 0; i < brugere.size(); i++) {
			if (brugere.get(i).initialer.toLowerCase() == initialer.toLowerCase()) {
				j = i;
				break;
			}
		}
		return brugere.get(j);
	}

	public Projekt faProjekt(int nummer) {
		int j = 0;
		for (int i = 0; i < brugere.size(); i++) {
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

}