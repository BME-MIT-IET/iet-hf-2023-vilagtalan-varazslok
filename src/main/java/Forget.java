package main.java;
import java.util.ArrayList;

public class Forget extends Virus{

	// Forget oszt�ly konstruktora
	public Forget() {
		aminoacidCost = 50;
		nucleotideCost = 50;
		duration = 1;
		timeLeft = 1;
		ttl = 3;
		virologist = null;
	}
	
	// L�trehoz egy �j, ugyanolyan t�pus� v�rust
	// @return - az �j v�rus
	public Agent create() {
		return new Forget();
	}
	
	// A v�rus hat�st fejt ki, az �ldozat elfelejti az ismert �genseit
	public void effect() {
		virologist.setAgentsKnown(new ArrayList<Agent>());
	}
	
}
