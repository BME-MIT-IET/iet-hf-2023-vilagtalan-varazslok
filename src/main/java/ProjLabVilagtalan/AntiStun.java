package ProjLabVilagtalan;
public class AntiStun extends Vaccine{
	
	// AntiStun oszt�ly konstruktora
	public AntiStun() {
		aminoacidCost = 50;
		nucleotideCost = 50;
		duration = 10;
		timeLeft = 10;
		ttl = 3;
		virologist = null;
	}
	
	// Visszaadja, hogy a vakcina immunit�st biztos�t-e egy adott v�rus ellen
	// @param v - a t�mad� v�rus
	// @return - az eredm�ny
	public boolean counter(Virus v) {
		if(v.getClass()==Stun.class) {
			return true;
		}
		return false;
	}
	
	// L�trehoz egy �j, ugyanolyan t�pus� vakcin�t
	// @return - az �j vakcina
	public Agent create() {
		return new AntiStun();
	}

}
