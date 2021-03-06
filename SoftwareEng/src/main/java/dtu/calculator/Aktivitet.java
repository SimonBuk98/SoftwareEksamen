package dtu.calculator;
import java.util.ArrayList;
import java.util.Calendar;

public class Aktivitet {

    String navn;
    public  ArrayList<Tidsforbrug> tider = new ArrayList<Tidsforbrug>();
    Calendar start = Calendar.getInstance();
    Calendar slut = Calendar.getInstance();

    
    
    public Aktivitet(String navn){
        this.navn = navn;
    }

   //s204501
    public double tjekMedarbejder(Bruger bruger){
		for (int i = 0; i < tider.size(); i++){
            if (bruger == tider.get(i).bruger){
                return (tider.get(i).tid);
            }
        }
        return 0;
	}

    //s204497
    public boolean satTid(Bruger bruger, double tid){
        for (int i = 0; i < tider.size(); i++){
            if (bruger == tider.get(i).bruger){
                tider.get(i).satTid(tid);
                return true;
            }
        }
        return false;
    }

    //s193939
    public double sumTid(){
    	double sum = 0;
    	for (int i = 0; i < tider.size(); i++){
    		sum += tider.get(i).tid;
        }
    	return sum;
    }
}
