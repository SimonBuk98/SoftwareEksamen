package dtu.calculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Locale;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ExampleSteps {
	public App app;
	public Fejlbesked fejlbesked = new Fejlbesked();

	public ExampleSteps(App app) {
		this.app = app;
	}

	@Given("bruger {string} er logget ind")
	public void bruger_er_logget_ind(String navn) {
		Bruger bruger = new Bruger(navn);
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
	public void bruger_opretter_en_bruger_med_initialerne(String initialer) {
		if (!app.oversigt.tjekMedarbejder(("ABC"))) {
			app.oversigt.brugere.add(new Bruger("ABC"));
		} else {
			app.fejlbesked.satFejlbesked("Disse intialer er allerede taget");
			app.fejlbesked.faFejlbesked();
		}

	}

	@Then("fås fejlmeddelsen {string}")
	public void fås_fejlmeddelsen(String fejlbeskeder) {
		assertThat(app.fejlbesked.faFejlbesked(), is(equalTo(fejlbeskeder)));
	}

	@When("bruger opretter et projekt med navnet {string}")
	public void bruger_opretter_et_projekt_med_navnet(String string) {
		Oversigt.projekter.add(new Projekt(string, App.oversigt));

	}

	@Then("et projekt {string} er oprettet")
	public void et_projekt_er_oprettet(String projekt) {
		assertTrue(app.oversigt.tjekProjekt("Projekt"));
	}

	@Then("et projekt med navnet {string} og et projektnummer eksisterer")
	public void et_projekt_med_navnet_og_et_projektnummer_eksisterer(String projekt) {
		assertTrue(app.oversigt.tjekProjekt("Projekt"));
	}

	@Given("bruger er ikke logget ind")
	public void bruger_er_ikke_logget_ind() {
		assertFalse(!app.loggedind);
		app.fejlbesked.satFejlbesked("Bruger skal være logget ind");
		app.fejlbesked.faFejlbesked();
	}
//
//	@Given("en bruger med initialerne {string} findes")
//	public void en_bruger_med_initialerne_abc_findes(String navn) {
//		Bruger bruger = new Bruger(navn);
//		Oversigt.brugere.add(bruger);
//		assertTrue(app.oversigt.tjekMedarbejder(navn));
//	}
//
//	@When("brugeren tilføjer brugeren {string} som projektleder")
//	public void admin_tilføjer_brugeren_abc_som_projektleder(String navn) {
//		Projekt.projektleder = app.oversigt.fåMedarbejder(navn);
//	}
//
//	@Then("en projektleder med initialerne {string} findes")
//	public void en_projektleder_med_initialerne_abc_findes() {
//		assertTrue(app.oversigt.faProjekt("Projekt").projektleder == app.oversigt.fåMedarbejder("ABC"));
//	}
//
//	@Then("en projektleder er oprettet")
//	public void en_projektleder_er_oprettet() {
//		if (app.oversigt.tjekMedarbejder("NEJ") == false) {
//			app.fejlbesked.satFejlbesked("Brugeren skal være oprettet i systemet");
//			app.fejlbesked.faFejlbesked();
//		} else {
//			Projekt.projektleder = app.oversigt.fåMedarbejder("NEJ");
//		}
//
//	}
//
//	@Then("en fejlbesked “Brugeren skal være oprettet i systemet” er givet")
//	public void en_fejlbesked_brugeren_skal_være_oprettet_i_systemet_er_givet() {
//
//	}
//
//	@Given("at brugeren \"ABC” \"projektleder")
//	public void at_brugeren_abc_projektleder() {
//		Projekt.projektleder = app.oversigt.fåMedarbejder("ABC");
//	}
//
//	@Given("projektet {string} eksisterer")
//	public void projektet_eksisterer(String projekt) {
//		assertTrue(app.oversigt.tjekProjekt("projekt"));
//	}
//
//	@Given("medarbejderen {string} eksisterer")
//	public void medarbejderen_eksisterer(String string) {
//		app.oversigt.brugere.add(new Bruger("HEJ"));
//		assertTrue(app.oversigt.tjekMedarbejder("HEJ"));
//	}
//
//	@Given("medarbejderen ikke er en del af projektet")
//	public void medarbejderen_ikke_er_en_del_af_projektet() {
//		assertTrue(app.oversigt.faProjekt("Projekt").tjekForMedarbejder("HEJ"));
//	}
//
//	@When("brugeren tilføjer medarbejderen til projektet")
//	public void brugeren_tilføjer_medarbejderen_til_projektet() {
//		app.oversigt.faProjekt("Projekt").tilfojmedarbejder(app.oversigt.fåMedarbejder("HEJ"));
//	}
//
//	@Then("brugeren er tilføjet til projektet")
//	public void brugeren_er_tilføjet_til_projektet() {
//		assertTrue(app.oversigt.faProjekt("Projekt").tjekForMedarbejder("HEJ"));
//	}
//
//	@Given("at brugeren {string} er eksisterer")
//	public void at_brugeren_er_eksisterer(String string) {
//		assertTrue(app.oversigt.tjekMedarbejder("ABC")); 
//	}
//
//	@Given("projektet {string} ikke eksisterer")
//	public void projektet_ikke_eksisterer(String string) {
//		if (app.oversigt.tjekProjekt("Test", 1997) == false)
//			app.fejlbesked.satFejlbesked("Brugeren skal være oprettet i systemet");
//		app.fejlbesked.faFejlbesked();
//
//	}
//	@Then("fås fejlmeddelelsen {string}")
//	public void fås_fejlmeddelelsen(String string) {
//
//	}
//	@Given("brugeren {string} projektleder")
//	public void brugeren_projektleder(String string) {
//		Oversigt.projekter.add(new Projekt("Pacman", 9999));
//		app.oversigt.faProjekt(9999).projektleder = app.oversigt.fåMedarbejder("ABC");
//
//	}
//
//	@Given("aktiviteten {string} eksisterer")
//	public void aktiviteten_eksisterer(String string) {
//		app.oversigt.faProjekt(9999).tilfojAktivitet(new Aktivitet("Ghost"));
//		assertTrue(app.oversigt.faProjekt(9999).tjekAktivitet("Ghost"));
//	}
//
//	@Given("medarbejderen er del af projektet")
//	public void medarbejderen_er_del_af_projektet() {
//		app.oversigt.faProjekt(9999).tilfojmedarbejder(app.oversigt.fåMedarbejder("HEJ"));
//		assertTrue(app.oversigt.faProjekt(9999).tjekForMedarbejder("HEJ"));
//	}
//
//	@When("brugeren tilføjer medarbejderen til aktiviteten")
//	public void brugeren_tilføjer_medarbejderen_til_aktiviteten() {
//		app.bruger.tilfojAktivitet(app.oversigt.faProjekt(9999).fåAktivitet("Ghost"));	
//	}
//
//	@Then("brugeren er en del af aktiviteten")
//	public void brugeren_er_en_del_af_aktiviteten() {
//		assertTrue(app.bruger.tjekAktivitet("Ghost"));
//	}
//	@Given("brugeren {string} eksisterer")
//	public void brugeren_eksisterer(String string) {
//		app.oversigt.brugere.add(new Bruger("AUD"));
//		assertTrue(app.oversigt.tjekMedarbejder("AUD"));
//	}
//
//	@Given("brugeren er ikke del af projektet")
//	public void brugeren_er_ikke_del_af_projektet() {
//		assertFalse(app.oversigt.faProjekt(9999).tjekForMedarbejder("AUD"));
//	}
//
//	@When("den aktive bruger tilføjer den anden bruger til aktiviteten")
//	public void den_aktive_bruger_tilføjer_den_anden_bruger_til_aktiviteten() {
//		if(app.oversigt.faProjekt(9999).tjekForMedarbejder("AUD")==false) {
//			app.fejlbesked.satFejlbesked("Bruger er ikke tildelt projekt");
//			app.fejlbesked.faFejlbesked();
//		}else if(app.oversigt.faProjekt(9999).tjekForMedarbejder("AUD")==true) {
//			app.bruger.tilfojAktivitet(app.oversigt.faProjekt(9999).fåAktivitet("Ghost"));
//		}
//	}
////	@Given("aktivitet {string} eksisterer under projektet {int}")
////	public void aktivitet_eksisterer_under_projektet(String string, Integer int1) {
////		assertTrue(app.oversigt.faProjekt(9999).tjekAktivitet("Ghost"));
////	}
//
	@Given("bruger er tildelt aktivitet {string}")
	public void bruger_er_tildelt_aktivitet(String string) {
		app.oversigt.fåMedarbejder("ABC").tilfojAktivitet(new Aktivitet("ABE"));
		assertTrue(app.oversigt.fåMedarbejder("ABC").tjekAktivitet("ABE"));
	}

	@When("bruger {string} registrerer tid arbejdet på aktiviteten {string} under projektet {string}")
	public void bruger_registrerer_tid_arbejdet_på_aktiviteten_under_projektet(String bruger, String aktivitet, String projekt) {
		if(app.oversigt.fåMedarbejder(bruger).tjekAktivitet(aktivitet)==true) {
			assertTrue(app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).satTid(app.oversigt.fåMedarbejder(bruger), 1));
		}else {
			app.fejlbesked.satFejlbesked("Ulovlig tidsregistrering");
			app.fejlbesked.faFejlbesked();
		}
	}

	@Then("brugerens totale tidsforbrug er opdateret")
	public void brugerens_totale_tidsforbrug_er_opdateret() {
	}
	
	@Given("bruger er tildelt aktivitet {string} under projektet {string}")
	public void bruger_er_tildelt_aktivitet_under_projektet(String string, String string2) {
		
	}

	@Given("brugeren {string} er projektleder")
	public void brugeren_er_projektleder(String initialer) {
		Bruger bruger = new Bruger(initialer);
		bruger.projektleder = true;
		app.oversigt.tilføjMedarbjeder(bruger);
		app.bruger = bruger;
		app.loggedind = true;

		assertTrue(app.oversigt.tjekMedarbejder(initialer));
		assertTrue(app.oversigt.fåMedarbejder(initialer).projektleder);
	}

	@Given("Projektet {string} eksisterer")
	public void aktiviteten_og_projektet_eksisterer(String projektnavn) {

		Projekt projekt = new Projekt(projektnavn, app.oversigt);
		app.oversigt.projekter.add(projekt);

		assertTrue(app.oversigt.tjekProjekt(projektnavn));
	}

	@Given("aktiviteten {string} horer under projektet {string}")
	public void aktiviteten_horer_under_projektet(String projektnavn,String aktivitetsnavn) {
		Aktivitet aktivitet = new Aktivitet(aktivitetsnavn); 
		app.oversigt.faProjekt(projektnavn).tilfojAktivitet(aktivitet);

		assertTrue(app.oversigt.faProjekt(projektnavn).tjekAktivitet(aktivitetsnavn));
	}

	@When("brugeren sletter aktiviteten {string} fra projektet {string}")
	public void brugeren_sletter_aktiviteten(String aktivitet, String projektnavn) {
		app.oversigt.faProjekt(projektnavn).fjernAktivitet(app.oversigt.faProjekt(projektnavn).fåAktivitet(aktivitet));
	}

	@Then("aktiviteten {string} fjernes fra projektet {string}")
	public void aktiviteten_fjernes_fra_projektet(String aktivitet, String projektnavn) {
		assertFalse(app.oversigt.faProjekt(projektnavn).tjekAktivitet(aktivitet));
	}

	@Given("Projektet {string} er tomt")
	public void projektet_er_tomt(String projektnavn) {
		assertTrue(app.oversigt.faProjekt(projektnavn).aktivitetsliste.size() == 0);
	}

	@When("brugeren vælger projektet {string}")
	public void brugeren_vælger_projektet(String navn) {
		app.oversigt.faProjekt(navn).printAktiviteter();
		app.fejlbesked = app.oversigt.faProjekt(navn).fejl;
	}

	@Given("aktivitet {string} eksisterer under projektet {string}")
	public void aktivitet_eksisterer(String aktivitet, String projekt) {
		app.oversigt.faProjekt(projekt).tilfojAktivitet(new Aktivitet(aktivitet));
		assertTrue(app.oversigt.faProjekt(projekt).tjekAktivitet(aktivitet));
	}

	@When("brugeren {string} sætter start til {int} og slut til {int} for aktiviteten {string} under projektet {string}")
	public void brugeren_sætter_start_til_og_slut_til(String bruger, Integer startdato, Integer slutdato, String aktivitet, String projekt) {
		
		app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start.set(Calendar.DAY_OF_MONTH, startdato/1000000);
		app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start.set(Calendar.MONTH, (startdato/10000)%100);
		app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start.set(Calendar.YEAR, startdato%10000);

		app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).slut.set(Calendar.DAY_OF_MONTH, slutdato/1000000);
		app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).slut.set(Calendar.MONTH,(slutdato/10000)%100);
		app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).slut.set(Calendar.YEAR, slutdato%10000);
		
		if (app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).slut.before(app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start)) {
			
			app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start = null;
			app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).slut = null;
			
			app.fejlbesked.satFejlbesked("Ugyldige datoer");
		}

	}

	@Then("start er {int} {int} {int} og slut {int} {int} {int} for aktiviteten {string} under projektet {string}")
	public void start_er_og_slut(int startD, int startM, int startY, int slutD, int slutM, int slutY, String aktivitet, String projekt) {


		assertEquals(app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start.get(Calendar.DAY_OF_MONTH), startD);

		if (app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start.get(Calendar.MONTH) == 0) {
			assertEquals(12, startM);
		}
		else {
			assertEquals(app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start.get(Calendar.MONTH), startM);
		}

		assertEquals(app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start.get(Calendar.YEAR), startY);
		
	}
}