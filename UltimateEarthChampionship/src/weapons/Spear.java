package weapons;

import model.Weapon;

/**
 * Represents a Spear weapon.
 */
public class Spear extends Weapon {

	// Name
	private static final String name = "Spear";

	// Stat Boosts
	private static final float damageMultiplier = 0.75f;
	private static final int offenseBoost = 4;
	private static final int defenseBoost = 3;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 0.9f;

	/**
	 * Creates a new Spear object with default attribute values.
	 */
	public Spear() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}

}
