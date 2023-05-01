package weapons;

import model.Weapon;

/**
 * Represents a Sword weapon.
 */
public class Sword extends Weapon {
	
	// Name
	private static final String name = "Sword";
	
	// Stat Boosts
	private static final int damageMultiplier = 1;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 1;
	
	// Price
	private static final float startingPrice = 10f;
	private static final float priceChangeWeekly = 0.9f;
	
	/**
     * Creates a new Sword object with default attribute values.
     */
	public Sword() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}
	
}
