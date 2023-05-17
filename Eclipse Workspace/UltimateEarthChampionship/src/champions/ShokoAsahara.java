package champions;

import model.Champion;

/**
 * Represents the Shoko Asahara champion.
 */
public class ShokoAsahara extends Champion {

	// Name
	private static String name = "Shoko Asahara";

	// Stat Boosts
	private static int healthBoost = -2;
	private static int staminaBoost = 1;
	private static int offenseBoost = 1;
	private static int defenseBoost = 1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Shoko Asahara object with default attribute values.
	 */
	public ShokoAsahara() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
