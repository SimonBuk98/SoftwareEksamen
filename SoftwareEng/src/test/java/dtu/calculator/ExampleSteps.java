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
		app.oversigt.brugere.add(new Bruger(string));

	}

	@Then("brugeren med initialerne {string} findes")
	public void brugeren_med_initialerne_findes(String string) {
		assertTrue(app.oversigt.tjekMedarbejder(string));
	}

	@Given("der eksisterer en bruger med intialerne {string}")
	public void der_eksisterer_en_bruger_med_intialerne(String string) {
		assertTrue(app.oversigt.tjekMedarbejder(string));
	}

	@When("bruger opretter en bruger med initialerne {string}")
	public void bruger_opretter_en_bruger_med_initialerne(String initialer) {
		if (!app.oversigt.tjekMedarbejder((initialer))) {
			app.oversigt.brugere.add(new Bruger(initialer));
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
		assertTrue(app.oversigt.tjekProjekt(projekt));
	}

	@Then("et projekt med navnet {string} og et projektnummer eksisterer")
	public void et_projekt_med_navnet_og_et_projektnummer_eksisterer(String projekt) {
		assertTrue(app.oversigt.tjekProjekt(projekt));
	}

	@Given("bruger er ikke logget ind")
	public void bruger_er_ikke_logget_ind() {
		assertFalse(!app.loggedind);
		app.fejlbesked.satFejlbesked("Bruger skal være logget ind");
		app.fejlbesked.faFejlbesked();
	}

	@Given("en bruger med initialerne {string} findes")
	public void en_bruger_med_initialerne_abc_findes(String navn) {
		Bruger bruger = new Bruger(navn);
		Oversigt.brugere.add(bruger);
		assertTrue(app.oversigt.tjekMedarbejder(navn));
	}

	@When("brugeren tilføjer brugeren {string} som projektleder")
	public void admin_tilføjer_brugeren_abc_som_projektleder(String navn) {
		Projekt.projektleder = app.oversigt.fåMedarbejder(navn);
	}

	@Then("en projektleder med initialerne {string} findes")
	public void en_projektleder_med_initialerne_abc_findes(String navn) {
		assertTrue(app.oversigt.faProjekt(navn).projektleder == app.oversigt.fåMedarbejder(navn));
	}

	@Then("en projektleder er oprettet")
	public void en_projektleder_er_oprettet() {
		if (app.oversigt.tjekMedarbejder("NEJ") == false) {
			app.fejlbesked.satFejlbesked("Brugeren skal være oprettet i systemet");
			app.fejlbesked.faFejlbesked();
		} else {
			Projekt.projektleder = app.oversigt.fåMedarbejder("NEJ");
		}

	}

	@Then("en fejlbesked {string} er givet")
	public void en_fejlbesked_er_givet(String string) {

	}

	@Given("at brugeren {string} er projektleder")
	public void at_brugeren_projektleder(String string) {
		Projekt.projektleder = app.oversigt.fåMedarbejder(string);
	}

	@Given("projektet {string} eksisterer")
	public void projektet_eksisterer(String navn) {
		app.oversigt.projekter.add(new Projekt(navn, app.oversigt));

		assertTrue(app.oversigt.tjekProjekt(navn));
	}

	@Given("medarbejderen {string} eksisterer")
	public void medarbejderen_eksisterer(String string) {
		app.oversigt.brugere.add(new Bruger(string));
		assertTrue(app.oversigt.tjekMedarbejder(string));
	}

	@Given("medarbejderen {string} ikke er en del af projektet {string}")
	public void medarbejderen_ikke_er_en_del_af_projektet(String string, String string2) {
		assertFalse(app.oversigt.faProjekt(string2).tjekForMedarbejder(string));
	}

	@When("brugeren tilføjer medarbejderen {string} til projektet {string}")
	public void brugeren_tilføjer_medarbejderen_til_projektet(String string, String string2) {
		app.oversigt.faProjekt(string2).tilfojmedarbejder(app.oversigt.fåMedarbejder(string));
	}

	@Then("brugeren {string} er tilføjet til projektet {string}")
	public void brugeren_er_tilføjet_til_projektet(String string, String string2) {
		assertTrue(app.oversigt.faProjekt(string2).tjekForMedarbejder(string));
	}

	@Given("at brugeren {string} er eksisterer")
	public void at_brugeren_er_eksisterer(String string) {
		assertTrue(app.oversigt.tjekMedarbejder(string));
	}

	@Given("medarbejderen {string} er en del af projektet {string}")
	public void medarbejderen_er_en_del_af_projektet(String string, String string2) {
		assertTrue(app.oversigt.faProjekt(string2).tjekForMedarbejder(string));
	}

	@Given("projektet {string} ikke eksisterer")
	public void projektet_ikke_eksisterer(String string) {
		if (app.oversigt.tjekProjekt(string) == false)
			app.fejlbesked.satFejlbesked("Brugeren skal være oprettet i systemet");
		app.fejlbesked.faFejlbesked();

	}

	@Then("fås fejlmeddelelsen {string}")
	public void fås_fejlmeddelelsen(String string) {

	}

	@Given("aktiviteten {string} eksisterer i projektet {string}")
	public void aktiviteten_eksisterer_i_projektet(String aktivitet, String projekt) {
		app.oversigt.faProjekt(projekt).tilfojAktivitet(new Aktivitet(aktivitet));
		assertTrue(app.oversigt.faProjekt(projekt).tjekAktivitet(aktivitet));
	}

	@Given("medarbejderen {string} er del af projektet {string}")
	public void medarbejderen_er_del_af_projektet(String navn, String projekt) {
		app.oversigt.faProjekt(projekt).tilfojmedarbejder(app.oversigt.fåMedarbejder(navn));
		assertTrue(app.oversigt.faProjekt(projekt).tjekForMedarbejder(navn));
	}

	@When("brugeren tilføjer medarbejderen {string} til aktiviteten {string} under projektet {string}")
	public void brugeren_tilføjer_medarbejderen_til_aktiviteten_under_projektet(String navn, String aktivitet,
			String projekt) {
		app.oversigt.fåMedarbejder(navn).tilfojAktivitet(app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet),
				app.oversigt.fåMedarbejder(navn));
	}

	@Then("brugeren {string} er en del af aktiviteten {string}")
	public void brugeren_er_en_del_af_aktiviteten(String navn, String aktivitet) {
		assertTrue(app.oversigt.fåMedarbejder(navn).tjekAktivitet(aktivitet));
	}
	
	@Given("brugeren {string} er ikke del af projektet {string}")
	public void brugeren_er_ikke_del_af_projektet(String navn, String projekt) {
		assertFalse(app.oversigt.faProjekt(projekt).tjekForMedarbejder(navn));
	}

	@When("projektlederen tilføjer {string} til aktiviteten {string} under projektet {string}")
	public void projektlederen_tilføjer_til_aktiviteten_under_projektet(String navn, String aktivitet, String projekt) {
		if (app.oversigt.faProjekt(projekt).tjekForMedarbejder(navn) == false) {
			app.fejlbesked.satFejlbesked("Bruger er ikke tildelt projekt");
			app.fejlbesked.faFejlbesked();
		} else if (app.oversigt.faProjekt(projekt).tjekForMedarbejder(navn) == true) {
			app.oversigt.fåMedarbejder(navn).tilfojAktivitet(app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet), app.oversigt.fåMedarbejder(navn));
		}
	}
	
	@When("bruger {string} registrerer tid arbejdet på aktiviteten {string} under projektet {string}")
	public void bruger_registrerer_tid_arbejdet_på_aktiviteten_under_projektet(String bruger, String aktivitet,
			String projekt) {
		if (app.oversigt.fåMedarbejder(bruger).tjekAktivitet(aktivitet) == true) {
			assertTrue(app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).satTid(app.oversigt.fåMedarbejder(bruger),
					1));
		} else {
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
		app.oversigt.tilføjMedarbjeder(bruger);
		app.oversigt.fåMedarbejder(initialer).projektleder = true;
		app.loggedind = true;
		app.bruger = bruger;

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
	public void aktiviteten_horer_under_projektet(String projektnavn, String aktivitetsnavn) {
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
	public void brugeren_sætter_start_til_og_slut_til(String bruger, Integer startdato, Integer slutdato,
			String aktivitet, String projekt) {

		app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start.set(Calendar.DAY_OF_MONTH, startdato / 1000000);
		app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start.set(Calendar.MONTH, (startdato / 10000) % 100);
		app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start.set(Calendar.YEAR, startdato % 10000);

		app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).slut.set(Calendar.DAY_OF_MONTH, slutdato / 1000000);
		app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).slut.set(Calendar.MONTH, (slutdato / 10000) % 100);
		app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).slut.set(Calendar.YEAR, slutdato % 10000);

		if (app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).slut
				.before(app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start)) {

			app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start = null;
			app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).slut = null;

			app.fejlbesked.satFejlbesked("Ugyldige datoer");
		}

	}

	@Then("start er {int} {int} {int} og slut {int} {int} {int} for aktiviteten {string} under projektet {string}")
	public void start_er_og_slut(int startD, int startM, int startY, int slutD, int slutM, int slutY, String aktivitet,
			String projekt) {

		assertEquals(app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start.get(Calendar.DAY_OF_MONTH), startD);

		if (app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start.get(Calendar.MONTH) == 0) {
			assertEquals(12, startM);
		} else {
			assertEquals(app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start.get(Calendar.MONTH), startM);
		}

		assertEquals(app.oversigt.faProjekt(projekt).fåAktivitet(aktivitet).start.get(Calendar.YEAR), startY);

	}
}