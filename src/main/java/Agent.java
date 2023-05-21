
public abstract class Agent implements Steppable{
	protected int aminoacidCost;
	protected int nucleotideCost;
	protected int duration;
	protected int timeLeft;
	protected int ttl;
	protected Virologist virologist;
	
	// Visszaadja az ágens aminosavköltségét
    // @return - az aminosavköltség
	public int getAminoCost() {
		return aminoacidCost;
	}
	
	// Visszaadja az ágens nukleotidköltségét
    // @return - a nukleotidköltség
	public int getNucleoCost() {
		return nucleotideCost;
	}
	
	// Visszaadja az ágens hatásidejét
    // @return - a hatásidõ
	public int getDuration() {
		return duration;
	}
	
	// Visszaadja az ágens hatásának hátralévõ idejét
    // @return - a hátralévõ idõ
	public int getTimeLeft() {
		return timeLeft;
	}
	
	// Beállítja az ágens hatásának hátralévõ idejét
    // @param n - a hátralévõ idõ
	public void setTimeLeft(int n) {
		timeLeft = n;
	}
	
	// Idõben lépteti az ágenst
	public void step() {
		if(ttl > 0)
			ttl--;
		if(ttl==0) {
			virologist.loseCreatedAgent(this);
		}
	}
	
	// Beállítja az ágenst birtokló virológust
    // @param v - a virológus
	public void setVirologist(Virologist v) {
		virologist = v;
	}
	
	// Létrehoz egy új, ugyanolyan típusú ágenst
	// @return - az új vírus
	public abstract Agent create();
	
	//Visszaadja az ágens lejáratig tartó idõt
	// @return - az ágens lejáratig tartó idõ
	public int getTTL()
    {
        return ttl;
    }
	
	//Visszaadja a hozzá tartozó virológust
	// @return -  a virológus
    public Virologist getVirologist() {
        return virologist;
    }
	
}
