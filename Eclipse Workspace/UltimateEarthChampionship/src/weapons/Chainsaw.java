package weapons;

import model.Weapon;

/**
 * Represents a Chainsaw weapon.
 */
public class Chainsaw extends Weapon {
	
	// Name
	private static final String name = "Chainsaw";
	
	// Stat Boosts
	private static final float damageMultiplier = 2.5f;
	private static final int offenseBoost = 2;
	private static final int defenseBoost = 1;
	
	// Price
	private static final float startingPrice = 100f;
	private static final float priceChangeWeekly = 0.9f;
	
	/**
     * Creates a new Chainsaw object with default attribute values.
     */
	public Chainsaw() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}
	
}
