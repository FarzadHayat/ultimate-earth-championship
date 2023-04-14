package weapon.weapons;

import weapon.Weapon;

/**
 * Represents a Shield weapon.
 */
public class Pickaxe extends Weapon {
	
	// Name
	private static final String name = "Pickaxe";
	
	// Stat Boosts
	private static final int damageBoost = 1;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 1;
	
	// Price
	private static final float startingPrice = 10f;
	private static final float priceChangeWeekly = 0.9f;
	
	// Image
	private static final String imageFileName = "";
	
	/**
     * Creates a new Shield object with default attribute values.
     */
	public Pickaxe() {
		super(name, damageBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, imageFileName);
	}
	
}
