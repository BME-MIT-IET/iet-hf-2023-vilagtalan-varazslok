package ProjLabVilagtalan;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;

public class Control {
	//Az aktu�lis virol�gus
	private Virologist currentVirologist;
	//A jelenlegi index
	private int currentIndex;
	//A j�t�kos h�tral�v� akci�inak sz�ma
	private int actionsLeft;
	//A megjelen�s�rt felel� frame
	private MainFrame frame;
	
	//Konstruktor
	// @param f - a j�t�khoz haszn�lt frame
	public Control(MainFrame f) {
		frame = f;
		actionsLeft = 3;
	}
	
	//A mozg�sparancsot kezel� f�ggv�ny, megh�vja a modell kapcsol�d� f�ggv�ny�t.
	//Cs�kkenti az akci�k sz�m�t, ha elfogynak, skippel
	// @param f - amelyik mez�re mozog
	public void move(Field f) {
		currentVirologist.move(f);
		actionsLeft--;
		if(actionsLeft==0) {
			skipTurn();
		}
		frame.draw();
	}
	
	//A felv�tel-parancsot kezel� f�ggv�ny, az adott mez�n a megfelel� pick-up-line (hihi) megh�v�s�t kezdem�nyezi.
	//Cs�kkenti az akci�k sz�m�t, ha elfogynak, skippel. Azt is megn�zi, nyert-e valaki.
	public void pickUp() {
		currentVirologist.pickUp();
		if(Game.instance().checkEndGame(currentVirologist.getAgentsKnown().size())) {
			frame.endGame();
		}
		actionsLeft--;
		if(actionsLeft==0) {
			skipTurn();
		}
		frame.draw();
	}
	
	//A lop�sparancsot (iz� j�hiszem� eltulajdon�t�s) kezel� f�ggv�ny, megh�vja a modell kapcsol�d� f�ggv�ny�t.
	//Cs�kkenti az akci�k sz�m�t, ha elfogynak, skippel
	// @param v - a virol�gus, akit�l taktikai beszerz�s (lop�s) folyik
	public void steal(Virologist v) {
		currentVirologist.steal(v);
		actionsLeft--;
		if(actionsLeft==0) {
			skipTurn();
		}
		frame.draw();
	}
	
	//DIY James Bond helyett az �gensk�sz�t�s parancs�t kezel� f�ggv�ny, megh�vja a modell kapcsol�d� f�ggv�ny�t.
	//Cs�kkenti az akci�k sz�m�t, ha elfogynak, skippel
	// @param a - az elk�sz�tend� �gens
	public void createAgent(Agent a) {
		currentVirologist.createAgent(a);
		actionsLeft--;
		if(actionsLeft==0) {
			skipTurn();
		}
		frame.draw();
	}
	
	//A v�rushaszn�lat parancs�t kezel� f�ggv�ny, megh�vja a modell kapcsol�d� f�ggv�ny�t.
	//Cs�kkenti az akci�k sz�m�t, ha elfogynak, skippel
	// @param v - a felhaszn�land� v�rus
	// @param t - a virol�gus, akin a v�rus haszn�lva lesz
	public void useVirus(Virologist t, Virus v) {
		currentVirologist.useVirus(t, v);
		actionsLeft--;
		if(actionsLeft==0) {
			skipTurn();
		}
		frame.draw();
	}
	//A vakcinahaszn�lat parancs�t kezel� f�ggv�ny, megh�vja a modell kapcsol�d� f�ggv�ny�t.
	//Cs�kkenti az akci�k sz�m�t, ha elfogynak, skippel
	// @param v - a felhaszn�land� vakcina
	// @param t - a virol�gus, akin a vakcina haszn�lva lesz
	public void useVaccine(Virologist t, Vaccine v) {
		currentVirologist.useVaccine(t, v);
		actionsLeft--;
		if(actionsLeft==0) {
			skipTurn();
		}
		frame.draw();
	}
	//A t�rgyeldob�s parancs�t kezel� f�ggv�ny, megh�vja a modell kapcsol�d� f�ggv�ny�t.
	//Cs�kkenti az akci�k sz�m�t, ha elfogynak, skippel
	// @param i - az eldoband� t�rgy
	public void dropItem(Item i) {
		currentVirologist.loseItem(i);
		actionsLeft--;
		if(actionsLeft==0) {
			skipTurn();
		}
		frame.draw();
	}
	//�tadja a k�rt a k�vetkez� j�t�kosnak, �s be�ll�tja az ezzel kapcsolatos v�ltoz�kat. Ha mindenki k�re volt, a 
	//timer tickel egyet.
	public void skipTurn() {
		actionsLeft = 3;
		ArrayList<Virologist> vs = Game.instance().getVirologists();
		currentIndex++;
		if(currentIndex >= vs.size()) {
			currentIndex = 0;
			Timer.instance().tick();
		}
		currentVirologist = vs.get(currentIndex);
		frame.draw();
	}
	
	//Beolvassa a p�ly�t, l�trehozza �s inicializ�lja a view objektumokat, �s a modell felhazsn�lt objektumait
	//�sszek�ti a mez�ket, kirajzoltatja a p�ly�t.
	// @param file - a f�jl, ahonnan a p�lya beolvas�sra ker�l
	// @param virologistsNum - a j�t�kosok sz�ma
	public void startGame(String file, int virologistsNum) {
		actionsLeft = 3;
		currentIndex = 0;
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			String image = br.readLine();
			ArrayList<Field> fs = new ArrayList<Field>();
			ArrayList<FieldView> fvs = new ArrayList<FieldView>();
			ArrayList<Virologist> vs = new ArrayList<Virologist>();
			ArrayList<VirologistView> vvs = new ArrayList<VirologistView>();
			int fieldsNum = Integer.parseInt(br.readLine());
			for(int i = 1; i <= fieldsNum; i++) {
				String line = br.readLine();
				String[] lineSplit = line.split(";");
				Field f = null;
				switch(lineSplit[0].charAt(lineSplit[0].length()-1)) {
				case 'f':
					f = new Field();
					break;
				case 'l':
					f = new Laboratory();
					break;
				case 's':
					f = new Shelter();
					break;
				case 'a':
					f = new AminoacidStorage();
					break;
				case 'n':
					f = new NucleotideStorage();
					break;
				}
				fs.add(f);
				fvs.add(new FieldView(f, Integer.parseInt(lineSplit[1]), Integer.parseInt(lineSplit[2]), i));
			}
			for(int i = 0; i < fieldsNum; i++) {
				String line = br.readLine();
				String[] lineSplit = line.split(";");
				String[] lineSplit0Split = lineSplit[0].split(" ");
				lineSplit[0] = lineSplit0Split[1];
				for(int j = 0; j < lineSplit.length; j++) {
					fs.get(i).addNeighbour(fs.get(Integer.parseInt(lineSplit[j])-1));
				}
			}
			
			
			Virologist v1 = new Virologist();
			vs.add(v1);
			VirologistView vv1 = new VirologistView("Red", Color.red,  2, 2, v1);
			vvs.add(vv1);
			if(virologistsNum >= 2) {
				Virologist v2 = new Virologist();
				vs.add(v2);
				VirologistView vv2 = new VirologistView("Blue", Color.blue,  -8, -8, v2);
				vvs.add(vv2);
				if(virologistsNum >= 3) {
					Virologist v3 = new Virologist();
					vs.add(v3);
					VirologistView vv3 = new VirologistView("Green", Color.green,  -8, 2, v3);
					vvs.add(vv3);
					if(virologistsNum >= 4) {
						Virologist v4 = new Virologist();
						vs.add(v4);
						VirologistView vv4 = new VirologistView("Orange", Color.orange,  2, -8, v4);
						vvs.add(vv4);
					}
				}
			}
			
			Game.instance().generateMap(fs, vs);
			frame.getPlayArea().setup(fvs, vvs, image);
			currentVirologist = Game.instance().getVirologists().get(0);
			frame.draw();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	//Visszaadja a virol�gust, akinek a k�re van
	// @return - az aktu�lis virol�gus
	public Virologist getCurrentVirologist() {
		return currentVirologist;
	}
	
	//Visszaadja, h�ny akci�ja van m�g a soron lev� j�t�kosnak
	// @return - a h�tral�v� akci�k
	public int getActionsLeft() {
		return actionsLeft;
	}
}
