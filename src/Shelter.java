
public class Shelter extends Field{
	private Item items;
	
	//Óvóhely konstruktor
	public Shelter() {
		items=null;
	}
	// Felszerelést hozzáadja
	// @param i - az a felszerelés ami az óvóhelyen van
	public void addItem(Item i) {	
		items=i;
	}
	// Felszerelés felvétel
	// @param v -  A virológus aki felveszi a felszerelést
	public void pickedUp(Virologist v) {
		if(v.addItem(items)) {
			items=null;
		}
				
	}
	//Visszaadja a rajta lévõ tárgyat
	// @return - a rajta lévõ tárgy
	public Item getItem(){
		return items;
	}
}
