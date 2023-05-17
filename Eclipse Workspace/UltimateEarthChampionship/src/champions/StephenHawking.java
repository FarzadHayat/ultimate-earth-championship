package champions;

import model.Champion;

/**
 * Represents the Stephen Hawking champion.
 */
public class StephenHawking extends Champion {

	// Name
	private static String name = "Stephen Hawking";

	// Stat Boosts
	private static int healthBoost = 1;
	private static int staminaBoost = -1;
	private static int offenseBoost = 0;
	private static int defenseBoost = 1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Stephen Hawking object with default attribute values.
	 */
	public StephenHawking() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
