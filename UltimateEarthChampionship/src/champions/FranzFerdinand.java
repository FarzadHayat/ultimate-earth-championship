package champions;

import model.Champion;

/**
 * Represents the Franz Ferdinand champion.
 */
public class FranzFerdinand extends Champion {

	// Name
	private static final String name = "Franz Ferdinand";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = 1;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 0;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Franz Ferdinand object with default attribute values.
	 */
	public FranzFerdinand() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
