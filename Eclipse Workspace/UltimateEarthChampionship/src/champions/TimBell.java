package champions;

import model.Champion;

/**
 * Represents the Tim Bell champion.
 */
public class TimBell extends Champion {

	// Name
	private static String name = "Tim Bell";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = 0;
	private static int offenseBoost = 1;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Tim Bell object with default attribute values.
	 */
	public TimBell() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
