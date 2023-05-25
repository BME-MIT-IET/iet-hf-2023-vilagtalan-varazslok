package ProjLabVilagtalan;

public class NucleotideStorage extends Storage{
	//Nukleotid konstruktor
	public NucleotideStorage() {
		active=true;
	}
	// Nukleotid felv�tel
	// @param v - Az a virol�gus aki felveszi a nukleotidot
	public void pickedUp(Virologist v) {
		if(active) {
			int maxmaterial=v.getMaxMaterial();
			v.setNucleotide(maxmaterial);
		}
		
	}

}
