
public abstract class Vaccine extends Agent {

	// Visszaadja, hogy a vakcina immunitást biztosít-e egy adott vírus ellen
	// @param v - a támadó vírus
	// @return - az eredmény
	public abstract boolean counter(Virus v);
	
	// Idõben lépteti a vakcinát
	public void step() {
		super.step();
		if(timeLeft > 0)
			timeLeft--;
		if(timeLeft==0) {
			virologist.loseVaccine(this);
		}
	}
	
	// Létrehoz egy új, ugyanolyan típusú vakcinát
	// @return - az új vírus
	public abstract Agent create();

}
