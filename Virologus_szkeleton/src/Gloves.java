
public class Gloves extends Item{

	// A kesztyû ellenállóképessége
private int durability;
	
	/**Ctor
	 * */
	public Gloves() {
		durability = 3;
	}
	
	/**Akkor hivodik meg, ha megtamadtak viselojet. Az eredeti idotartam felevel csokken az agens maradek ideje,
	 * ha 0-ra megy, eldobja, ha nem, a tamadora visszadobja.
	 * 
	 * @param v - Virologus akirol levesszuk
	 * @return - megakadalyozza-e a tamadast
	 * */
	public boolean attackEffect(Virologist attacker, Virologist attacked, Virus a) {

        durability--;
        int maxDuration = a.getDuration();
        int timeLeft = a.getTimeLeft();

        int newTimeLeft = timeLeft-maxDuration/2;
        a.setTimeLeft(newTimeLeft);

        if(newTimeLeft <=0) {
            Timer.instance().removeSteppable(a);
        } else {
            if(attacker!=null) {
                attacker.gotInfected(attacked, a);
            }
            else {
            	Timer.instance().removeSteppable(a);
            }
        }

        if(durability==0) {
            attacked.loseItem(this);
        }
        return true;
    }
	
	//Visszaadja a kesztyû ellenállóképességét
	// @return - a kesztyû ellenállóképessége
	public int getDurability() {
		return durability;
	}
}