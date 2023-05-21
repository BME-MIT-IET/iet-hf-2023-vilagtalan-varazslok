package main.java;
import java.util.ArrayList;

public class Laboratory extends Field {
	private ArrayList<Agent>agents=new ArrayList<Agent>();
	
	//Laborat�rium konstruktor
	public Laboratory() {
		
	}
	// �genseket hozz�adja a mez�h�z
	// @param a1 - egyik �gens
	// @param a2 - m�sik �gens
	
	public void addAgents(ArrayList<Agent> ags) {
		for(Agent a : ags) {
			agents.add(a);	
		}
	}
	// �gens tanul�s
	// @param v -  A virol�gus aki megtanulja az �genseket
	public void pickedUp(Virologist v) {	
		ArrayList<Agent> ags =new ArrayList<Agent>();
		for(Agent a : agents) {
			if(a.getClass() != Bear.class) {
				ags.add(a);
			}
		}
		v.addKnownAgents(ags);
	}
	
	//Akci�, amikor r�l�pnek a labor�t�riumra. Medv�vel fert�zi a r�l�p�ket, ha van n�la.
	// @param v - a virol�gus, aki a mez�re l�pett
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
	
	//Visszaadja a mez�n l�v� virol�gusokat
	// @return - a mez�n �ll� virol�gusok list�ja
	public ArrayList<Agent> getAgents(){
		return agents;
	}
}

