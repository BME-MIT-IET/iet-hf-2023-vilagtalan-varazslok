package ProjLabVilagtalan;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import io.cucumber.java.en.Given;

public class StepDefMotyo {

    private Field f1;
    private Field f2;
    private Virologist v1;
    private Virologist v2;
    private Chorea c;
    private Gloves g;
    private Gloves g2;
    private AntiChorea ac;
    private AntiStun as;

    @Given("There is a field with two virologist, the first knows chorea and created one, the second has gloves")
    public void field_two_virologists_first_knows_chorea_second_has_gloves() {
        f1 = new Field();
        v1 = new Virologist();
        v2 = new Virologist();
        v1.move(f1);
        v2.move(f1);
        c = new Chorea();
        ArrayList<Agent> agents = new ArrayList<Agent>();
        agents.add(c);
        v1.addKnownAgents(agents);
        v1.createAgent(c);
        Gloves g = new Gloves();
        v2.addItem(g);        
    }
    @When("The virologist uses the chorea virus on the other")
    public void virologist_uses_chorea() {
        v1.useVirus(v2, c);
    }
    @Then("The first virologist should get the chorea")
    public void first_virologist_is_infected() {
        assertEquals(c, v1.getActiveViruses().get(0));
        assertEquals(v1, c.getVirologist());
    }




    @Given("There is a field with two virologist, the first knows chorea and created one, both have gloves")
    public void field_two_virologists_first_knows_chorea_both_have_gloves() {
        f1 = new Field();
        v1 = new Virologist();
        v2 = new Virologist();
        v1.move(f1);
        v2.move(f1);
        c = new Chorea();
        ArrayList<Agent> agents = new ArrayList<Agent>();
        agents.add(c);
        v1.addKnownAgents(agents);
        v1.createAgent(c);
        g = new Gloves();
        v2.addItem(g);        
        g2 = new Gloves();
        v1.addItem(g2);
    }
    @Then("Noone should have chorea")
    public void noone_has_chorea() {
        assertEquals(0, v1.getActiveViruses().size());
        assertEquals(0, v2.getActiveViruses().size());
        assertEquals(null, c.getVirologist());
    }



    @Given("There is a field with two virologist, the first knows chorea and created one, the second has gloves with one durability")
    public void field_two_virologists_first_knows_chorea_second_has_gloves_one_durability() {
        f1 = new Field();
        v1 = new Virologist();
        v2 = new Virologist();
        v1.move(f1);
        v2.move(f1);
        c = new Chorea();
        ArrayList<Agent> agents = new ArrayList<Agent>();
        agents.add(c);
        v1.addKnownAgents(agents);
        v1.createAgent(c);
        g = new Gloves();
        v2.addItem(g);

        Chorea c2 = new Chorea();
        Chorea c3 = new Chorea();
        v2.gotInfected(null, c2);
        v2.gotInfected(null, c3);
    }
    @Then("First virologist should be infected by chorea, second virologist should not have gloves")
    public void v1_has_chorea_gloves_is_broken() {
        assertEquals(c, v1.getActiveViruses().get(0));
        assertEquals(0, v2.getActiveViruses().size());
        assertEquals(0, v2.getItems().size());
    }



    @Given("There is a field with two virologist, the first has an antichorea")
    public void field_two_virologists_first_has_an_antichorea() {
        f1 = new Field();
        v1 = new Virologist();
        v2 = new Virologist();
        v1.move(f1);
        v2.move(f1);
        ac = new AntiChorea();
        ArrayList<Agent> agents = new ArrayList<Agent>();
        agents.add(ac);
        v1.addKnownAgents(agents);
        v1.createAgent(ac);
    }
    @When("The virologist uses the antichorea vaccine on the other")
    public void virologist_uses_antichorea() {
        v1.useVaccine(v2, ac);
    }
    @Then("The second virologist should be antichorea vaccinated")
    public void second_virologist_is_antichorea_vaccinated() {
        assertEquals(ac, v2.getActiveVaccines().get(0));
        assertEquals(v2, ac.getVirologist());
    }



    @Given("There are two fields that are neighbours and a there is a virologist on the first field infected by chorea")
    public void two_fields_virologist_infection_chorea() {
        f1 = new Field();
        f2 = new Field();
        f1.addNeighbour(f2);
        f2.addNeighbour(f1);
        v1 = new Virologist();
        v1.move(f1);
        c = new Chorea();
        v1.gotInfected(null, c);
        Timer.instance().addSteppable(c);
    }
    @When("Timer ticks")
    public void timer_ticks() {
        Timer.instance().tick();
    }
    @Then("The virologist with chorea should appear on the other field")
    public void virologist_is_on_f2() {
        assertEquals(v1.getField(), f2);
        assertEquals(f2.getVirologists().get(0), v1);
    }



    @Given("There is a virologist who has a created antistun")
    public void virologist_with_created_antistun() {
        v1 = new Virologist();
        as = new AntiStun();
        ArrayList<Agent> agents = new ArrayList<Agent>();
        agents.add(as);
        v1.addKnownAgents(agents);
        v1.createAgent(as);
    }
    @When("The timer ticks 3 times")
    public void timer_ticks_3() {
        Timer.instance().tick();
        Timer.instance().tick();
        Timer.instance().tick();
    }
    @Then("The antistun agent should be expired")
    public void antistun_expired() {
        assertEquals(0, v1.getAgentsCreated().size());
    }
}