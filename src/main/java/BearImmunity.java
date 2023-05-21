package main.java;
public class BearImmunity extends Vaccine{
	
	// AntiForget oszt�ly konstruktora
	public BearImmunity() {
		aminoacidCost = 50;
		nucleotideCost = 50;
		duration = -1;
		timeLeft = -1;
		ttl = -1;
		virologist = null;
	}
	
	// Visszaadja, hogy a vakcina immunit�st biztos�t-e egy adott v�rus ellen
	// @param v - a t�mad� v�rus
	// @return - az eredm�ny
	public boolean counter(Virus v) {
		return true;
	}
	
	// L�trehoz egy �j, ugyanolyan t�pus� vakcin�t
	// @return - az �j vakcina
	public Agent create() {
		BearImmunity newbie = new BearImmunity();
		return newbie;
	}
}
