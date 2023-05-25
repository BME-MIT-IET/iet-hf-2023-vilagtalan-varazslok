package ProjLabVilagtalan;
import java.util.ArrayList;

public class Timer {

	private static ArrayList<Steppable> steppables = new ArrayList<Steppable>();
	private static Timer timer = null;
	
	///Meghívja a steppables minden elemén a Step() függvényt.
	public void tick() {
		ArrayList<Steppable> st=this.getSteppables();
		int asd = st.size();									
		for(int i=0; i<asd; i++) {
			if(i>=this.getSteppables().size()) break;
			st.get(i).step();
		}
	}
	
	///Felveszi a steppables listába a paraméterként megadott Steppable-t.
	///@param s Az adott léptetendõ példány, melyet innentõl kezdve léptetünk
	public void addSteppable(Steppable s) {	
		steppables.add(s);
	}
	
	///Eltávolítja a steppables listából a paraméterként megadott Steppable-t.
	///@param s Az adott, már nem léptetendõ példány
	public void removeSteppable(Steppable s) {
		steppables.remove(s);
	}
	
	///Timer konstruktor, privát, ezzel biztosítja, hogy az osztály Singleton ként mûködik
	private Timer() {
	}
	
	///a függvény biztosítja, hogy az osztály Singleton ként mûküdik
	public static Timer instance() {
		if(timer==null) {
			timer = new Timer();
		}
		return timer;
	}
	
	//kitakarít
	public static void clear() {
		steppables.clear();
	}
	
	//Visszaadja a steppable intefészű objektumok listáját
	// @return - a steppables lista
	public ArrayList<Steppable> getSteppables(){
		return steppables;
	}
}
