package main.java;
public abstract class Storage extends Field{
	//Jelzi, m�k�dik-e a rakt�r
	protected boolean active;
	//Konstruktor
	public Storage() {
		active=true;
	}
	//Elpusztul a rakt�r (m�k�d�k�pess�g falg hamis)
	public void destroyed() {
		active=false;
	}
	//Visszaadja a m�k�d�k�pess�get
	// @return - a m�k�d�k�pess�g
	public boolean getActive() {
		return active;
	}
}

