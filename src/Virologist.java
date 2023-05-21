
import java.util.ArrayList;

public class Virologist {
	
	
	/** A virológusnál levõ nukleotid mennyisége.    
     * */
	private int nucleotide;
	
	/** A virológusnál levõ aminosav mennyisége	     
     * */
	
	private int aminoacid;
	/** A virológusnál levõ maximális anyagmennyiség, alapból 100
		anyagonként.    
     * */
	private int maxMaterial=100;
	/**A virológus által készíthetõ ágensek listája.
     * */
	private ArrayList<Agent> agentsKnown=new ArrayList<Agent>();
	/** A virológus által elkészített, éppen felhasználható
		ágensek listája.     
     * */
	private ArrayList<Agent> agentsCreated;
	/** A virológusra jelenleg ható (felkent) vírusok listája	     
     * */
	private ArrayList<Virus> viruses=new ArrayList<Virus>();
	/** A virológusra jelenleg ható (felkent) vakcinák listája      
     * */
	private ArrayList<Vaccine> vaccines=new ArrayList<Vaccine>();
	/** A virológus által birtokolt tárgyak (védõfelszerelés) listája	     
     * */
	private ArrayList<Item> items=new ArrayList<Item>();
	/** Az a mezõ, amin a virológus áll.	     
     * */
	private Field field;
	
	/** Konstruktor	     
	     * */
	public Virologist() {
		agentsCreated=new ArrayList<Agent>();
		aminoacid = 50;
		nucleotide = 50;
	}
	
	/** Megvizsgálja, hogy cselekvõképes-e. Ha igen, a virológus a
		paraméterben megadott mezõre lép.
     * @param f - A mezõ, ahova lép
     * */
	public void move(Field f) {
		for(Virus vir: viruses) {
			if(!vir.canAct()) {
				return;
			}				
		}
		if(field!=null) {
			field.removeVirologist(this);
		}
		field=f;
		f.addVirologist(this);
		for(Item i: items) {
			i.moveEffect(this);
		}
		f.movedOn(this);
	}
	/**Visszaadja a mezõt, amelyen a virológus áll.
     * 
     * @return - a mezõ, ahol a virológus áll
     * */
	public Field getField() {
		return field;		
	}
	/** A virológus felveszi a mezõn található dolgokat.
     * */
	public void pickUp() {
		for(Virus vir: viruses) {
			if(!vir.canAct()) {
				return;
			}				
		}
		field.pickedUp(this);
	}
	/**Ellenõrzi, hogy a virológus felveheti-e az adott tárgyat. Ha
		felveheti akkor fel is veszi.  
     * @param i - a felvevendõ tárgy
     * @return - a felvétel sikeressége
     * */
	public boolean addItem(Item i) {
		if(i==null) return false;
		for(Item it: items) {
			if(it.getClass()==i.getClass()) {
				return false;
			}				
		}
		if(items.size()==3) {
			return false;
		}
		items.add(i);
		i.equip(this);
		return true;
	}
	/** Amennyiben a virológus nem ismeri még az
		adott ágenseket, akkor megtanulja.
     * @param a - A megtanulandó ágensek listája
     * 
     * */
	public void addKnownAgents (ArrayList<Agent> a) {
		for(Agent ag: agentsKnown) {
			for(Agent ag2: a) {
				if(ag2.getClass()==ag.getClass())
					return;
			}			
		}
		agentsKnown.addAll(a);
		Game.instance().checkEndGame(agentsKnown.size());
	}
	/** : Beállítja a virológusnál található aminosavmennyiséget a
		paraméterben kapott számra

     * @param n - A beállítandó mennyiség
     * 
     * */
	public void setAminoacid(int n) {
		aminoacid=n;
	}
	/** : Beállítja a virológusnál található nukleotidmennyiséget a
		paraméterben kapott számra.

	     * @param n - A beállítandó mennyiség
	     * 
	     * */
	public void setNucleotide(int n) {
		nucleotide=n;
	}
	/** : Beállítja azt a mennyiségetet, ami a virológusnál lehet
		pillanatnyilag egy idõben egy anyagfajtából.

	     * @param n - A beállítandó mennyiség
	     * 
	     * */
	public void setMaxMaterial(int n) {
		maxMaterial=n;
	}
	/**Visszaadja azt a mennyiséget, ami a virológusnál lehet egy
		idõben egy anyagfajtából alapértelmezetten.
     * @return - megakadalyozza-e a tamadast
     * */
	public int getMaxMaterial() {
		return maxMaterial;
	}
	/**Megvizsgálja, hogy cselekvõképes-e. Ha igen, lop a
		paraméterben kapott virológustól; meghívja a paraméterben kapott virológus
		stolenFrom függvényét
     * @param v - A virológus, akitõl lopni akar
     * 
     * */
	public void steal(Virologist v) {
		for(Virus vir: viruses) {
			if(!vir.canAct()) {
				return;
			}				
		}
		v.stolenFrom(this, items, maxMaterial-aminoacid, maxMaterial-nucleotide);
	}
	/**A virológus
		ezzel kezeli le, ha meglopták, a lopótól kapja, paraméterben a lopó virológussal, a
		tárgylistájával, és azzal, hogy mennyi anyag kell neki az egyes anyagfajtákból, hogy
		elérje a maximumot. A meglopott virológus végigkérdezi a saját magán aktív
		vírusokon, hogy van-e olyan, amely lophatóvá tenné (jelenleg csak a bénultság, ez
		késõbbiekben bõvülhet): ha nincs akkor egyszerûen visszatér, ha van, akkor pedig
		meghívja a lopón a receive függvényt, paraméterben a neki átadható tárgyak listájával,
		illetve a lehetõ legtöbb nukleotiddal és aminosavval, amennyi még elfér nála (és
		rendelkezik vele). Ezt a mennyiséget magától levonja a meglopott virológus.

     * @param v - Virologus, aki lop
     * @param items - a lopó tárgyainak a listája
     * @param amino - a lopónak szükséges aminosav mennyisége
     * @param nucleo - a lopónak szükséges nukleotid mennyisége
     * */
	public void stolenFrom(Virologist v, ArrayList<Item> items, int amino, int nucleo) {		
		boolean temp=false;
		for(Virus vir: viruses) {
			if(vir.stealable()) {
				temp=true;
				break;
			}				
		}
		if(!temp)
		{
			return;
		}
		ArrayList<Item> listStolen=new ArrayList<Item>();
		for(Item i: this.items) {
			boolean temp2 = true;
			for(Item j: items) {
			if(i.getClass()==j.getClass())
				temp2 = false;
			}
			if(temp2 && !listStolen.contains(i)) {
				listStolen.add(i);
			}
		}
		int maxa= aminoacid>amino?amino:aminoacid; 
		int maxn=nucleotide>nucleo?nucleo:nucleotide;
		v.recieve(listStolen, maxa, maxn);
		nucleotide-=maxn;
		aminoacid-=maxa;
		for(Item i: listStolen) {
			i.unequip(this);
		}
		for(Item i: listStolen) {
			if(this.items.contains(i)) {
				this.items.remove(i);
			}
		}
		
	}
	/**A lopó virológuson hívja meg a
		meglopott virológus, paraméterben a lopó által kapott (ellopott) tárgyak listájával,
		aminosav- és nukleotidmennyiséggel, ezeket a lopó virológus hozzáadja magához.
     * @param items - az ellopott tárgyak listája
     * @param amino - az ellopott aminosav mennyisége
     * @param nucleo - az ellopott nukleotid mennyisége
     * */
	public void recieve(ArrayList<Item> items, int amino, int nucleo) {
		aminoacid+=amino;
		nucleotide+=nucleo;
		for(Item i: items) {
			i.equip(this);
			this.items.add(i);
		}
		
	}
	/**Megvizsgálja, hogy cselekvõképes-e. Ha igen, lekéri a
		paraméterben kapott ágens anyagköltségét, és ha van elég anyaga, meghívja az ágens
		create() függvényét, a visszatérési értékben kapott ágenset hozzáadja az elkészített
		ágensek listájához, ezután pedig hozzáadja a Timer steppables listájához. Az ágensen
		beállítja a hozzá tartozó virológust.
     * @param ag - Az elkészítendõ ágens
     * 
     * */
	public void createAgent(Agent ag) {
		for(Virus vir: viruses) {
			if(!vir.canAct()) {
				return;
			}				
		}
		if(ag.getAminoCost()<=aminoacid & ag.getNucleoCost()<=nucleotide) {
			aminoacid-=ag.getAminoCost();
			nucleotide-=ag.getNucleoCost();
			Agent newagent=ag.create();
			agentsCreated.add(newagent);
			newagent.setVirologist(this);
			Timer.instance().addSteppable(newagent);
		}
	}
	/**Megvizsgálja, hogy cselekvõképes-e. Ha igen, a
		virológus frissíti a vírus idejét, és meghívja a paraméterben kapott virológus
		gotInfected függvényét paraméterben a vírussal
     * @param t - Virologus, akit megfertõzni szeretne
     * @param v - Vírus, amivel fertõz
     * */
	public void useVirus (Virologist t, Virus v) {
		for(Virus vir: viruses) {
			if(!vir.canAct()) {
				return;
			}				
		}
		int maxduration=v.getDuration();
		v.setTimeLeft(maxduration);
		t.gotInfected(this, v);
		agentsCreated.remove(v);
	}
	/**Lekéri az összes védõfelszerelés attackEffect
		függvényét, és ha kap igaz értéket, visszatér. Ha nem, akkor lekéri az összes
		vakcinájától a counter függvényt, és ha kap igazat egyiktõl is, akkor eltávolítja a
		paraméterben kapott vírust a Timer steppables listájából a removeSteppable
		függvénnyel, ha viszont mind hamissal tér vissza, akkor hozzáadja a paraméterben
		kapott vírust a rá felkent vírusok listájához. Az ágensen beállítja a hozzá tartozó
		virológust (magát), és kifejteti az azonnali hatását.
     * @param t - Virologus, aki fertõz
     * @param v - Vírus, amivel fertõzték
     * */
	public boolean gotInfected(Virologist t, Virus v) {
		for(Item it: items) {
			if(it.attackEffect(t, this, v)) {
				return false;
			}				
		}
		for(Vaccine vacc: vaccines) {
			if(vacc.counter(v)) {
				Timer.instance().removeSteppable(v);
				return false;
			}
		}
		viruses.add(v);
		v.setVirologist(this);
		v.effect();
		return true;
	}
	
	/**Megvizsgálja, hogy cselekvõképes-e. Ha igen,
		a virológus frissíti a vakcina idejét, és meghívja a paraméterben kapott virológus
		gotVaccinated függvényét paraméterben a vakcinával.

     * @param t - Virologus, akinek beadjuk a vakcinát
     * @param v - A vakcina, amit beadnak
     * */
	public void useVaccine (Virologist t, Vaccine v) {
		boolean temp=true;
		for(Virus vir: viruses) {
			if(!vir.canAct()) {
				temp=false;
				break;
			}				
		}
		if(!temp)
		{
			return;
		}
		int maxduration=v.getDuration();
		v.setTimeLeft(maxduration);
		t.gotVaccinated(v);
		agentsCreated.remove(v);
	}
	
	/**Hozzáadja a paraméterben kapott vakcinát az aktív
		vakcinák listájához. Az ágensen beállítja a hozzá tartozó virológust (magát)
     * @param v - A beadott vakcina
     * 
     * */
	public void gotVaccinated(Vaccine v) {
		vaccines.add(v);
		v.setVirologist(this);
	}
	
	/**Beállítja a virológus által ismert (elkészíthetõ)
		ágensek listáját a paraméterben kapottra.
     * @param a - a beállítandó ágenslista
     * 
     * */
	public void setAgentsKnown(ArrayList<Agent> a) {
		agentsKnown = a;
	}
	
	/**Kiveszi a vírusok listájából a paraméterként kapott vírust.
     * @param v - A virus, amit elveszít
     * 
     * */
	public void loseVirus(Virus v) {
		if(viruses.remove(v)) {Timer.instance().removeSteppable(v);}
	}
	
	/**Kiveszi a vakcinák listájából a paraméterként kapott
		vakcinát.
     * @param v - Vakcina, amit elveszít
     * 
     * */
	public void loseVaccine(Vaccine v) {
		if(vaccines.remove(v)) {Timer.instance().removeSteppable(v);}
	}
	
	/** Kiveszi a felhasználható ágensek listájából a
		paraméterként kapott ágens
     * @param a - Az ágens, amit elveszít
     * 
     * */
	public void loseCreatedAgent(Agent a) {
		if(agentsCreated.remove(a)) {
			Timer.instance().removeSteppable(a);
		}
	}
	/**Visszaadja a virológuson aktív vírusokat
     * 
     * @return - a virológuson aktív vírusok
     * */
	public ArrayList<Virus> getActiveViruses(){
		return viruses;
	}
	/**Megtámadják a virológust baltával. Ha sikeres, akkor meghal, cleareli a listáit, eltávolítja magát a játékból.
     * 
     * */
	public boolean axed() {
		
		
		for(int i = 0; i < viruses.size(); i++) {
			if(viruses.get(i).getClass() == Bear.class) {
				field.removeVirologist(this);
				Timer.instance().removeSteppable(viruses.get(i));
	            
	            for(int j = 0; j < viruses.size(); j++) {
	            	Timer.instance().removeSteppable(viruses.get(j));
	            }
	            viruses.clear();
	            for(int j = 0; j < vaccines.size(); j++) {
	            	Timer.instance().removeSteppable(vaccines.get(j));
	            }
	            vaccines.clear();
	            for(int j = 0; j < items.size(); j++) {
	            }
	            items.clear();
	            for(int j = 0; j < agentsCreated.size(); j++) {
	            	Timer.instance().removeSteppable(agentsCreated.get(j));
	            }
	            agentsCreated.clear();
	            Game.instance().removeVirologist(this);
				return true;
			}
		}
		return false;
	}
	/**Eldobja az adott tárgyat
     * 
     * @param i  - Az eldobandó tárgy
     * */
	public void loseItem(Item i) {
		items.remove(i);
	}
	/**Visszaadja a birtokolt nukleotid mennyiségét
     * 
     * @return - a birtokolt nukleotid mennyisége
     * */
	public int getNucleotide() {
		return nucleotide;
	}
	/**Visszaadja a birtokolt aminosav mennyiségét
     * 
     * @return - a birtokolt aminosav mennyisége
     * */
	public int getAminoacid() {
		return aminoacid;
	}
	/**Visszaadja a virológus által ismert ágenseket
     * 
     * @return - a virológus által ismert ágensek
     * */
	public ArrayList<Agent> getAgentsKnown() {
		return agentsKnown;
	}
	/**Visszaadja a virológus által legyártott ágenseket
     * 
     * @return - a virológus által legyártott ágensek
     * */
	public ArrayList<Agent> getAgentsCreated(){
		return agentsCreated;
	}
	/**Visszaadja a virológuson aktív vakcinákat
     * 
     * @return - a virológuson aktív vakcinák
     * */
	public ArrayList<Vaccine> getActiveVaccines(){
		return vaccines;
	}
	/**Visszaadja a virológus tárygait
     * 
     * @return - a virológus tárgyai
     * */
	public ArrayList<Item> getItems(){
		return items;
	}
	/**Beállítja a virológus mezõjét
     * 
     * @param newf  - Az új, beállítandó mezõ
     * */
	public void setField(Field newf) {
		field=newf;
		
	}
}
