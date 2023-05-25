package ProjLabVilagtalan;
import java.util.ArrayList;

public class Field {
	private ArrayList<Field>neighbours=new ArrayList<Field>();
	private ArrayList<Virologist> virologist;
	//Mez� konstruktor
	public Field() {
		virologist = new ArrayList<Virologist>();
	}

	//Hozz�adja a virol�gust a mez�h�z
	//@param v - a hozz�adand� virol�gus
	public void addVirologist(Virologist v) {
		virologist.add(v);
	}
	//Elt�vol�tja a virol�gust a mez�r�l
	//@param v - a Elt�vol�tand� virol�gus
	public void removeVirologist(Virologist v) {
		virologist.remove(v);
	}
	// Visszaadja a mez� szomsz�djait
	// @return - visszaadja a szomsz�dait
	public ArrayList<Field> getNeighbours(){
		return neighbours;
		
	}
	//Hozz�ad egy szomsz�dos mez�t
	// @param f - a hozz�adand� mez�
	public void addNeighbour(Field f) {
		neighbours.add(f);
	}
	// Az �res mez�n lev� dolgot felveszi a virol�gus
	// @ param v - virol�gust aki megpr�b�l felvenni valamit
	public void pickedUp(Virologist v) {
	}
	
	//A mez�re l�p egy virol�gus
	// @param v - a virol�gus, aki r�l�p
	public void movedOn(Virologist v) {
	}
	
	//A mez� elpusztul (itt nem, de fel�ldefini�lhat�)
	public void destroyed() {
	}
	
	//Visszaadja a mez�n l�v� virol�gusokat
	// return - a mez�n l�v� virol�gusok list�ja
	public  ArrayList<Virologist> getVirologists() {
		return virologist;
	}
	
}

