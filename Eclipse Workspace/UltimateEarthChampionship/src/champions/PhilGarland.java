package champions;

import model.Champion;

/**
 * Represents the Phil Garland champion.
 */
public class PhilGarland extends Champion {

	// Name
	public static final String name = "Phil Garland";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = 0;
	public static final int offenseBoost = 0;
	public static final int defenseBoost = 1;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Phil Garland object with default attribute values.
	 */
	public PhilGarland() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
