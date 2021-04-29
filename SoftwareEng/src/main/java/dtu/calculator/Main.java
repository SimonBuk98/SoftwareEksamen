package dtu.calculator;

public class Main {

    App app = new App();


    public void main(String[] args){

        app.oversigt.brugere.add(new Bruger("ABC"));
        System.out.println(app.oversigt.tjekMedarbejder("ABC"));
        
    }

}
