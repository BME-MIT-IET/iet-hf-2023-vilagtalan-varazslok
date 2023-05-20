import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	//A játékban található tárgyak listája
	private ArrayList<Item> items = new ArrayList<Item>();
	//A játékban található virológusok listája
	private ArrayList<Virologist> virologists = new ArrayList<Virologist>();
	//A játékban található ágensek listája
	private static ArrayList<Agent> agents = new ArrayList<Agent>();
	//A játékban található mezõk listája
	private ArrayList<Field> fields = new ArrayList<Field>();
	
	private static Game game = null;
	
	/// Létrehozza a pályát, elhelyezi a genetikai kódokat, a tárgyakat, illetve a virológusokat is.
	public void generateMap(ArrayList<Field> f, ArrayList<Virologist> v) {
		fields = f;
		virologists = v;
		for(int i = 0; i < virologists.size(); i++) {
			virologists.get(i).move(fields.get(53));
		}
		
		ArrayList<Laboratory> ls = new ArrayList<Laboratory>();
		ArrayList<Shelter> ss = new ArrayList<Shelter>();
		for(int i = 0; i < fields.size(); i++) {
			if(fields.get(i) instanceof Laboratory) {
				ls.add((Laboratory) fields.get(i));
			}
			else if(fields.get(i) instanceof Shelter) {
				ss.add((Shelter) fields.get(i));
			}
		}
		
		agents.add(new Chorea());
		agents.add(new AntiChorea());
		agents.add(new Forget());
		agents.add(new AntiForget());
		agents.add(new Stun());
		agents.add(new AntiStun());
		agents.add(new Bear());
		ArrayList<Agent> temp;
		Random rand = new Random();
		int r = rand.nextInt(3);
		temp = new ArrayList<Agent>();
		temp.add(agents.get(6));
		ls.get(r).addAgents(temp);
		for(int i = 0; i < 3; i++) {
			int ra = rand.nextInt(ls.size());
			switch(i) {
			case 0:
				temp = new ArrayList<Agent>();
				temp.add(agents.get(0));
				temp.add(agents.get(1));
				ls.get(ra).addAgents(temp);
				break;
			case 1:
				temp = new ArrayList<Agent>();
				temp.add(agents.get(2));
				temp.add(agents.get(3));
				ls.get(ra).addAgents(temp);
				break;
			case 2:
				temp = new ArrayList<Agent>();
				temp.add(agents.get(4));
				temp.add(agents.get(5));
				ls.get(ra).addAgents(temp);
				break;
			}	
			ls.remove(ra);
		}
		
		items.add(new Axe());
		items.add(new Cape());
		items.add(new Gloves());
		items.add(new Backpack());
		items.add(new Axe());
		items.add(new Cape());
		items.add(new Gloves());
		items.add(new Backpack());
		for(int i = 0; i < 8; i++) {
			int ra = rand.nextInt(ss.size());
			ss.get(ra).addItem(items.get(i));
			ss.remove(ra);
		}
		
	}

	///  Ellenõrzi, hogy elegendõ genetikai kóddal rendelkezik e a játékos, a játék megnyeréséhez
	/// @param n az adott virológus jelenlegi megszerzett ágenseinek száma
	public boolean checkEndGame(int n){
		if(n==agents.size()-1) {
			return true;
		}
		return false;
	}
	
	//Eltávolítja a virológust a játékból, ha az létezik
	// @param v - az eltávolítandó virológus
	public void removeVirologist(Virologist v) {
		if(virologists.contains(v)) {
			virologists.remove(v);
		}
	}
	
	//Visszaadja a játékban szerpelõ virológusokat
	// @return - a virológusok listája
	public ArrayList<Virologist> getVirologists() {
		return virologists;
	}
	
	///Game konstruktor, privát, ezzel biztosítja, hogy az osztály Singleton ként mûködik
	private Game() {
	}
	
	///a függvény biztosítja, hogy az osztály Singleton ként mûküdik
	public static Game instance() {
		if(game==null) {
			game = new Game();
		}
		return game;
	}
}
