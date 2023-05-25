package ProjLabVilagtalan;
public class Shelter extends Field{
	private Item items;
	
	//�v�hely konstruktor
	public Shelter() {
		items=null;
	}
	// Felszerel�st hozz�adja
	// @param i - az a felszerel�s ami az �v�helyen van
	public void addItem(Item i) {	
		items=i;
	}
	// Felszerel�s felv�tel
	// @param v -  A virol�gus aki felveszi a felszerel�st
	public void pickedUp(Virologist v) {
		if(v.addItem(items)) {
			items=null;
		}
				
	}
	//Visszaadja a rajta l�v� t�rgyat
	// @return - a rajta l�v� t�rgy
	public Item getItem(){
		return items;
	}
}
