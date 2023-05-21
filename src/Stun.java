
public class Stun extends Virus{

	// Stun osztály konstruktora
	public Stun() {
		aminoacidCost = 50;
		nucleotideCost = 50;
		duration = 10;
		timeLeft = 10;
		ttl = 3;
		virologist = null;
	}
	
	// Létrehoz egy új, ugyanolyan típusú vírust
	// @return - az új vírus
	public Agent create() {
		return new Stun();
	}

	// Visszaadja, hogy a vírus hatása alatt álló virológustól lehet-e lopni
    // @return - az eredmény
	public boolean stealable() {
		return true;
	}
	
	// Visszaadja, hogy a vírus hatása alatt álló virológus cselekvõképes-e
    // @return - az eredmény
	public boolean canAct() {
		return false;
	}
}
