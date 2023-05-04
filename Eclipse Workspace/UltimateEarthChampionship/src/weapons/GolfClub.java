package weapons;

import model.Weapon;

/**
 * Represents a Golf Club weapon.
 */
public class GolfClub extends Weapon {
	
	// Name
	private static final String name = "Golf Club";
	
	// Stat Boosts
	private static final float damageMultiplier = 1.25f;
	private static final int offenseBoost = 3;
	private static final int defenseBoost = 0;
	
	// Price
	private static final float startingPrice = 25f;
	private static final float priceChangeWeekly = 0.9f;
	
	/**
     * Creates a new Golf Club object with default attribute values.
     */
	public GolfClub() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}
	
}
