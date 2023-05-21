package main.java;
import java.util.ArrayList;
import java.util.Random;



public class Chorea extends Virus {
	private static Random rand=new Random();

	// Chorea oszt�ly konstruktora
	public Chorea() {
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
		return new Chorea();
	}
	
	// Visszaadja, hogy a v�rus hat�sa alatt �ll� virol�gus cselekv�k�pes-e
    // @return - az eredm�ny
	public boolean canAct() {
		return false;
	}
	
	// Id�ben l�pteti a v�rust, v�letlenszer�en l�pteti a hat�sa alatt �ll� virol�gust
	public void step() {
		super.step();
		if(virologist.getActiveViruses().contains(this)) {
			Field f = virologist.getField();
			ArrayList<Field> neighbours = f.getNeighbours();
			if(neighbours.size()!=0) {
				f.removeVirologist(virologist);
				int r= rand.nextInt(neighbours.size());
				Field newf = neighbours.get(r);
		        newf.addVirologist(virologist);
		        newf.movedOn(virologist);
		        virologist.setField(newf);
			}
		}
	}

}
