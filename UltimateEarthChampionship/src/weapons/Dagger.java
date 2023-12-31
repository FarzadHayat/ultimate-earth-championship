package weapons;

import model.Weapon;

/**
 * Represents a Dagger weapon.
 */
public class Dagger extends Weapon {

	// Name
	private static final String name = "Dagger";

	// Stat Boosts
	private static final float damageMultiplier = 2f;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = -1;

	// Price
	private static final float startingPrice = 100f;
	private static final float priceChangeWeekly = 0.9f;

	/**
	 * Creates a new Dagger object with default attribute values.
	 */
	public Dagger() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}

}
