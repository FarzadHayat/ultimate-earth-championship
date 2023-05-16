package weapons;

import model.Weapon;

/**
 * Represents a Nunchucks weapon.
 */
public class Nunchucks extends Weapon {

	// Name
	private static final String name = "Nunchucks";

	// Stat Boosts
	private static final float damageMultiplier = 0.8f;
	private static final int offenseBoost = 2;
	private static final int defenseBoost = 2;

	// Price
	private static final float startingPrice = 25f;
	private static final float priceChangeWeekly = 0.9f;

	/**
	 * Creates a new Nunchucks object with default attribute values.
	 */
	public Nunchucks() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}

}
