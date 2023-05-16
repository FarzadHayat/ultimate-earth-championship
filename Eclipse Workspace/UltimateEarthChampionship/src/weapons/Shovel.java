package weapons;

import model.Weapon;

/**
 * Represents a Shovel weapon.
 */
public class Shovel extends Weapon {

	// Name
	private static final String name = "Shovel";

	// Stat Boosts
	private static final float damageMultiplier = 1f;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 2;

	// Price
	private static final float startingPrice = 25f;
	private static final float priceChangeWeekly = 0.9f;

	/**
	 * Creates a new Shovel object with default attribute values.
	 */
	public Shovel() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}

}
