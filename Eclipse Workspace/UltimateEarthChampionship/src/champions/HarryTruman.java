package champions;

import model.Champion;

/**
 * Represents the Harry Truman champion.
 */
public class HarryTruman extends Champion {

	// Name
	public static String name = "Harry Truman";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = 1;
	private static int offenseBoost = 0;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Harry Truman object with default attribute values.
	 */
	public HarryTruman() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
