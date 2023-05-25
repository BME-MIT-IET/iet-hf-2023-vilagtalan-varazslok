package ProjLabVilagtalan;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import io.cucumber.java.en.Given;


public class StepDefinitionsFischer {

    private Field f1;
    private Virologist v1;
    private Virologist v2;
    private Forget F;
    private Stun S1;
    private Chorea C1;
    private AntiChorea AC1;
    private AntiStun AS1;
    private  ArrayList<Agent> V1List;
    private ArrayList<Agent> V2List;
    private AntiForget AF;


    //Test 18
    @Given("There are two Virologists on the same field")
    public void two_virologists_on_the_same_field() {
        f1 = new Field();
        v1 = new Virologist();
        v2 = new Virologist();
        V1List = new ArrayList<>();
        V2List = new ArrayList<>();
        f1.addVirologist(v1);
        f1.addVirologist(v2);
        F = new Forget();
        V1List.add(F);
        v1.setAgentsKnown(V1List);
        S1 = new Stun();
        AS1 = new AntiStun();
        V2List.add(AS1);
        V2List.add(S1);
        v2.setAgentsKnown(V2List);
    }
    @When("One of the Virologists casts Forget virus on the other")
    public void the_virologist1_uses_Forget() {
        v1.useVirus(v2, F);
    }
    @Then("The other virologist recieves the effect of the Forget virus")
    public void virologist2_forgets_the_agents() {
        assertEquals(0, v2.getAgentsKnown().size());
    }

    //Test 19
    @Given("There are two Virologists on the same field with Forget and AntiForget")
    public void two_virologists_on_the_same_field_with_Forget_and_AntiForget() {
        f1 = new Field();
        v1 = new Virologist();
        v2 = new Virologist();
        V1List = new ArrayList<>();
        V2List = new ArrayList<>();
        f1.addVirologist(v1);
        f1.addVirologist(v2);
        F = new Forget();
        AF = new AntiForget();
        V1List.add(F);
        v1.setAgentsKnown(V1List);
        S1 = new Stun();
        AS1 = new AntiStun();
        V2List.add(AS1);
        V2List.add(S1);
        V2List.add(AF);
        v2.setAgentsKnown(V2List);
        v2.useVaccine(v2, AF);
    }

    @Then("The other virologist does not recieve the effect of the Forget virus")
    public void virologist2_does_not_forget_the_agents() {
        assertEquals(3, v2.getAgentsKnown().size());
    }

    //Test 20
    @Given("There are two Virologists on the same field with Stun and AntiStun")
    public void two_virologists_on_the_same_field_with_Stun_and_AntiStun() {
        f1 = new Field();
        v1 = new Virologist();
        v2 = new Virologist();
        V1List = new ArrayList<>();
        V2List = new ArrayList<>();
        f1.addVirologist(v1);
        f1.addVirologist(v2);
        S1 = new Stun();
        V1List.add(S1);
        v1.setAgentsKnown(V1List);
        AS1 = new AntiStun();
        V2List.add(AS1);
        v2.setAgentsKnown(V2List);
        v2.useVaccine(v2, AS1);
        
    }
    @When("One of the Virologists casts Stun virus on the other")
    public void the_virologist1_uses_Stun() {
        v1.useVirus(v2, S1);
    }
    @Then("The other virologist does not recieve the effect of the Stun virus")
    public void virologist2_does_not_get_stunned() {
        assertEquals(0, v2.getActiveViruses().size());
    }

    //Test 21
    @Given("There are two Virologists on the same field with Forget and AntiChorea")
    public void two_virologists_on_the_same_field_with_Chorea_and_AntiChorea() {
        f1 = new Field();
        v1 = new Virologist();
        v2 = new Virologist();
        V1List = new ArrayList<>();
        V2List = new ArrayList<>();
        f1.addVirologist(v1);
        f1.addVirologist(v2);
        C1 = new Chorea();
        AC1 = new AntiChorea();
        V1List.add(C1);
        v1.setAgentsKnown(V1List);
        V2List.add(AC1);
        v2.setAgentsKnown(V2List);
        v2.useVaccine(v2, AC1);
        
    }
    @When("One of the Virologists casts Chorea virus on the other")
    public void the_virologist1_uses_Chorea() {
        v1.useVirus(v2, C1);
    }
    @Then("The other virologist does not recieve the effect of the Chorea virus")
    public void virologist2_is_not_effected_by_Chrorea() {
        assertEquals(0, v2.getActiveViruses().size());
    }
}
