package dtu.calculator;

import java.util.Calendar;

public class Status {
	
	Calendar fra;
	Calendar til;
	int status;
	
	public Status(int status) {
		this.status = status;
	}

	public boolean tjekLedighed(Projekt projekt) {
		if (status > 1) {
			if (fra.before(projekt.start)) {
				if (til.after(projekt.start)) {
					return false;
				}
			} else if (fra.before(projekt.slut)) {
				return false;
				}
			}
	return true;
	}
	
	public boolean tjekLedighed(Aktivitet aktivitet) {
		if (status == 1) {
			if (fra.before(aktivitet.start)) {
				if (til.after(aktivitet.start)) {
					return false;
				}
			} else if (fra.before(aktivitet.slut)) {
				return false;
				}
			}
	return true;	
	}
	
	public static Calendar dato(int dag, int måned, int år) {
		Calendar dato = Calendar.getInstance();
		dato.set(år, måned, dag, 0, 0);
		return dato;
	}

}
