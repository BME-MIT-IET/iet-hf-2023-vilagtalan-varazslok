
public class AminoacidStorage extends Storage{

	// Amino konstruktor
	public AminoacidStorage() {
		active=true;
	}
	// Amino felvétel
	// @param v - Az a virológus aki felveszi az aminot
	public void pickedUp(Virologist v) {
		if(active) {
			int maxmaterial=v.getMaxMaterial();
			v.setAminoacid(maxmaterial);
		}
		
	}
}
