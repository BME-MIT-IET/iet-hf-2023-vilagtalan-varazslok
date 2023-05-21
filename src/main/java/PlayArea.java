package main.java;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class PlayArea extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//A h�tt�r
	private Image background;
	//A p�lya k�pe
	private Image mapImage;
	//View-k list�i
	private ArrayList<FieldView> fieldViews;
	private ArrayList<VirologistView> virologistViews;
	
	//Konstruktor
	public PlayArea() {
		this.setPreferredSize(new Dimension(700, 700));
		
		fieldViews = new ArrayList<FieldView>();
		virologistViews = new ArrayList<VirologistView>();
		
	}
	
	//Komponens kirajzol�sa
	// @param g - a Graphics objektum, amire rajzol
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
	
	//Rajzol�f�ggv�ny
	//A p�ly�ra annak aktu�lis �llapot�t rajzolja ki
	public void draw() {
		background = new BufferedImage(620, 640, BufferedImage.TYPE_INT_RGB);
		Graphics g = background.getGraphics();
		g.drawImage(mapImage, 0, 0, Color.white, null);
		
		FieldView currentF = null;
		//Virol�gusok (ill. medv�k) kirajzol�sa
		for(int i = 0; i < fieldViews.size(); i++) {
			currentF = fieldViews.get(i);
			
			ArrayList<Virologist> vs = currentF.getField().getVirologists();
			VirologistView currentV = null;
			for(int j = 0; j < virologistViews.size(); j++) {
				currentV = virologistViews.get(j);
				if(vs.contains(currentV.getVirologist())) {
					ArrayList<Virus> virs = currentV.getVirologist().getActiveViruses();
					boolean isBear = false;
					for(int k = 0; k < virs.size(); k++) {
						if(virs.get(k) instanceof Bear) {
							isBear = true;
							break;
						}
					}
					g.setColor(currentV.getColor());
					if(isBear) {
						g.fillRect(currentF.getX()+currentV.getX(), currentF.getY()+currentV.getY(), 8, 8);
					}
					else {
						g.fillOval(currentF.getX()+currentV.getX(), currentF.getY()+currentV.getY(), 8, 8);
					}
				}
			}
			//Ha kifogyott az �v�hely
			if(currentF.getField() instanceof Shelter && ((Shelter)currentF.getField()).getItem()!=null) {
				g.setColor(Color.darkGray);
				g.drawRoundRect(currentF.getX(), currentF.getY(), 8, 8, 5, 5);
			}
			//Ha elpusztult az aminosav-rakt�r
			if(currentF.getField() instanceof AminoacidStorage && !((AminoacidStorage)currentF.getField()).getActive()) {
				g.setColor(Color.magenta);
				Font prev = g.getFont();
				g.setFont(new Font(prev.getName(), prev.getStyle(), 50));
				g.drawString("X", currentF.getX()-12, currentF.getY()+12);
				g.setFont(prev);
			}
			//Ha elpusztult a nukleotid-rakt�r
			if(currentF.getField() instanceof NucleotideStorage && !((NucleotideStorage)currentF.getField()).getActive()) {
				g.setColor(Color.magenta);
				Font prev = g.getFont();
				g.setFont(new Font(prev.getName(), prev.getStyle(), 50));
				g.drawString("X", currentF.getX()-12, currentF.getY()+14);
				g.setFont(prev);
			}
			//Mez�k azonos�t�inak ki�r�sa
			g.setColor(Color.black);
			g.drawString(""+currentF.getID(), currentF.getX()-7, currentF.getY());
			
		}
		repaint();
	}
	
	//A p�lya megjelen�ts�snek be�ll�t�sai
	// @param f - mez� view-k, amiket haszn�l
	// @param v - virol�gus view-k, amiket haszn�l
	// @param file - f�jl, ahonnan a k�peket olvassa
	public void setup(ArrayList<FieldView> f, ArrayList<VirologistView> v, String file) {
		fieldViews = f;
		virologistViews = v;
		try {
			background = ImageIO.read(new File(file));
			mapImage = ImageIO.read(new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Visszaadja a haszn�lt virol�gusn�zeteket
	// @return - a virol�gusn�zetek list�ja
	public ArrayList<VirologistView> getVirologistViews() {
		return virologistViews;
	}
	//Visszaadja a haszn�lt mez�n�zeteket
	// @return - a mez�n�zetek list�ja
	public ArrayList<FieldView> getFieldViews() {
		return fieldViews;
	}
}
