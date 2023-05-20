import java.util.ArrayList;
import java.util.Random;

public class Bear extends Virus {

	// Bear osztály konstruktora
	public Bear() {
		aminoacidCost = 50;
		nucleotideCost = 50;
		duration = -1;
		timeLeft = -1;
		ttl = -1;
		virologist = null;
	}
	
	// Létrehoz egy új, ugyanolyan típusú vírust
	// @return - az új vírus
	public Agent create() {
        Bear newb = new Bear();
        return newb;
    }
	
	// Visszaadja, hogy a vírus hatása alatt álló virológus cselekvõképes-e
    // @return - az eredmény
	public boolean canAct() {
		return false;
	}
	
	// Idõben lépteti a vírust, elõször terjeszti a medvefertõzést, majd véletlenszerûen lépteti a virológust, majd ismét terjeszti a fertõzést
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
	
	// A vírus hatást fejt ki, az áldozat elveszíti a rajta lévõ aktív vírusokat
	public void effect() {
		ArrayList<Virus> viruses = virologist.getActiveViruses();
		for(int i = 0; i < viruses.size(); i++) {
			if(viruses.get(i).getClass() != Bear.class) {
				virologist.loseVirus(viruses.get(i));
			}
		}
	}
}
