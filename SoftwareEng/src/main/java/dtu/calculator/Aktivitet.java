package dtu.calculator;
import java.util.ArrayList;

public class Aktivitet {

    public String navn;
    public static  ArrayList<Tidsforbrug> tider = new ArrayList<Tidsforbrug>();

    
    public Aktivitet(String navn){
        this.navn = navn;
    }

    public static void tilfojMedarbejder(Bruger bruger){
		
	}
   
    public double tjekMedarbejder(Bruger bruger){
		for (int i = 0; i < tider.size(); i++){
            if (bruger == tider.get(i).bruger){
                return (tider.get(i).tid);
            }
        }
        return 0;
	}

    public void satTid(Bruger bruger, double tid){
        for (int i = 0; i < tider.size(); i++){
            if (bruger == tider.get(i).bruger){
                tider.get(i).satTid(tid);
                break;
            }
        }
    }

    public static void sumTid(){
        
    }
}
