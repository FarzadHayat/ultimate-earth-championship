package weapons;

import model.Weapon;

/**
 * Represents a Shield weapon.
 */
public class Shield extends Weapon {
	
	// Name
	private static final String name = "Shield";
	
	// Stat Boosts
	private static final int damageMultiplier = 1;
	private static final int offenseBoost = -1;
	private static final int defenseBoost = 3;
	
	// Price
	private static final float startingPrice = 10f;
	private static final float priceChangeWeekly = 0.9f;
	
	/**
     * Creates a new Shield object with default attribute values.
     */
	public Shield() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}
	
}
