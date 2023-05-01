package weapons;

import model.Weapon;

/**
 * Fists are the default weapon for all champions
 */
public class Fists extends Weapon {
	
	// Name
	private static final String name = "Fists";
	
	// Stat Boosts
	private static final int damageMultiplier = 1;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 0;
	
	// Price
	private static final float startingPrice = 0f;
	private static final float priceChangeWeekly = 1f;
	
	/**
     * Creates a new Fists object with default attribute values.
     */
	public Fists() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, true);
	}
	
}
