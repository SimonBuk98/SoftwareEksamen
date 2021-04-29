package dtu.calculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ExampleSteps {
	public App app;
	public Fejlbesked fejlbesked;

	public ExampleSteps(App app) {
		this.app = app;
	}

	@Given("bruger er logget ind")
	public void bruger_er_logget_ind() {
		Bruger bruger = new Bruger("ABC");
		app.bruger = bruger;
		app.loggedind = true;
		assertTrue(App.loggedind);

	}

	@When("bruger laver en bruger med initialerne {string}")
	public void bruger_laver_en_bruger_med_initialerne(String string) {
		app.oversigt.brugere.add(new Bruger("ABC"));

	}
	@Then("brugeren med initialerne {string} findes")
	public void brugeren_med_initialerne_findes(String string) {
		assertTrue(app.oversigt.tjekMedarbejder("ABC"));
	}

	@Given("der eksisterer en bruger med intialerne {string}")
	public void der_eksisterer_en_bruger_med_intialerne(String string) {
		assertTrue(app.oversigt.tjekMedarbejder("ABC"));
	}

	@When("bruger opretter en bruger med initialerne {string}")
	public void bruger_opretter_en_bruger_med_initialerne(String initialer){
		if (!app.oversigt.tjekMedarbejder(("ABC"))){
			app.oversigt.brugere.add(new Bruger("ABC"));
		}
		else {
			app.fejlbesked.satFejlbesked("Disse intialer er allerede taget");
			app.fejlbesked.faFejlbesked();
		}

	}

	@Then("fås fejlmeddelsen {string}")
	public void fås_fejlmeddelsen(String fejlbeskeder)  {
		assertThat(app.fejlbesked.faFejlbesked(),is(equalTo(fejlbeskeder)));	
	}
	@When("bruger opretter et projekt med navnet {string}")
	public void bruger_opretter_et_projekt_med_navnet(String string) {
			Oversigt.projekter.add(new Projekt("projekt",1998));
	
	}

	@Then("et projekt {string} er oprettet")
	public void et_projekt_er_oprettet(String projekt) {
		assertTrue(app.oversigt.tjekProjekt("projekt", 1998));
	}

	@Then("et projekt med navnet {string} og et projektnummer eksisterer")
	public void et_projekt_med_navnet_og_et_projektnummer_eksisterer(String projekt) {
		assertTrue(app.oversigt.tjekProjekt("projekt", 1998));
	}


	@Given("bruger er ikke logget ind")
	public void bruger_er_ikke_logget_ind() {
		assertFalse(!app.loggedind);
		app.fejlbesked.satFejlbesked("Bruger skal være logget ind");
		app.fejlbesked.faFejlbesked();
	}
	@Given("en bruger med initialerne “ABC” findes")
	public void en_bruger_med_initialerne_abc_findes() {
		assertTrue(app.oversigt.tjekMedarbejder("ABC"));
	}

	@When("admin tilføjer brugeren “ABC” som projektleder")
	public void admin_tilføjer_brugeren_abc_som_projektleder() {
	   Projekt.projektleder = app.oversigt.fåMedarbejder("ABC");
	}

	@Then("en projektleder er oprettet")
	public void en_projektleder_er_oprettet() {
	  assertTrue(app.bruger == app.oversigt.faProjekt(1998).projektleder );
	}

	@Then("en projektleder med initialerne “ABC” findes")
	public void en_projektleder_med_initialerne_abc_findes() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}