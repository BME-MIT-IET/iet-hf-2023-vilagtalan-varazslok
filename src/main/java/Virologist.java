package main.java;
import java.util.ArrayList;

public class Virologist {
	
	
	/** A virol�gusn�l lev� nukleotid mennyis�ge.    
     * */
	private int nucleotide;
	
	/** A virol�gusn�l lev� aminosav mennyis�ge	     
     * */
	
	private int aminoacid;
	/** A virol�gusn�l lev� maxim�lis anyagmennyis�g, alapb�l 100
		anyagonk�nt.    
     * */
	private int maxMaterial=100;
	/**A virol�gus �ltal k�sz�thet� �gensek list�ja.
     * */
	private ArrayList<Agent> agentsKnown=new ArrayList<Agent>();
	/** A virol�gus �ltal elk�sz�tett, �ppen felhaszn�lhat�
		�gensek list�ja.     
     * */
	private ArrayList<Agent> agentsCreated;
	/** A virol�gusra jelenleg hat� (felkent) v�rusok list�ja	     
     * */
	private ArrayList<Virus> viruses=new ArrayList<Virus>();
	/** A virol�gusra jelenleg hat� (felkent) vakcin�k list�ja      
     * */
	private ArrayList<Vaccine> vaccines=new ArrayList<Vaccine>();
	/** A virol�gus �ltal birtokolt t�rgyak (v�d�felszerel�s) list�ja	     
     * */
	private ArrayList<Item> items=new ArrayList<Item>();
	/** Az a mez�, amin a virol�gus �ll.	     
     * */
	private Field field;
	
	/** Konstruktor	     
	     * */
	public Virologist() {
		agentsCreated=new ArrayList<Agent>();
		aminoacid = 50;
		nucleotide = 50;
	}
	
	/** Megvizsg�lja, hogy cselekv�k�pes-e. Ha igen, a virol�gus a
		param�terben megadott mez�re l�p.
     * @param f - A mez�, ahova l�p
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
	/**Visszaadja a mez�t, amelyen a virol�gus �ll.
     * 
     * @return - a mez�, ahol a virol�gus �ll
     * */
	public Field getField() {
		return field;		
	}
	/** A virol�gus felveszi a mez�n tal�lhat� dolgokat.
     * */
	public void pickUp() {
		for(Virus vir: viruses) {
			if(!vir.canAct()) {
				return;
			}				
		}
		field.pickedUp(this);
	}
	/**Ellen�rzi, hogy a virol�gus felveheti-e az adott t�rgyat. Ha
		felveheti akkor fel is veszi.  
     * @param i - a felvevend� t�rgy
     * @return - a felv�tel sikeress�ge
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
	/** Amennyiben a virol�gus nem ismeri m�g az
		adott �genseket, akkor megtanulja.
     * @param a - A megtanuland� �gensek list�ja
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
	/** : Be�ll�tja a virol�gusn�l tal�lhat� aminosavmennyis�get a
		param�terben kapott sz�mra

     * @param n - A be�ll�tand� mennyis�g
     * 
     * */
	public void setAminoacid(int n) {
		aminoacid=n;
	}
	/** : Be�ll�tja a virol�gusn�l tal�lhat� nukleotidmennyis�get a
		param�terben kapott sz�mra.

	     * @param n - A be�ll�tand� mennyis�g
	     * 
	     * */
	public void setNucleotide(int n) {
		nucleotide=n;
	}
	/** : Be�ll�tja azt a mennyis�getet, ami a virol�gusn�l lehet
		pillanatnyilag egy id�ben egy anyagfajt�b�l.

	     * @param n - A be�ll�tand� mennyis�g
	     * 
	     * */
	public void setMaxMaterial(int n) {
		maxMaterial=n;
	}
	/**Visszaadja azt a mennyis�get, ami a virol�gusn�l lehet egy
		id�ben egy anyagfajt�b�l alap�rtelmezetten.
     * @return - megakadalyozza-e a tamadast
     * */
	public int getMaxMaterial() {
		return maxMaterial;
	}
	/**Megvizsg�lja, hogy cselekv�k�pes-e. Ha igen, lop a
		param�terben kapott virol�gust�l; megh�vja a param�terben kapott virol�gus
		stolenFrom f�ggv�ny�t
     * @param v - A virol�gus, akit�l lopni akar
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
	/**A virol�gus
		ezzel kezeli le, ha meglopt�k, a lop�t�l kapja, param�terben a lop� virol�gussal, a
		t�rgylist�j�val, �s azzal, hogy mennyi anyag kell neki az egyes anyagfajt�kb�l, hogy
		el�rje a maximumot. A meglopott virol�gus v�gigk�rdezi a saj�t mag�n akt�v
		v�rusokon, hogy van-e olyan, amely lophat�v� tenn� (jelenleg csak a b�nults�g, ez
		k�s�bbiekben b�v�lhet): ha nincs akkor egyszer�en visszat�r, ha van, akkor pedig
		megh�vja a lop�n a receive f�ggv�nyt, param�terben a neki �tadhat� t�rgyak list�j�val,
		illetve a lehet� legt�bb nukleotiddal �s aminosavval, amennyi m�g elf�r n�la (�s
		rendelkezik vele). Ezt a mennyis�get mag�t�l levonja a meglopott virol�gus.

     * @param v - Virologus, aki lop
     * @param items - a lop� t�rgyainak a list�ja
     * @param amino - a lop�nak sz�ks�ges aminosav mennyis�ge
     * @param nucleo - a lop�nak sz�ks�ges nukleotid mennyis�ge
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
	/**A lop� virol�guson h�vja meg a
		meglopott virol�gus, param�terben a lop� �ltal kapott (ellopott) t�rgyak list�j�val,
		aminosav- �s nukleotidmennyis�ggel, ezeket a lop� virol�gus hozz�adja mag�hoz.
     * @param items - az ellopott t�rgyak list�ja
     * @param amino - az ellopott aminosav mennyis�ge
     * @param nucleo - az ellopott nukleotid mennyis�ge
     * */
	public void recieve(ArrayList<Item> items, int amino, int nucleo) {
		aminoacid+=amino;
		nucleotide+=nucleo;
		for(Item i: items) {
			i.equip(this);
			this.items.add(i);
		}
		
	}
	/**Megvizsg�lja, hogy cselekv�k�pes-e. Ha igen, lek�ri a
		param�terben kapott �gens anyagk�lts�g�t, �s ha van el�g anyaga, megh�vja az �gens
		create() f�ggv�ny�t, a visszat�r�si �rt�kben kapott �genset hozz�adja az elk�sz�tett
		�gensek list�j�hoz, ezut�n pedig hozz�adja a Timer steppables list�j�hoz. Az �gensen
		be�ll�tja a hozz� tartoz� virol�gust.
     * @param ag - Az elk�sz�tend� �gens
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
	/**Megvizsg�lja, hogy cselekv�k�pes-e. Ha igen, a
		virol�gus friss�ti a v�rus idej�t, �s megh�vja a param�terben kapott virol�gus
		gotInfected f�ggv�ny�t param�terben a v�russal
     * @param t - Virologus, akit megfert�zni szeretne
     * @param v - V�rus, amivel fert�z
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
	/**Lek�ri az �sszes v�d�felszerel�s attackEffect
		f�ggv�ny�t, �s ha kap igaz �rt�ket, visszat�r. Ha nem, akkor lek�ri az �sszes
		vakcin�j�t�l a counter f�ggv�nyt, �s ha kap igazat egyikt�l is, akkor elt�vol�tja a
		param�terben kapott v�rust a Timer steppables list�j�b�l a removeSteppable
		f�ggv�nnyel, ha viszont mind hamissal t�r vissza, akkor hozz�adja a param�terben
		kapott v�rust a r� felkent v�rusok list�j�hoz. Az �gensen be�ll�tja a hozz� tartoz�
		virol�gust (mag�t), �s kifejteti az azonnali hat�s�t.
     * @param t - Virologus, aki fert�z
     * @param v - V�rus, amivel fert�zt�k
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
	
	/**Megvizsg�lja, hogy cselekv�k�pes-e. Ha igen,
		a virol�gus friss�ti a vakcina idej�t, �s megh�vja a param�terben kapott virol�gus
		gotVaccinated f�ggv�ny�t param�terben a vakcin�val.

     * @param t - Virologus, akinek beadjuk a vakcin�t
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
	
	/**Hozz�adja a param�terben kapott vakcin�t az akt�v
		vakcin�k list�j�hoz. Az �gensen be�ll�tja a hozz� tartoz� virol�gust (mag�t)
     * @param v - A beadott vakcina
     * 
     * */
	public void gotVaccinated(Vaccine v) {
		vaccines.add(v);
		v.setVirologist(this);
	}
	
	/**Be�ll�tja a virol�gus �ltal ismert (elk�sz�thet�)
		�gensek list�j�t a param�terben kapottra.
     * @param a - a be�ll�tand� �genslista
     * 
     * */
	public void setAgentsKnown(ArrayList<Agent> a) {
		agentsKnown = a;
	}
	
	/**Kiveszi a v�rusok list�j�b�l a param�terk�nt kapott v�rust.
     * @param v - A virus, amit elvesz�t
     * 
     * */
	public void loseVirus(Virus v) {
		if(viruses.remove(v)) {Timer.instance().removeSteppable(v);}
	}
	
	/**Kiveszi a vakcin�k list�j�b�l a param�terk�nt kapott
		vakcin�t.
     * @param v - Vakcina, amit elvesz�t
     * 
     * */
	public void loseVaccine(Vaccine v) {
		if(vaccines.remove(v)) {Timer.instance().removeSteppable(v);}
	}
	
	/** Kiveszi a felhaszn�lhat� �gensek list�j�b�l a
		param�terk�nt kapott �gens
     * @param a - Az �gens, amit elvesz�t
     * 
     * */
	public void loseCreatedAgent(Agent a) {
		if(agentsCreated.remove(a)) {
			Timer.instance().removeSteppable(a);
		}
	}
	/**Visszaadja a virol�guson akt�v v�rusokat
     * 
     * @return - a virol�guson akt�v v�rusok
     * */
	public ArrayList<Virus> getActiveViruses(){
		return viruses;
	}
	/**Megt�madj�k a virol�gust balt�val. Ha sikeres, akkor meghal, cleareli a list�it, elt�vol�tja mag�t a j�t�kb�l.
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
	/**Eldobja az adott t�rgyat
     * 
     * @param i  - Az eldoband� t�rgy
     * */
	public void loseItem(Item i) {
		items.remove(i);
	}
	/**Visszaadja a birtokolt nukleotid mennyis�g�t
     * 
     * @return - a birtokolt nukleotid mennyis�ge
     * */
	public int getNucleotide() {
		return nucleotide;
	}
	/**Visszaadja a birtokolt aminosav mennyis�g�t
     * 
     * @return - a birtokolt aminosav mennyis�ge
     * */
	public int getAminoacid() {
		return aminoacid;
	}
	/**Visszaadja a virol�gus �ltal ismert �genseket
     * 
     * @return - a virol�gus �ltal ismert �gensek
     * */
	public ArrayList<Agent> getAgentsKnown() {
		return agentsKnown;
	}
	/**Visszaadja a virol�gus �ltal legy�rtott �genseket
     * 
     * @return - a virol�gus �ltal legy�rtott �gensek
     * */
	public ArrayList<Agent> getAgentsCreated(){
		return agentsCreated;
	}
	/**Visszaadja a virol�guson akt�v vakcin�kat
     * 
     * @return - a virol�guson akt�v vakcin�k
     * */
	public ArrayList<Vaccine> getActiveVaccines(){
		return vaccines;
	}
	/**Visszaadja a virol�gus t�rygait
     * 
     * @return - a virol�gus t�rgyai
     * */
	public ArrayList<Item> getItems(){
		return items;
	}
	/**Be�ll�tja a virol�gus mez�j�t
     * 
     * @param newf  - Az �j, be�ll�tand� mez�
     * */
	public void setField(Field newf) {
		field=newf;
		
	}
}
