
public abstract class Storage extends Field{
	//Jelzi, mûködik-e a raktár
	protected boolean active;
	//Konstruktor
	public Storage() {
		active=true;
	}
	//Elpusztul a raktár (mûködõképesség falg hamis)
	public void destroyed() {
		active=false;
	}
	//Visszaadja a mûködõképességet
	// @return - a mûködõképesség
	public boolean getActive() {
		return active;
	}
}

