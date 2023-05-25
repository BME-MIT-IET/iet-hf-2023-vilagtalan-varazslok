package ProjLabVilagtalan;
public class Stun extends Virus{

	// Stun oszt�ly konstruktora
	public Stun() {
		aminoacidCost = 50;
		nucleotideCost = 50;
		duration = 10;
		timeLeft = 10;
		ttl = 3;
		virologist = null;
	}
	
	// L�trehoz egy �j, ugyanolyan t�pus� v�rust
	// @return - az �j v�rus
	public Agent create() {
		return new Stun();
	}

	// Visszaadja, hogy a v�rus hat�sa alatt �ll� virol�gust�l lehet-e lopni
    // @return - az eredm�ny
	public boolean stealable() {
		return true;
	}
	
	// Visszaadja, hogy a v�rus hat�sa alatt �ll� virol�gus cselekv�k�pes-e
    // @return - az eredm�ny
	public boolean canAct() {
		return false;
	}
}
