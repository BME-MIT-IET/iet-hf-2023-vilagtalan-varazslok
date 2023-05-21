import java.util.ArrayList;
import java.util.Random;



public class Chorea extends Virus {

	// Chorea osztály konstruktora
	public Chorea() {
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
		return new Chorea();
	}
	
	// Visszaadja, hogy a vírus hatása alatt álló virológus cselekvõképes-e
    // @return - az eredmény
	public boolean canAct() {
		return false;
	}
	
	// Idõben lépteti a vírust, véletlenszerûen lépteti a hatása alatt álló virológust
	public void step() {
		super.step();
		if(virologist.getActiveViruses().contains(this)) {
			Field f = virologist.getField();
			ArrayList<Field> neighbours = f.getNeighbours();
			if(neighbours.size()!=0) {
				f.removeVirologist(virologist);
				int r= new Random().nextInt(neighbours.size());
				Field newf = neighbours.get(r);
		        newf.addVirologist(virologist);
		        newf.movedOn(virologist);
		        virologist.setField(newf);
			}
		}
	}

}
