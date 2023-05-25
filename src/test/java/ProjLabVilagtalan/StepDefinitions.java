package ProjLabVilagtalan;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;


public class StepDefinitions {

    private Field f1;
    private Field f2;
    private Virologist v1;

    @Given("There are two fields that are neighbours and a there is a virologist on the first field")
    public void two_field_and_virologist() {
        f1 = new Field();
        f2 = new Field();
        f1.addNeighbour(f2);
        f2.addNeighbour(f1);
        v1 = new Virologist();
        v1.move(f1);
    }
    @When("The virologist moves to the other field")
    public void the_virologist_moves() {
        v1.move(f2);
    }
    @Then("The virologist should appear on the other field")
    public void virologist_is_on_f2() {
        assertEquals(v1.getField(), f2);
        assertEquals(f2.getVirologists().get(0), v1);
    }
}
