package ProjLabVilagtalan;
import java.util.ArrayList;

public class Axe extends Item{
	//a balta �less�ge, haszn�lhat�s�ga
	private boolean active;
	
	//A balta oszt�ly konstruktora
	public Axe() {
		active = true;
	}
	
	//Kifejti a balta mozg�s k�zbeni hat�s�t. Az aktu�lis mez�n tart�zkod� �sszes virol�gust megbalt�zza
	//majd ha ez sikeres, kicsorb�tha a balt�t
	// @param v - a virol�gus, aki balt�t haszn�l
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
	
	//Visszaadja a balta �less�g�t
	// @return - a balta �less�ge
	public boolean getActive() {
		return active;
	}
}

