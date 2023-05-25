package ProjLabVilagtalan;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Array;
import java.util.ArrayList;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;

public class PosaStepDefinitions {

    private Laboratory l1;
    private Chorea c1;
    private AntiChorea ac1;
    private Chorea c2;
    private AntiChorea ac2;
    private Virologist v1;

    private Field f1;
    private Virologist v2;
    private Backpack b1;
    private Backpack b2;
    private Stun s1;
    private Cape cap1;

    private Stun s2;

    @Given("there is a laboratory with chorea and antichorea in it, and a virologist, who already knows chorea and antichorea")
    public void learnSetup() {
        l1 = new Laboratory();
        c1 = new Chorea();
        c2 = new Chorea();
        ac1 = new AntiChorea();
        ac2 = new AntiChorea();

        v1 = new Virologist();

        ArrayList<Agent> arr = new ArrayList<>();
        arr.add(c1);
        arr.add(ac1);
        v1.addKnownAgents(arr);

        ArrayList<Agent> arrr = new ArrayList<>();
        arr.add(c2);
        arr.add(ac2);
        l1.addAgents(arrr);
    }
    @When("the virologist moves to the laboratory")
    public void learnAction() {
        v1.move(l1);
        v1.pickUp();
    }
    @Then("the virologist's learned viruses shouldn't change")
    public void learnTest() {
        assertEquals(2, v1.getAgentsKnown().size());
        assertEquals(v1.getField(), l1);
        assertEquals(c1, v1.getAgentsKnown().get(0));
        assertEquals(ac1, v1.getAgentsKnown().get(1));
    }


    @Given("there are two virologists, one who is stunned with a backpack and a cape, and an other with a backpack")
    public void stealSetup() {
        f1 = new Field();
        v1 = new Virologist();
        v2 = new Virologist();
        b1 = new Backpack();
        b2 = new Backpack();
        cap1 = new Cape();
        s1 = new Stun();
        v1.move(f1);
        v2.move(f1);
        
        v1.gotInfected(null, s1);
        v1.addItem(cap1);
        v1.addItem(b1);
        v2.addItem(b2);
    }
    @When("the virologist steals from the stunned one")
    public void stealAction() {
        v2.steal(v1);
    }
    @Then("the virologist gains the other's materials and cape, but not the backpack, since they already have one")
    public void stealTest() {
        assertEquals(2, v2.getItems().size());
        assertEquals(Backpack.class, v2.getItems().get(0).getClass());
        assertEquals(Cape.class, v2.getItems().get(1).getClass());
        assertEquals(100, v2.getAminoacid());
        assertEquals(100, v2.getNucleotide());

        assertEquals(1, v1.getItems().size());
        assertEquals(Backpack.class, v1.getItems().get(0).getClass());
        assertEquals(0, v1.getAminoacid());
        assertEquals(0, v1.getNucleotide());
    }


    @Given("there are two virologists, one has a cape and both are stunned")
    public void stealFailSetup() {
        f1 = new Field();
        v1 = new Virologist();
        v2 = new Virologist();
        cap1 = new Cape();
        s1 = new Stun();
        s2 = new Stun();
        v1.move(f1);
        v2.move(f1);
        
        v1.gotInfected(null, s1);
        v2.gotInfected(null, s2);
        v1.addItem(cap1);
    }
    @When("the capeless virologist tries to steal from the other")
    public void stealFailAction() {
        v2.steal(v1);
    }
    @Then("nothing happens")
    public void stealFailTest() {
        assertEquals(0, v2.getItems().size());
        assertEquals(50, v2.getAminoacid());
        assertEquals(50, v2.getNucleotide());

        assertEquals(1, v1.getItems().size());
        assertEquals(Cape.class, v1.getItems().get(0).getClass());
        assertEquals(50, v1.getAminoacid());
        assertEquals(50, v1.getNucleotide());
    }


}
