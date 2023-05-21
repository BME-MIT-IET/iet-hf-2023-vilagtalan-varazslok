
public class BearImmunity extends Vaccine{
	
	// AntiForget osztály konstruktora
	public BearImmunity() {
		aminoacidCost = 50;
		nucleotideCost = 50;
		duration = -1;
		timeLeft = -1;
		ttl = -1;
		virologist = null;
	}
	
	// Visszaadja, hogy a vakcina immunitást biztosít-e egy adott vírus ellen
	// @param v - a támadó vírus
	// @return - az eredmény
	public boolean counter(Virus v) {
		return true;
	}
	
	// Létrehoz egy új, ugyanolyan típusú vakcinát
	// @return - az új vakcina
	public Agent create() {
		BearImmunity newbie = new BearImmunity();
		return newbie;
	}
}
