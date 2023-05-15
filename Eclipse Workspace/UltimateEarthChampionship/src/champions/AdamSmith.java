package champions;

import model.Champion;

/**
 * Represents the Adam Smith champion.
 */
public class AdamSmith extends Champion {

	// Name
	public static String name = "Adam Smith";

	// Stat Boosts
	private static int healthBoost = -1;
	private static int staminaBoost = 1;
	private static int offenseBoost = 1;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Adam Smith object with default attribute values.
	 */
	public AdamSmith() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
