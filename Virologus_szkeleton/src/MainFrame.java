import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//A kezelõfelület gombjai és combobox-ai, (remélhetõleg) beszédes nevekkel, a játékos akciói kezdeményezhetõk és 
	//paraméterezhetõk velük
	private JButton moveButton;
	private JComboBox<Object> fieldsBox;
	private JButton pickUpButton;
	private JButton stealButton;
	private JComboBox<Object> stealTargets;
	private JButton createAgentButton;
	private JComboBox<Object> createablesBox;
	private JButton useVirusButton;
	private JComboBox<Object> virusTargets;
	private JComboBox<Object> useableViruses;
	private JButton useVaccineButton;
	private JComboBox<Object> vaccineTargets;
	private JComboBox<Object> useableVaccines;
	private JButton dropItemButton;
	private JComboBox<Object> droppableItems;
	private JButton skipButton;
	
	//Az állapotok megjelenítésére szolgáló label-ek
	private JLabel lName;
	private JLabel lField;
	private JLabel lMaxMat;
	private JLabel lAmino;
	private JLabel lNucleo;
	private JLabel lKnown;
	private JLabel lCreated;
	private JLabel lViruses;
	private JLabel lVaccines;
	private JLabel lItems;
	private JLabel lActions;
	
	//Az új játék menüpontja
	private JMenuItem newGame;
	
	//A játéktér
	private PlayArea playArea;
	//A játékot irányító kontroller
	private Control controller;
	
	//Frissíti a combobox tartalmát
	// @param box - a frissítendõ comboBox
	// @param list - a lista, amivel feltültésre kerül
	private void comboBoxRefresher(JComboBox<Object> box, List<Object> list) {
		DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<>();
        for(Object iter : list) {
            model.addElement((Object)iter);
        }
        box.setModel(model);
	}
	
	//Konstruktor. A megjelenítést inicializálja.
	public MainFrame() {
		//Alapvetõ megjelenítés
		this.setVisible(true);
        this.setTitle("Game");
        this.setSize(700, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
        
        controller = new Control(this);
        
        //Layoutok kiosztása
		setLayout(new BorderLayout());
		playArea = new PlayArea();
		add(playArea, BorderLayout.CENTER);
		JPanel left = new JPanel();
		add(left, BorderLayout.WEST);
		JPanel right = new JPanel();
		right.setVisible(true);
		add(right, BorderLayout.EAST);
		
		//menü feltöltése
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		newGame = new JMenuItem("New game");
		newGame.addActionListener(new newGameActionListener());
		menu.add(newGame);
		
		//Kiírások kezelése, bal oldal beállítása, feltöltése
		left.setPreferredSize(new Dimension(450, 700));
		left.setLayout(new GridLayout(11, 1));
		lName = new JLabel(" Name: ");
		lField = new JLabel(" Field: ");
		lMaxMat = new JLabel(" Maximum material: ");
		lAmino = new JLabel(" Aminoacid: ");
		lNucleo = new JLabel(" Nucleotide: ");
		lKnown = new JLabel(" Known agents:\n ");
		lCreated = new JLabel(" Created agents:\n ");
		lViruses = new JLabel(" Active viruses:\n ");
		lVaccines = new JLabel(" Active vaccines:\n ");
		lItems = new JLabel(" Items:\n ");
		lActions = new JLabel(" Actions left: ");
		left.add(lName);
		left.add(lField);
		left.add(lMaxMat);
		left.add(lAmino);
		left.add(lNucleo);
		left.add(lKnown);
		left.add(lCreated);
		left.add(lViruses);
		left.add(lVaccines);
		left.add(lItems);
		left.add(lActions);
		
		//A jobb oldal feltöltése
		right.setLayout(new GridLayout(8, 1));
		JPanel pMove = new JPanel();
		JPanel pPickUp = new JPanel();
		JPanel pSteal = new JPanel();
		JPanel pCreate = new JPanel();
		JPanel pVirus = new JPanel();
		JPanel pVaccine = new JPanel();
		JPanel pDrop = new JPanel();
		JPanel pSkip = new JPanel();
		right.add(pMove);
		right.add(pPickUp);
		right.add(pSteal);
		right.add(pCreate);
		right.add(pVirus);
		right.add(pVaccine);
		right.add(pDrop);
		right.add(pSkip);
		
		//Mozgásgomb és a paraméterezõ comboBox hozzáadása, eseménykezelõ regisztrálása
		moveButton = new JButton("Move");
		Object[] os = {};
		fieldsBox = new JComboBox<>(os);
		pMove.add(fieldsBox);
		pMove.add(moveButton);
		moveButton.addActionListener(new moveButtonActionListener());
		
		//felvevõgomb hozzáadása, eseménykezelõ regisztrálása
		pickUpButton = new JButton("Pick up");
		pPickUp.add(pickUpButton);
		pickUpButton.addActionListener(new pickUpButtonActionListener());
		
		//Lopásgomb és a paraméterezõ comboBox hozzáadása, eseménykezelõ regisztrálása
		stealButton = new JButton("Steal");
		Object[] os2 = {};
		stealTargets = new JComboBox<>(os2);
		pSteal.add(stealTargets);
		pSteal.add(stealButton);
		stealButton.addActionListener(new stealButtonActionListener());
		
		//Ágensgyártó gomb és a paraméterezõ comboBox hozzáadása, eseménykezelõ regisztrálása
		createAgentButton = new JButton("Create agent");
		Object[] os3 = {};
		createablesBox = new JComboBox<>(os3);
		pCreate.add(createablesBox);
		pCreate.add(createAgentButton);
		createAgentButton.addActionListener(new createAgentButtonActionListener());
		
		//Víruskenõ gomb és a paraméterezõ comboBoxok hozzáadása, eseménykezelõ regisztrálása
		useVirusButton = new JButton("Use virus");
		Object[] os4 = {};
		Object[] os5 = {};
		virusTargets= new JComboBox<>(os4);
		useableViruses = new JComboBox<>(os5);
		pVirus.add(virusTargets);
		pVirus.add(useableViruses);
		pVirus.add(useVirusButton);
		useVirusButton.addActionListener(new useVirusButtonActionListener());
		
		//Víruskenõ gomb és a paraméterezõ comboBoxok hozzáadása, eseménykezelõ regisztrálása
		useVaccineButton = new JButton("Use vaccine");
		Object[] os6 = {};
		Object[] os7 = {};
		vaccineTargets = new JComboBox<>(os6);
		useableVaccines = new JComboBox<>(os7);
		pVaccine.add(vaccineTargets);
		pVaccine.add(useableVaccines);
		pVaccine.add(useVaccineButton);
		useVaccineButton.addActionListener(new useVaccineButtonActionListener());
		
		//VTárgyeldobó gomb és a paraméterezõ comboBox hozzáadása, eseménykezelõ regisztrálása
		dropItemButton = new JButton("Drop item");
		Object[] os8 = {};
		droppableItems = new JComboBox<>(os8);
		pDrop.add(droppableItems);
		pDrop.add(dropItemButton);
		dropItemButton.addActionListener(new dropItemButtonActionListener());
		
		//Kör vége gomb hozzáadása, eseménykezelõ regisztrálása
		skipButton = new JButton("Skip turn");
		pSkip.add(skipButton);
		skipButton.addActionListener(new skipButtonActionListener());
		
		this.pack();
	}
	
	//Rajzolófüggvény. A játék aktuális állása szerint kitölti a paraméterezõ comboBoxokat
	//az aktuális elemekkel, amelyeket lekér a modellbõl.
	public void draw() {
		Virologist v = controller.getCurrentVirologist();
		Field f = v.getField();
		ArrayList<Field> fs = f.getNeighbours();
		ArrayList<FieldView> fvs = playArea.getFieldViews();
		ArrayList<Object> temp = new ArrayList<Object>();
		for(int i = 0; i < fvs.size(); i++) {
			if(fs.contains(fvs.get(i).getField())) {
				temp.add(fvs.get(i).getID());
			}
		}
		comboBoxRefresher(fieldsBox, temp);
		
		temp = new ArrayList<Object>();
		ArrayList<Virologist> targets = f.getVirologists();
		ArrayList<VirologistView> vvs = playArea.getVirologistViews();
		for(int i = 0; i < targets.size(); i++) {
			for(int j = 0; j < vvs.size(); j++) {
				if(targets.get(i) == vvs.get(j).getVirologist() && targets.get(i)!=v) {
					temp.add(vvs.get(j).getName());
					break;
				}
			}
		}
		comboBoxRefresher(stealTargets, temp);
		comboBoxRefresher(virusTargets, temp);
		for(int i = 0; i < vvs.size(); i++) {
			if(v == vvs.get(i).getVirologist()) {
				temp.add(vvs.get(i).getName());
			}
		}
		comboBoxRefresher(vaccineTargets, temp);
		
		temp = new ArrayList<Object>();
		ArrayList<Agent> as = v.getAgentsKnown();
		for(int i = 0; i < as.size(); i++) {
			if(as.get(i) instanceof Chorea) {
				temp.add("Chorea");
			}
			if(as.get(i) instanceof Forget) {
				temp.add("Forget");
			}
			if(as.get(i) instanceof Stun) {
				temp.add("Stun");
			}
			if(as.get(i) instanceof AntiChorea) {
				temp.add("AntiChorea");
			}
			if(as.get(i) instanceof AntiForget) {
				temp.add("AntiForget");
			}
			if(as.get(i) instanceof AntiStun) {
				temp.add("AntiStun");
			}
		}
		comboBoxRefresher(createablesBox, temp);
		
		temp = new ArrayList<Object>();
		ArrayList<Agent> acs = v.getAgentsCreated();
		for(int i = 0; i < acs.size(); i++) {
			if(acs.get(i) instanceof Chorea) {
				temp.add("Chorea");
			}
			if(acs.get(i) instanceof Forget) {
				temp.add("Forget");
			}
			if(acs.get(i) instanceof Stun) {
				temp.add("Stun");
			}
		}
		comboBoxRefresher(useableViruses, temp);
		
		temp = new ArrayList<Object>();
		for(int i = 0; i < acs.size(); i++) {
			if(acs.get(i) instanceof AntiChorea) {
				temp.add("AntiChorea");
			}
			if(acs.get(i) instanceof AntiForget) {
				temp.add("AntiForget");
			}
			if(acs.get(i) instanceof AntiStun) {
				temp.add("AntiStun");
			}
		}
		comboBoxRefresher(useableVaccines, temp);
		
		temp = new ArrayList<Object>();
		ArrayList<Item> is = v.getItems();
		for(int i = 0; i < is.size(); i++) {
			if(is.get(i) instanceof Axe) {
				temp.add("Axe");
			}
			if(is.get(i) instanceof Cape) {
				temp.add("Cape");
			}
			if(is.get(i) instanceof Gloves) {
				temp.add("Gloves");
			}
			if(is.get(i) instanceof Backpack) {
				temp.add("Backpack");
			}
		}
		comboBoxRefresher(droppableItems, temp);
		
		VirologistView vv = null;
		for(int i = 0; i < vvs.size(); i++) {
			if(vvs.get(i).getVirologist() == v) {
				vv = vvs.get(i);
			}
		}
		lName.setText(" Name: "+vv.getName());
		
		FieldView fv = null;
		for(int i = 0; i < fvs.size(); i++) {
			if(fvs.get(i).getField() == f) {
				fv = fvs.get(i);
			}
		}
		lField.setText(" Field: "+fv.getID());
		
		lMaxMat.setText(" Maximum material: "+v.getMaxMaterial());
		lAmino.setText(" Aminoacid: "+v.getAminoacid());
		lNucleo.setText(" Nucleotide: "+v.getNucleotide());
		
		String known = " Known agents:\n ";
		for(int i = 0; i < as.size(); i++) {
			if(as.get(i) instanceof Chorea) {
				known += "Chorea";
			}
			if(as.get(i) instanceof Forget) {
				known += "Forget";
			}
			if(as.get(i) instanceof Stun) {
				known += "Stun";
			}
			if(as.get(i) instanceof AntiChorea) {
				known += "AntiChorea";
			}
			if(as.get(i) instanceof AntiForget) {
				known += "AntiForget";
			}
			if(as.get(i) instanceof AntiStun) {
				known += "AntiStun";
			}
			if(i < as.size()-1) {
				known += ", ";
			}
		}
		lKnown.setText(known);
		
		String created = " Created agents:\n ";
		for(int i = 0; i < acs.size(); i++) {
			if(acs.get(i) instanceof Chorea) {
				created += "Chorea-"+acs.get(i).getTTL();
			}
			if(acs.get(i) instanceof Forget) {
				created += "Forget-"+acs.get(i).getTTL();;
			}
			if(acs.get(i) instanceof Stun) {
				created += "Stun-"+acs.get(i).getTTL();;
			}
			if(acs.get(i) instanceof AntiChorea) {
				created += "AntiChorea-"+acs.get(i).getTTL();;
			}
			if(acs.get(i) instanceof AntiForget) {
				created += "AntiForget-"+acs.get(i).getTTL();;
			}
			if(acs.get(i) instanceof AntiStun) {
				created += "AntiStun-"+acs.get(i).getTTL();;
			}
			if(i < acs.size()-1) {
				created += ", ";
			}
		}
		lCreated.setText(created);
		
		String viruses = " Active viruses:\n ";
		ArrayList<Virus> virs = v.getActiveViruses();
		for(int i = 0; i < virs.size(); i++) {
			if(virs.get(i) instanceof Chorea) {
				viruses += "Chorea-"+virs.get(i).getTimeLeft();
			}
			if(virs.get(i) instanceof Forget) {
				viruses += "Forget-"+virs.get(i).getTimeLeft();
			}
			if(virs.get(i) instanceof Stun) {
				viruses += "Stun-"+virs.get(i).getTimeLeft();
			}
			if(virs.get(i) instanceof Bear) {
				viruses += "Bear";
			}
			if(i < virs.size()-1) {
				viruses += ", ";
			}
		}
		lViruses.setText(viruses);
		
		String vaccines = " Active vaccines:\n ";
		ArrayList<Vaccine> vacs = v.getActiveVaccines();
		for(int i = 0; i < vacs.size(); i++) {
			if(vacs.get(i) instanceof AntiChorea) {
				vaccines += "AntiChorea-"+vacs.get(i).getTimeLeft();
			}
			if(vacs.get(i) instanceof AntiForget) {
				vaccines += "AntiForget-"+vacs.get(i).getTimeLeft();
			}
			if(vacs.get(i) instanceof AntiStun) {
				vaccines += "AntiStun-"+vacs.get(i).getTimeLeft();
			}
			if(vacs.get(i) instanceof BearImmunity) {
				vaccines += "BearImmunity";
			}
			if(i < vacs.size()-1) {
				vaccines += ", ";
			}
		}
		lVaccines.setText(vaccines);
		
		String items = " Items:\n ";
		for(int i = 0; i < is.size(); i++) {
			if(is.get(i) instanceof Axe) {
				items += "Axe-";
				if(((Axe)is.get(i)).getActive()) {
					items += "sharp";
				}
				else {
					items += "dull";
				}
			}
			if(is.get(i) instanceof Cape) {
				items += "Cape";
			}
			if(is.get(i) instanceof Gloves) {
				items += "Gloves-"+((Gloves)is.get(i)).getDurability();
			}
			if(is.get(i) instanceof Backpack) {
				items += "Backpack";
			}
			if(i < is.size()-1) {
				items += ", ";
			}
		}
		lItems.setText(items);
		
		lActions.setText(" Actions left: "+controller.getActionsLeft());
		//Pálya kirajzolása
		playArea.draw();
	}
	
	//A játék végét kezeli. Kiiírja a nyertest, lezárja a gombokat.
	public void endGame() {
		
		moveButton.setEnabled(false);
		fieldsBox.setEnabled(false);
		pickUpButton.setEnabled(false);
		stealButton.setEnabled(false);
		stealTargets.setEnabled(false);
		createAgentButton.setEnabled(false);
		createablesBox.setEnabled(false);
		useVirusButton.setEnabled(false);
		virusTargets.setEnabled(false);
		useableViruses.setEnabled(false);
		useVaccineButton.setEnabled(false);
		vaccineTargets.setEnabled(false);
		useableVaccines.setEnabled(false);
		dropItemButton.setEnabled(false);
		droppableItems.setEnabled(false);
		skipButton.setEnabled(false);

        Virologist v = controller.getCurrentVirologist();
        ArrayList<VirologistView> vvs = playArea.getVirologistViews();
        VirologistView vv = null;
		for(int i = 0; i < vvs.size(); i++) {
			if(vvs.get(i).getVirologist() == v) {
				vv = vvs.get(i);
			}
		}
        JOptionPane.showMessageDialog(null, vv.getName()+" won the game! Congratulations!");
	}
	
	//Visszaadja a pályát
	// @return - a pálya, ahova kirajzolásra kerül az a modellbeli pálya
	public PlayArea getPlayArea() {
		return playArea;
	}
	
	//Az új játák indításának eseménykezelõje
	//Az actionPerformed függvényben elkéri a felhasználótól a beállításokat, és új játékot indít.
	private class newGameActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] maps = {"map1", "map2", "map3"};
			Object[] nums = {"1", "2", "3", "4"};
			JComboBox<Object> mapBox = new JComboBox<>(maps);
			JComboBox<Object> numBox = new JComboBox<>(nums);

            JPanel p = new JPanel(new GridLayout(2, 2));
            p.add(new JLabel("Choose a map:"));
            p.add(mapBox);
            p.add(new JLabel("Choose number of players:"));
            p.add(numBox);

            int result = JOptionPane.showConfirmDialog(null, p, "New game", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
            	controller.startGame((String)mapBox.getSelectedItem()+".txt", Integer.parseInt((String)numBox.getSelectedItem()));
            }
        }
	}
	//A mozgás gomb eseménykezelõje
	//Az actionPerformed függvényben kezeli a mozgatást
	private class moveButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int next = (int)fieldsBox.getSelectedItem();
			ArrayList<FieldView> fvs = playArea.getFieldViews();
			for(int i = 0; i < fvs.size(); i++) {
				if(next == fvs.get(i).getID()) {
					controller.move(fvs.get(i).getField());
					return;
				}
			}
        }
	}
	//A felvétel gomb eseménykezelõje
	//Az actionPerformed függvényben kezeli a felvételt
	private class pickUpButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.pickUp();
        }
	}
	//A lopás gomb eseménykezelõje
	//Az actionPerformed függvényben kezeli a lopást
	private class stealButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String target = (String)stealTargets.getSelectedItem();
			ArrayList<VirologistView> vvs = playArea.getVirologistViews();
			for(int i = 0; i < vvs.size(); i++) {
				if(target == vvs.get(i).getName()) {
					controller.steal(vvs.get(i).getVirologist());
					return;
				}
			}
        }
	}
	//Az ágenskészítés gomb eseménykezelõje
	//Az actionPerformed függvényben kezeli az ágenskészítést
	private class createAgentButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String agent = (String)createablesBox.getSelectedItem();
			ArrayList<Agent> as = controller.getCurrentVirologist().getAgentsKnown();
			for(int i = 0; i < as.size(); i++) {
				switch(agent) {
				case "Chorea":
					if(as.get(i) instanceof Chorea) {
						controller.createAgent(as.get(i));
						return;
					}
					break;
				case "Forget":
					if(as.get(i) instanceof Forget) {
						controller.createAgent(as.get(i));
						return;
					}
					break;
				case "Stun":
					if(as.get(i) instanceof Stun) {
						controller.createAgent(as.get(i));
						return;
					}
					break;
				case "AntiChorea":
					if(as.get(i) instanceof AntiChorea) {
						controller.createAgent(as.get(i));
						return;
					}
					break;
				case "AntiForget":
					if(as.get(i) instanceof AntiForget) {
						controller.createAgent(as.get(i));
						return;
					}
					break;
				case "AntiStun":
					if(as.get(i) instanceof AntiStun) {
						controller.createAgent(as.get(i));
						return;
					}
					break;
				default:
					break;
				}
			}
        }
	}
	
	//A vírushasználat gomb eseménykezelõje
	//Az actionPerformed függvényben kezeli a vírushasználatot
	private class useVirusButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String target = (String)virusTargets.getSelectedItem();
			Virologist targ = null;
			ArrayList<VirologistView> vvs = playArea.getVirologistViews();
			for(int i = 0; i < vvs.size(); i++) {
				if(target == vvs.get(i).getName()) {
					targ = vvs.get(i).getVirologist();
					break;
				}
			}
			
			String virus = (String)useableViruses.getSelectedItem();
			ArrayList<Agent> vs = controller.getCurrentVirologist().getAgentsCreated();
			for(int i = 0; i < vs.size(); i++) {
				switch(virus) {
				case "Chorea":
					if(vs.get(i) instanceof Chorea) {
						controller.useVirus(targ, (Virus)vs.get(i));
						return;
					}
					break;
				case "Forget":
					if(vs.get(i) instanceof Forget) {
						controller.useVirus(targ, (Virus)vs.get(i));
						return;
					}
					break;
				case "Stun":
					if(vs.get(i) instanceof Stun) {
						controller.useVirus(targ, (Virus)vs.get(i));
						return;
					}
					break;
				default:
					break;
				}
			}
        }
	}
	//A vakcinahasználat gomb eseménykezelõje
	//Az actionPerformed függvényben kezeli a vakcinahasználatot
	private class useVaccineButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String target = (String)vaccineTargets.getSelectedItem();
			Virologist targ = null;
			ArrayList<VirologistView> vvs = playArea.getVirologistViews();
			for(int i = 0; i < vvs.size(); i++) {
				if(target == vvs.get(i).getName()) {
					targ = vvs.get(i).getVirologist();
					break;
				}
			}
			
			String vaccine = (String)useableVaccines.getSelectedItem();
			ArrayList<Agent> vs = controller.getCurrentVirologist().getAgentsCreated();
			for(int i = 0; i < vs.size(); i++) {
				switch(vaccine) {
				case "AntiChorea":
					if(vs.get(i) instanceof AntiChorea) {
						controller.useVaccine(targ, (Vaccine)vs.get(i));
						return;
					}
					break;
				case "AntiForget":
					if(vs.get(i) instanceof AntiForget) {
						controller.useVaccine(targ, (Vaccine)vs.get(i));
						return;
					}
					break;
				case "AntiStun":
					if(vs.get(i) instanceof AntiStun) {
						controller.useVaccine(targ, (Vaccine)vs.get(i));
						return;
					}
					break;
				default:
					break;
				}
			}
        }
	}
	//A tárygeldobás gomb eseménykezelõje
	//Az actionPerformed függvényben kezeli a tárgya eldobását
	private class dropItemButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String item = (String)droppableItems.getSelectedItem();
			ArrayList<Item> is = controller.getCurrentVirologist().getItems();
			for(int i = 0; i < is.size(); i++) {
				switch(item) {
				case "Axe":
					if(is.get(i) instanceof Axe) {
						controller.dropItem(is.get(i));
						return;
					}
					break;
				case "Cape":
					if(is.get(i) instanceof Cape) {
						controller.dropItem(is.get(i));
						return;
					}
					break;
				case "Gloves":
					if(is.get(i) instanceof Gloves) {
						controller.dropItem(is.get(i));
						return;
					}
					break;
				case "Backpack":
					if(is.get(i) instanceof Backpack) {
						controller.dropItem(is.get(i));
						return;
					}
				default:
					break;
				}
			}
        }
	}
	
	//A skip gomb eseménykezelõje
	//Az actionPerformed függvényben kezeli a kör átadását
	private class skipButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.skipTurn();
        }
	}
}
