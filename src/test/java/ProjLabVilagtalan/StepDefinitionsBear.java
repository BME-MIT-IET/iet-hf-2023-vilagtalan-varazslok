package ProjLabVilagtalan;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;



public class StepDefinitionsBear {

    
    private Virologist v1, v2;
    private Laboratory l;
    private Forget f;
    private Bear b;
    private Field f1;
    private Field f2;

    @Given("there is a laboratory with bear and a virologist")
    public void lab_and_virologist() {
        l=new Laboratory();
        b=new Bear();
        ArrayList<Agent> blist=new ArrayList<Agent>();
        blist.add(b);
        l.addAgents(blist);
        v1 = new Virologist();
    }
    @Given("the virologist has a Forget")
    public void has_Chorea() {
        f=new Forget();
        v1.gotInfected(v1, f);
    }
    @When("the virologist moves to the laboratoty")
    public void the_virologist_moves() {
        v1.move(l);
    }
    @Then("the virologist should have one virus")
    public void virologist_has_one_virus() {
        assertEquals(1, v1.getActiveViruses().size());
    
    }
    @Then("the virus should be bear")
    public void virus_is_bear() {
        assertEquals(Bear.class, v1.getActiveViruses().get(0).getClass());    
    }

    @Given("There are two fields that are neighbours and a there is virologist on the first field")
    public void two_field_and_virologist() {
        f1 = new Field();
        f2 = new Field();
        f1.addNeighbour(f2);
        f2.addNeighbour(f1);
        v1 = new Virologist();
        v1.move(f1);
    }

    @Given("the virologist has a Bear")
    public void has_Bear() {
        b=new Bear();
        Timer.instance().addSteppable(b);
        v1.gotInfected(v1, b);
    }

    @Given("there is another virologist on the second field")
    public void second_virologist() {        
        v2 = new Virologist();
        v2.move(f2);
    }
    @When("the timer ticks")
    public void timer_ticks() {
        Timer.instance().tick();
    }
    @Then("the other virologist should be bear")
    public void virologist_is_bear() {
        assertEquals(Bear.class, v2.getActiveViruses().get(0).getClass());    
    }

    @Given("there is an axe with him")
    public void thas_axe() {
        Axe a=new Axe();
        v2.addItem(a);           
        
    }
    @When("the second virologist moves to the other field")
    public void second_virologist_moves() {
        v2.move(f1);
    }
    @Then("the bear should die")
    public void bear_is_dead() {
        assertEquals(1, f1.getVirologists().size());
        assertEquals(v2, f1.getVirologists().get(0));
    }
    



}