package dtu.calculator;
import java.util.ArrayList;
import java.util.Calendar;

public class Aktivitet {

    public String navn;
    public static  ArrayList<Tidsforbrug> tider = new ArrayList<Tidsforbrug>();
    Calendar start = Calendar.getInstance();
    Calendar slut = Calendar.getInstance();

    
    public Aktivitet(String navn){
        this.navn = navn;
    }

   
    public double tjekMedarbejder(Bruger bruger){
		for (int i = 0; i < tider.size(); i++){
            if (bruger == tider.get(i).bruger){
                return (tider.get(i).tid);
            }
        }
        return 0;
	}

    public boolean satTid(Bruger bruger, double tid){
    	System.out.println("Størrelse på tider: " + tider.size());
        for (int i = 0; i < tider.size(); i++){
            if (bruger == tider.get(i).bruger){
            	System.out.println("inde hos " + tider.get(i).bruger.initialer);
                tider.get(i).satTid(tid);
                return true;
            }
        }
        return false;
    }

    public static double sumTid(){
    	double sum = 0;
    	for (int i = 0; i < tider.size(); i++){
    		sum += tider.get(i).tid;
        }
    	return sum;
    }
}
