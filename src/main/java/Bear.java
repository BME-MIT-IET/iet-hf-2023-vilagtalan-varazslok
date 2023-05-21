
package main.java;
import java.util.ArrayList;
import java.util.Random;

public class Bear extends Virus {

	// Bear oszt�ly konstruktora
	public Bear() {
		aminoacidCost = 50;
		nucleotideCost = 50;
		duration = -1;
		timeLeft = -1;
		ttl = -1;
		virologist = null;
	}
	
	// L�trehoz egy �j, ugyanolyan t�pus� v�rust
	// @return - az �j v�rus
	public Agent create() {
        Bear newb = new Bear();
        return newb;
    }
	
	// Visszaadja, hogy a v�rus hat�sa alatt �ll� virol�gus cselekv�k�pes-e
    // @return - az eredm�ny
	public boolean canAct() {
		return false;
	}
	
	// Id�ben l�pteti a v�rust, el�sz�r terjeszti a medvefert�z�st, majd v�letlenszer�en l�pteti a virol�gust, majd ism�t terjeszti a fert�z�st
	public void step() {
			Field f = virologist.getField();
			
			ArrayList<Virologist> virologists = f.getVirologists();
			for(int i = 0; i < virologists.size(); i++) {
				if(virologists.get(i) != virologist) {
					Bear b = (Bear)this.create();
					BearImmunity bi=(BearImmunity)(new BearImmunity().create());
					if(virologists.get(i).gotInfected(virologist, b)) {
						Timer.instance().addSteppable(b);
						virologists.get(i).gotVaccinated(bi);
					}
				}
			}
			
			
			ArrayList<Field> neighbours = f.getNeighbours();
			
			
			
			if(neighbours.size()!=0) {
				f.removeVirologist(virologist);
				int r= new Random().nextInt(neighbours.size());
				Field newf = neighbours.get(r);
				newf.destroyed();
		        newf.addVirologist(virologist);
		        virologist.setField(newf);
		        virologists = newf.getVirologists();
			}
			
			for(int i = 0; i < virologists.size(); i++) {
				if(virologists.get(i) != virologist) {
					Bear b = (Bear)this.create();
					BearImmunity bi=(BearImmunity)(new BearImmunity().create());
					if(virologists.get(i).gotInfected(virologist, b)) {
						Timer.instance().addSteppable(b);
						virologists.get(i).gotVaccinated(bi);
					}
				}
			}
		
		
	}
	
	// A v�rus hat�st fejt ki, az �ldozat elvesz�ti a rajta l�v� akt�v v�rusokat
	public void effect() {
		ArrayList<Virus> viruses = virologist.getActiveViruses();
		for(int i = 0; i < viruses.size(); i++) {
			if(viruses.get(i).getClass() != Bear.class) {
				virologist.loseVirus(viruses.get(i));
			}
		}
	}
}
