
public class AntiChorea extends Vaccine{

	// AntiChorea osztály konstruktora
	public AntiChorea() {
		aminoacidCost = 50;
		nucleotideCost = 50;
		duration = 10;
		timeLeft = 10;
		ttl = 3;
		virologist = null;
	}
	
	// Visszaadja, hogy a vakcina immunitást biztosít-e egy adott vírus ellen
	// @param v - a támadó vírus
	// @return - az eredmény
	public boolean counter(Virus v) {
		if(v.getClass()==Chorea.class) {
			return true;
		}
		return false;
	}
	
	// Létrehoz egy új, ugyanolyan típusú vakcinát
	// @return - az új vakcina
	public Agent create() {
		return new AntiChorea();
	}

}
