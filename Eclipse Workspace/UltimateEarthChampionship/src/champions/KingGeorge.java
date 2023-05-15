package champions;

import model.Champion;

/**
 * Represents the King George V champion.
 */
public class KingGeorge extends Champion {

	// Name
	private static String name = "King George V";

	// Stat Boosts
	private static int healthBoost = -1;
	private static int staminaBoost = -1;
	private static int offenseBoost = 0;
	private static int defenseBoost = 3;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new King George V object with default attribute values.
	 */
	public KingGeorge() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
