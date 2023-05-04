package weapons;

import model.Weapon;

/**
 * Represents a Axe weapon.
 */
public class Axe extends Weapon {
	
	// Name
	private static final String name = "Axe";
	
	// Stat Boosts
	private static final float damageMultiplier = 1.5f;
	private static final int offenseBoost = 2;
	private static final int defenseBoost = 3;
	
	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 0.9f;
	
	/**
     * Creates a new Axe object with default attribute values.
     */
	public Axe() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}
	
}
