package weapons;

import model.Weapon;

/**
 * Represents a Shuriken weapon.
 */
public class Shuriken extends Weapon {
	
	// Name
	private static final String name = "Shuriken";
	
	// Stat Boosts
	private static final int damageMultiplier = 1;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 1;
	
	// Price
	private static final float startingPrice = 10f;
	private static final float priceChangeWeekly = 0.9f;
	
	/**
     * Creates a new Shuriken object with default attribute values.
     */
	public Shuriken() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}
	
}
