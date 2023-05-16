package champions;

import model.Champion;

/**
 * Represents the George Washington champion.
 */
public class GeorgeWashington extends Champion {

	// Name
	private static String name = "George Washington";

	// Stat Boosts
	private static int healthBoost = 1;
	private static int staminaBoost = -2;
	private static int offenseBoost = 1;
	private static int defenseBoost = 1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new George Washington object with default attribute values.
	 */
	public GeorgeWashington() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
