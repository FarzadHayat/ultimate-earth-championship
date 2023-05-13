package weapons;

import model.Weapon;

/**
 * Represents a Machete weapon.
 */
public class Machete extends Weapon {

	// Name
	private static final String name = "Machete";

	// Stat Boosts
	private static final float damageMultiplier = 2.5f;
	private static final int offenseBoost = 2;
	private static final int defenseBoost = -2;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 0.9f;

	/**
	 * Creates a new Machete object with default attribute values.
	 */
	public Machete() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}

}
