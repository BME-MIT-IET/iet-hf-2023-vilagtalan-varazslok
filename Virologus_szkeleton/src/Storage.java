
public abstract class Storage extends Field{
	//Jelzi, működik-e a raktár
	protected boolean active;
	//Konstruktor
	public Storage() {
		active=true;
	}
	//Elpusztul a raktár (működőképesség falg hamis)
	public void destroyed() {
		active=false;
	}
	//Visszaadja a működőképességet
	// @return - a működőképesség
	public boolean getActive() {
		return active;
	}
}

