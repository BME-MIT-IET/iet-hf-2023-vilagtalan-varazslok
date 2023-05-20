import java.util.ArrayList;

public class Axe extends Item{
	//a balta élessége, használhatósága
	private boolean active;
	
	//A balta osztály konstruktora
	public Axe() {
		active = true;
	}
	
	//Kifejti a balta mozgás közbeni hatását. Az aktuális mezõn tartózkodó összes virológust megbaltázza
	//majd ha ez sikeres, kicsorbítha a baltát
	// @param v - a virológus, aki baltát használ
	public void moveEffect(Virologist v) {
		if(active) {
			Field f1 = v.getField();
			ArrayList<Virologist> arl = f1.getVirologists();
			for(int i = 0; i<arl.size(); i++) {
				if(arl.get(i).axed()) {
					active = false;
				}
			}
		}
		return;
	}
	
	//Visszaadja a balta élességét
	// @return - a balta élessége
	public boolean getActive() {
		return active;
	}
}

