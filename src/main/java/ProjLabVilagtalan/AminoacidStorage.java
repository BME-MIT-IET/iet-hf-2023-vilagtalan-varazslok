package ProjLabVilagtalan;
public class AminoacidStorage extends Storage{

	// Amino konstruktor
	public AminoacidStorage() {
		active=true;
	}
	// Amino felv�tel
	// @param v - Az a virol�gus aki felveszi az aminot
	public void pickedUp(Virologist v) {
		if(active) {
			int maxmaterial=v.getMaxMaterial();
			v.setAminoacid(maxmaterial);
		}
		
	}
}
