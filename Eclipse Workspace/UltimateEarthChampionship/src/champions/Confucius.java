package champions;

import model.Champion;

/**
 * Represents the Confucius champion.
 */
public class Confucius extends Champion {

	// Name
	private static String name = "Confucius";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = 0;
	private static int offenseBoost = -1;
	private static int defenseBoost = 2;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Confucius object with default attribute values.
	 */
	public Confucius() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
