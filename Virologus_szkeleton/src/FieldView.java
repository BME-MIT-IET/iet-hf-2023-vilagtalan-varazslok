
public class FieldView {
	//A mezõ x koordinátája
	private int x;
	//A mezõ y koordinátája
	private int y;
	//A mezõ azonostíója
	private int id;
	//A mezõ modellbeli megfelelõje
	private Field f;
	
	//Konstruktor
	// @param fi - A modellbeli mezõ
	// @param a - skálázandó x koordináta
	// @param b - skálázandó y koordináta
	// @param ids - azonosító
	public FieldView(Field fi, int a, int b, int ids) {
		x=a /5;
		y=b /5;
		id=ids;
		f = fi;
	}
	
	//Visszaadja az x koordinátát
	// @return - x koordináta
	public int getX() {
		return x;
	}
	//Visszaadja az y koordinátát
	// @return - y koordináta
	public int getY() {
		return y;
	}
	//Visszaadja az azonostíót
	// @return - azonosító
	public int getID() {
		return id;
	}
	//Visszaadja a modellbeli mezõt
	// @return - a mezõ
	public Field getField() {
		return f;
	}
}
