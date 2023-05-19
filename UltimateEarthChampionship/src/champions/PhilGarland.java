package champions;

import model.Champion;

/**
 * Represents the Phil Garland champion.
 */
public class PhilGarland extends Champion {

	// Name
	private static final String name = "Phil Garland";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = 0;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 1;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Phil Garland object with default attribute values.
	 */
	public PhilGarland() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
