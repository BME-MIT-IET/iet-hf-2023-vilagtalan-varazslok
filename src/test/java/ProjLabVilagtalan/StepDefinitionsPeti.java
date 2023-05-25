package ProjLabVilagtalan;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;



public class StepDefinitionsPeti {

    private Field f1;
    private Field f2;
    private Virologist v1;
    private Stun s;
    private Backpack b1,b2;
    private Shelter sh;

    //T1
    @Given("There are two fields that are neighbours and a there is a virologist on the first field and a stun potion")
    public void two_field_and_virologist_and_stun() {
        f1 = new Field();
        f2 = new Field();
        f1.addNeighbour(f2);
        f2.addNeighbour(f1);
        v1 = new Virologist();
        s=new Stun();
        v1.move(f1);
        v1.gotInfected(null, s);
    }
    @When("The virologist tries to move to the other field")
    public void the_virologist_moves2() {
        v1.move(f2);
    }
    @Then("The virologist cannot move.")
    public void virologist_is_on_f1() {
        assertEquals(f1,v1.getField());
        assertEquals(v1,f1.getVirologists().get(0));
    }

    //T2
    @Given("The virologist is on a shelter.")
    public void virologist_is_on_shelter() {
        f1 = new Field();
        sh=new Shelter();
        f1.addNeighbour(sh);
        sh.addNeighbour(f1);
        v1 = new Virologist();
        b1=new Backpack();
        
    }
    @When("The virologist picks up the backpack")
    public void the_virologist_pickUp_bagpack() {
        sh.addItem(b1);
        v1.move(sh);
        v1.addItem(b1);
    }
    @Then("The virologist succesfully picks up the backpack")
    public void virologist_pcikup_backpack() {
        assertEquals(v1.getField(), sh);
        assertEquals(v1.getItems().get(0), b1);
    }

    //T3
    @Given("The virologist has a cape and is on a shelter")
    public void virlogost_with_cape_on_Shelter() {
        f1 = new Field();
        sh=new Shelter();
        f1.addNeighbour(sh);
        sh.addNeighbour(f1);
        v1 = new Virologist();
        b1=new Backpack();
        b2=new Backpack();
       
    }
    @When("The virologist tries to pick up another cape")
    public void the_virologist_pickup_another_cape() {
        sh.addItem(b2);
        v1.addItem(b1);
        v1.move(sh);
        v1.addItem(b2);
    }
    @Then("The virologist cannot pick up")
    public void virologist_cannot_pickup() {
        assertEquals(v1.getItems().get(0), b1);
        assertEquals(v1.getItems().size(),1);
    }

}

