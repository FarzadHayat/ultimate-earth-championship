package champions;

import model.Champion;

/**
 * Represents the Phil Garland champion.
 */
public class PhilGarland extends Champion {

	// Name
	public static String name = "Phil Garland";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = 0;
	private static int offenseBoost = 0;
	private static int defenseBoost = 1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Phil Garland object with default attribute values.
	 */
	public PhilGarland() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
