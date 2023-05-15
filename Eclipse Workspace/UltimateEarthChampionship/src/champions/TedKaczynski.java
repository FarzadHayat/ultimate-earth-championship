package champions;

import model.Champion;

/**
 * Represents the Ted Kaczynski champion.
 */
public class TedKaczynski extends Champion {

	// Name
	public static String name = "Ted Kaczynski";

	// Stat Boosts
	private static int healthBoost = -1;
	private static int staminaBoost = 0;
	private static int offenseBoost = 3;
	private static int defenseBoost = -1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Ted Kaczynski object with default attribute values.
	 */
	public TedKaczynski() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
