
public abstract class Virus extends Agent {

	// A vírus hatást fejt ki
	public void effect() {}
	
	// Visszaadja, hogy a vírus hatása alatt álló virológustól lehet-e lopni
    // @return - az eredmény
	public boolean stealable() {
		return false;
	}
	
	// Visszaadja, hogy a vírus hatása alatt álló virológus cselekvõképes-e
    // @return - az eredmény
	public boolean canAct() {
		return true;
	}
	
	// Idõben lépteti a vírust
	public void step() {
		super.step();
		if(timeLeft > 0)
			timeLeft--;
		if(timeLeft==0) {
			virologist.loseVirus(this);
		}
	}

	// Létrehoz egy új, ugyanolyan típusú vírust
	// @return - az új vírus
	public abstract Agent create();

}
