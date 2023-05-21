
public abstract class Item {
	
	/**Akkor hivodik meg, ha megtamadtak viselojet.
	 * 
	 * @param v - Virologus akirol levesszuk
	 * @return - megakadalyozza-e a tamadast
	 * */
	public boolean attackEffect(Virologist attacker, Virologist attacked, Virus a) {
		return false;
	}
	
	/**Felvetelkor hivodik meg.
	 * 
	 * @param v - Virologus akirol levesszuk
	 * */
	public void equip(Virologist v) {
	}
	
	/**Levetelkor hivodik meg.
	 * 
	 * @param v - Virologus akirol levesszuk
	 * */
	public void unequip(Virologist v) {
	}
	
	/**Uj mezore lepeskor hivodik meg
	 * 
	 * @param v - Virologus aki lep
	 * */
	public void moveEffect(Virologist v) {	
	}
}
