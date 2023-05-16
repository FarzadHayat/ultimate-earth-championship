package champions;

import model.Champion;

/**
 * Represents the Simon Hoermann champion.
 */
public class SimonHoermann extends Champion {

	// Name
	private static String name = "Simon Hoermann";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = 0;
	private static int offenseBoost = 0;
	private static int defenseBoost = 1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Simon Hoermann object with default attribute values.
	 */
	public SimonHoermann() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
