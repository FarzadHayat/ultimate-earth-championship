package weapons;

import model.Weapon;

/**
 * Represents a Katana weapon.
 */
public class Katana extends Weapon {

	// Name
	private static final String name = "Katana";

	// Stat Boosts
	private static final float damageMultiplier = 3f;
	private static final int offenseBoost = -1;
	private static final int defenseBoost = -1;

	// Price
	private static final float startingPrice = 100f;
	private static final float priceChangeWeekly = 0.9f;

	/**
	 * Creates a new Katana object with default attribute values.
	 */
	public Katana() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}

}
