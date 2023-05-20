import java.awt.Color;

public class VirologistView {
	//A virológus neve
	private String name;
	//A virológus színe
	private Color color;
	//A virológus offsetjének x koordinátája
	private int offX;
	//A virológus offsetjének y koordinátája
	private int offY;
	//A virológus modellbeli párja
	private Virologist virologist;
	//Konstruktor
	// @param N - a virológus neve
	// @param C - a virológus színe
	// @param X - a virológus offsetje (x)
	// @param Y - a virológus offsetje (y)
	// @param V - a virológus modellbeli párja
	public VirologistView(String N, Color C, int X, int Y, Virologist V) {
		name = N;
		color = C;
		offX = X;
		offY = Y;
		virologist = V;
	}
	
	//Visszaadja a virológus modellbeli párját
	// @return - a virológus modellbeli párja
	public Virologist getVirologist() {
		return virologist;
	}
	//Visszaadja a virológus nevét
	// @return - a virológus neve
	public String getName() {
		return name;
	}
	//Visszaadja a virológus színét
	// @return - a virológus színe
	public Color getColor() {
		return color;
	}
	//Visszaadja a virológus x szerinti offsetjét
	// @return - a virológus x szerinti offsetje
	public int getX() {
		return offX;
	}
	//Visszaadja a virológus y szerinti offsetjét
	// @return - a virológus y szerinti offsetje
	public int getY() {
		return offY;
	}
	
}
