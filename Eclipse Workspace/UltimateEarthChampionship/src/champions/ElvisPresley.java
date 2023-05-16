package champions;

import model.Champion;

/**
 * Represents the Elvis Presley champion.
 */
public class ElvisPresley extends Champion {

	// Name
	private static String name = "Elvis Presley";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = 0;
	private static int offenseBoost = 1;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Elvis Presley object with default attribute values.
	 */
	public ElvisPresley() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
