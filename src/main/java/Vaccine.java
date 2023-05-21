package main.java;
public abstract class Vaccine extends Agent {

	// Visszaadja, hogy a vakcina immunit�st biztos�t-e egy adott v�rus ellen
	// @param v - a t�mad� v�rus
	// @return - az eredm�ny
	public abstract boolean counter(Virus v);
	
	// Id�ben l�pteti a vakcin�t
	public void step() {
		super.step();
		if(timeLeft > 0)
			timeLeft--;
		if(timeLeft==0) {
			virologist.loseVaccine(this);
		}
	}
	
	// L�trehoz egy �j, ugyanolyan t�pus� vakcin�t
	// @return - az �j v�rus
	public abstract Agent create();

}
