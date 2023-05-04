package weapons;

import model.Weapon;

/**
 * Represents a Pitchfork weapon.
 */
public class Pitchfork extends Weapon {
	
	// Name
	private static final String name = "Pitchfork";
	
	// Stat Boosts
	private static final float damageMultiplier = 1f;
	private static final int offenseBoost = 3;
	private static final int defenseBoost = 1;
	
	// Price
	private static final float startingPrice = 25f;
	private static final float priceChangeWeekly = 0.9f;
	
	/**
     * Creates a new Pitchfork object with default attribute values.
     */
	public Pitchfork() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}
	
}
