package champions;

import model.Champion;

/**
 * Represents the Bernard Montgomery champion.
 */
public class BernardMontgomery extends Champion {

	// Name
	private static String name = "Bernard Montgomery";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = -1;
	private static int offenseBoost = 1;
	private static int defenseBoost = 2;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Bernard Montgomery object with default attribute values.
	 */
	public BernardMontgomery() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
