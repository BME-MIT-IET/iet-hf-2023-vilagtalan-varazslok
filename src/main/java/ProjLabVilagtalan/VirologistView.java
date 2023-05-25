package ProjLabVilagtalan;
import java.awt.Color;

public class VirologistView {
	//A virol�gus neve
	private String name;
	//A virol�gus sz�ne
	private Color color;
	//A virol�gus offsetj�nek x koordin�t�ja
	private int offX;
	//A virol�gus offsetj�nek y koordin�t�ja
	private int offY;
	//A virol�gus modellbeli p�rja
	private Virologist virologist;
	//Konstruktor
	// @param N - a virol�gus neve
	// @param C - a virol�gus sz�ne
	// @param X - a virol�gus offsetje (x)
	// @param Y - a virol�gus offsetje (y)
	// @param V - a virol�gus modellbeli p�rja
	public VirologistView(String N, Color C, int X, int Y, Virologist V) {
		name = N;
		color = C;
		offX = X;
		offY = Y;
		virologist = V;
	}
	
	//Visszaadja a virol�gus modellbeli p�rj�t
	// @return - a virol�gus modellbeli p�rja
	public Virologist getVirologist() {
		return virologist;
	}
	//Visszaadja a virol�gus nev�t
	// @return - a virol�gus neve
	public String getName() {
		return name;
	}
	//Visszaadja a virol�gus sz�n�t
	// @return - a virol�gus sz�ne
	public Color getColor() {
		return color;
	}
	//Visszaadja a virol�gus x szerinti offsetj�t
	// @return - a virol�gus x szerinti offsetje
	public int getX() {
		return offX;
	}
	//Visszaadja a virol�gus y szerinti offsetj�t
	// @return - a virol�gus y szerinti offsetje
	public int getY() {
		return offY;
	}
	
}
