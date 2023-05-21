import java.util.ArrayList;

public class Forget extends Virus{

	// Forget osztály konstruktora
	public Forget() {
		aminoacidCost = 50;
		nucleotideCost = 50;
		duration = 1;
		timeLeft = 1;
		ttl = 3;
		virologist = null;
	}
	
	// Létrehoz egy új, ugyanolyan típusú vírust
	// @return - az új vírus
	public Agent create() {
		return new Forget();
	}
	
	// A vírus hatást fejt ki, az áldozat elfelejti az ismert ágenseit
	public void effect() {
		virologist.setAgentsKnown(new ArrayList<Agent>());
	}
	
}
