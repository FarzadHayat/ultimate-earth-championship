package weapons;

import model.Weapon;

/**
 * Represents a Mace weapon.
 */
public class Mace extends Weapon {

	// Name
	private static final String name = "Mace";

	// Stat Boosts
	private static final float damageMultiplier = 1.5f;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 3;

	// Price
	private static final float startingPrice = 25f;
	private static final float priceChangeWeekly = 0.9f;

	/**
	 * Creates a new Mace object with default attribute values.
	 */
	public Mace() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}

}
