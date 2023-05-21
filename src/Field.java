import java.util.ArrayList;

public class Field {
	private ArrayList<Field>neighbours=new ArrayList<Field>();
	private ArrayList<Virologist> virologist;
	//Mezõ konstruktor
	public Field() {
		virologist = new ArrayList<Virologist>();
	}

	//Hozzáadja a virológust a mezõhöz
	//@param v - a hozzáadandó virológus
	public void addVirologist(Virologist v) {
		virologist.add(v);
	}
	//Eltávolítja a virológust a mezõröl
	//@param v - a Eltávolítandó virológus
	public void removeVirologist(Virologist v) {
		virologist.remove(v);
	}
	// Visszaadja a mezõ szomszédjait
	// @return - visszaadja a szomszédait
	public ArrayList<Field> getNeighbours(){
		return neighbours;
		
	}
	//Hozzáad egy szomszédos mezõt
	// @param f - a hozzáadandó mezõ
	public void addNeighbour(Field f) {
		neighbours.add(f);
	}
	// Az üres mezõn levõ dolgot felveszi a virológus
	// @ param v - virológust aki megpróbál felvenni valamit
	public void pickedUp(Virologist v) {
	}
	
	//A mezõre lép egy virológus
	// @param v - a virológus, aki rálép
	public void movedOn(Virologist v) {
	}
	
	//A mezõ elpusztul (itt nem, de felüldefiniálható)
	public void destroyed() {
	}
	
	//Visszaadja a mezõn lévõ virológusokat
	// return - a mezõn lévõ virológusok listája
	public  ArrayList<Virologist> getVirologists() {
		return virologist;
	}
	
}

