package main.java;
import java.util.ArrayList;
import java.util.Random;

public class Game {
	private static Random rand=new Random();
	
	//A j�t�kban tal�lhat� t�rgyak list�ja
	private ArrayList<Item> items = new ArrayList<Item>();
	//A j�t�kban tal�lhat� virol�gusok list�ja
	private ArrayList<Virologist> virologists = new ArrayList<Virologist>();
	//A j�t�kban tal�lhat� �gensek list�ja
	private static ArrayList<Agent> agents = new ArrayList<Agent>();
	//A j�t�kban tal�lhat� mez�k list�ja
	private ArrayList<Field> fields = new ArrayList<Field>();
	
	private static Game game = null;
	
	/// L�trehozza a p�ly�t, elhelyezi a genetikai k�dokat, a t�rgyakat, illetve a virol�gusokat is.
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

	///  Ellen�rzi, hogy elegend� genetikai k�ddal rendelkezik e a j�t�kos, a j�t�k megnyer�s�hez
	/// @param n az adott virol�gus jelenlegi megszerzett �genseinek sz�ma
	public boolean checkEndGame(int n){
		if(n==agents.size()-1) {
			return true;
		}
		return false;
	}
	
	//Elt�vol�tja a virol�gust a j�t�kb�l, ha az l�tezik
	// @param v - az elt�vol�tand� virol�gus
	public void removeVirologist(Virologist v) {
		if(virologists.contains(v)) {
			virologists.remove(v);
		}
	}
	
	//Visszaadja a j�t�kban szerpel� virol�gusokat
	// @return - a virol�gusok list�ja
	public ArrayList<Virologist> getVirologists() {
		return virologists;
	}
	
	///Game konstruktor, priv�t, ezzel biztos�tja, hogy az oszt�ly Singleton k�nt m�k�dik
	private Game() {
	}
	
	///a f�ggv�ny biztos�tja, hogy az oszt�ly Singleton k�nt m�k�dik
	public static Game instance() {
		if(game==null) {
			game = new Game();
		}
		return game;
	}
}
