package ProjLabVilagtalan;




public abstract class Agent implements Steppable{
	protected int aminoacidCost;
	protected int nucleotideCost;
	protected int duration;
	protected int timeLeft;
	protected int ttl;
	protected Virologist virologist;
	
	// Visszaadja az �gens aminosavk�lts�g�t
    // @return - az aminosavk�lts�g
	public int getAminoCost() {
		return aminoacidCost;
	}
	
	// Visszaadja az �gens nukleotidk�lts�g�t
    // @return - a nukleotidk�lts�g
	public int getNucleoCost() {
		return nucleotideCost;
	}
	
	// Visszaadja az �gens hat�sidej�t
    // @return - a hat�sid�
	public int getDuration() {
		return duration;
	}
	
	// Visszaadja az �gens hat�s�nak h�tral�v� idej�t
    // @return - a h�tral�v� id�
	public int getTimeLeft() {
		return timeLeft;
	}
	
	// Be�ll�tja az �gens hat�s�nak h�tral�v� idej�t
    // @param n - a h�tral�v� id�
	public void setTimeLeft(int n) {
		timeLeft = n;
	}
	
	// Id�ben l�pteti az �genst
	public void step() {
		if(ttl > 0)
			ttl--;
		if(ttl==0) {
			virologist.loseCreatedAgent(this);
		}
	}
	
	// Be�ll�tja az �genst birtokl� virol�gust
    // @param v - a virol�gus
	public void setVirologist(Virologist v) {
		virologist = v;
	}
	
	// L�trehoz egy �j, ugyanolyan t�pus� �genst
	// @return - az �j v�rus
	public abstract Agent create();
	
	//Visszaadja az �gens lej�ratig tart� id�t
	// @return - az �gens lej�ratig tart� id�
	public int getTTL()
    {
        return ttl;
    }
	
	//Visszaadja a hozz� tartoz� virol�gust
	// @return -  a virol�gus
    public Virologist getVirologist() {
        return virologist;
    }
	
}
