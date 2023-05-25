package ProjLabVilagtalan;
public abstract class Virus extends Agent {

	// A v�rus hat�st fejt ki
	public void effect() {}
	
	// Visszaadja, hogy a v�rus hat�sa alatt �ll� virol�gust�l lehet-e lopni
    // @return - az eredm�ny
	public boolean stealable() {
		return false;
	}
	
	// Visszaadja, hogy a v�rus hat�sa alatt �ll� virol�gus cselekv�k�pes-e
    // @return - az eredm�ny
	public boolean canAct() {
		return true;
	}
	
	// Id�ben l�pteti a v�rust
	public void step() {
		super.step();
		if(timeLeft > 0)
			timeLeft--;
		if(timeLeft==0) {
			virologist.loseVirus(this);
		}
	}

	// L�trehoz egy �j, ugyanolyan t�pus� v�rust
	// @return - az �j v�rus
	public abstract Agent create();

}
