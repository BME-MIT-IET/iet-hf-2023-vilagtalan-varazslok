package main.java;
public class FieldView {
	//A mez� x koordin�t�ja
	private int x;
	//A mez� y koordin�t�ja
	private int y;
	//A mez� azonost��ja
	private int id;
	//A mez� modellbeli megfelel�je
	private Field f;
	
	//Konstruktor
	// @param fi - A modellbeli mez�
	// @param a - sk�l�zand� x koordin�ta
	// @param b - sk�l�zand� y koordin�ta
	// @param ids - azonos�t�
	public FieldView(Field fi, int a, int b, int ids) {
		x=a /5;
		y=b /5;
		id=ids;
		f = fi;
	}
	
	//Visszaadja az x koordin�t�t
	// @return - x koordin�ta
	public int getX() {
		return x;
	}
	//Visszaadja az y koordin�t�t
	// @return - y koordin�ta
	public int getY() {
		return y;
	}
	//Visszaadja az azonost��t
	// @return - azonos�t�
	public int getID() {
		return id;
	}
	//Visszaadja a modellbeli mez�t
	// @return - a mez�
	public Field getField() {
		return f;
	}
}
