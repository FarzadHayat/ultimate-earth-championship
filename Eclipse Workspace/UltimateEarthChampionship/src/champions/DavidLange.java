package champions;

import model.Champion;

/**
 * Represents the David Lange champion.
 */
public class DavidLange extends Champion {

	// Name
	private static String name = "David Lange";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = 1;
	private static int offenseBoost = 0;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new David Lange object with default attribute values.
	 */
	public DavidLange() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
