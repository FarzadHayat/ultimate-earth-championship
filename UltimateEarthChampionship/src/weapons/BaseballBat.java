package weapons;

import model.Weapon;

/**
 * Represents a Baseball Bat weapon.
 */
public class BaseballBat extends Weapon {

	// Name
	private static final String name = "Baseball Bat";

	// Stat Boosts
	private static final float damageMultiplier = 1.2f;
	private static final int offenseBoost = 4;
	private static final int defenseBoost = 1;

	// Price
	private static final float startingPrice = 25f;
	private static final float priceChangeWeekly = 0.9f;

	/**
	 * Creates a new Baseball Bat object with default attribute values.
	 */
	public BaseballBat() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}

}
