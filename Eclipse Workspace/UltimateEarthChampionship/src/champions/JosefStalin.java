package champions;

import model.Champion;

/**
 * Represents the Josef Stalin champion.
 */
public class JosefStalin extends Champion {

	// Name
	private static String name = "Josef Stalin";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = -3;
	private static int offenseBoost = 2;
	private static int defenseBoost = 2;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Josef Stalin object with default attribute values.
	 */
	public JosefStalin() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
