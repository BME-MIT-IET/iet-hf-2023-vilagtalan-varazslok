

public class NucleotideStorage extends Storage{
	//Nukleotid konstruktor
	public NucleotideStorage() {
		active=true;
	}
	// Nukleotid felvétel
	// @param v - Az a virológus aki felveszi a nukleotidot
	public void pickedUp(Virologist v) {
		if(active) {
			int maxmaterial=v.getMaxMaterial();
			v.setNucleotide(maxmaterial);
		}
		
	}

}
