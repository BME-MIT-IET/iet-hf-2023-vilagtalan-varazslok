import java.util.ArrayList;

public class Laboratory extends Field {
	private ArrayList<Agent>agents=new ArrayList<Agent>();
	
	//Laboratórium konstruktor
	public Laboratory() {
		
	}
	// Ágenseket hozzáadja a mezõhöz
	// @param a1 - egyik ágens
	// @param a2 - másik ágens
	
	public void addAgents(ArrayList<Agent> ags) {
		for(Agent a : ags) {
			agents.add(a);	
		}
	}
	// Ágens tanulás
	// @param v -  A virológus aki megtanulja az ágenseket
	public void pickedUp(Virologist v) {	
		ArrayList<Agent> ags =new ArrayList<Agent>();
		for(Agent a : agents) {
			if(a.getClass() != Bear.class) {
				ags.add(a);
			}
		}
		v.addKnownAgents(ags);
	}
	
	//Akció, amikor rálépnek a laborítóriumra. Medvével fertõzi a rálépõket, ha van nála.
	// @param v - a virológus, aki a mezõre lépett
	public void movedOn(Virologist v) {
		
		for(int i=0;i<agents.size();i++) {
			if(agents.get(i).getClass()==Bear.class) {
				Bear bear=(Bear)agents.get(i).create();
				Timer.instance().addSteppable(bear);
				if(v.gotInfected(null, bear)) {
					BearImmunity bearimmunity=(BearImmunity)(new BearImmunity().create());
					v.gotVaccinated(bearimmunity);
				}
			}
		}
	}
	
	//Visszaadja a mezõn lévõ virológusokat
	// @return - a mezõn álló virológusok listája
	public ArrayList<Agent> getAgents(){
		return agents;
	}
}

