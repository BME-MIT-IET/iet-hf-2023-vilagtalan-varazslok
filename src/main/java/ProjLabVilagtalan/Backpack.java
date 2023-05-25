package ProjLabVilagtalan;
public class Backpack extends Item{
	
	/**Ctor
	 * */
	public Backpack() {
	}
	
	/**Felvetelkor hivodik meg. Megnoveli a maximalis anyagmennyiseget 50-nel.
	 * 
	 * @param v - Virologus akirol levesszuk
	 * */
	public void equip(Virologist v) {
		v.setMaxMaterial(v.getMaxMaterial()+50);
	}
	
	/**Levetelkor hivodik meg. Lecsokkenti a maximalis anyagmennyiseget 50-nel
	 * 
	 * @param v - Virologus akirol levesszuk
	 * */
	public void unequip(Virologist v) {
		v.setMaxMaterial(v.getMaxMaterial()-50);
	}

}