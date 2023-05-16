package champions;

import model.Champion;

/**
 * Represents the Sun Tzu champion.
 */
public class SunTzu extends Champion {

	// Name
	public static String name = "Sun Tzu";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = -1;
	private static int offenseBoost = 1;
	private static int defenseBoost = 1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Sun Tzu object with default attribute values.
	 */
	public SunTzu() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
