package main.java;
import java.util.Random;

public class Cape extends Item{
	
	/**Ctor
	 * */
	public Cape() {
	}
	
	/**Akkor hivodik meg, ha megtamadtak viselojet.
	 * 
	 * @param v - Virologus akirol levesszuk
	 * @return - megakadalyozza-e a tamadast
	 * */
	public boolean attackEffect(Virologist attacker, Virologist attacked, Virus a) {
		//TODO 	Random generator (fifty-fifty) (82.3%)
		int result = new Random().nextInt(1001);
		
		if(result!=0 && result < 823) {
			Timer.instance().removeSteppable(a);
			return true;
		}
		return false;
	}
}
