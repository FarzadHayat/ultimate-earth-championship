package champions;

import model.Champion;

/**
 * Represents the Neil Armstrong champion.
 */
public class NeilArmstrong extends Champion {

	// Name
	private static String name = "Neil Armstrong";

	// Stat Boosts
	private static int healthBoost = 2;
	private static int staminaBoost = 1;
	private static int offenseBoost = -1;
	private static int defenseBoost = -1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Neil Armstrong object with default attribute values.
	 */
	public NeilArmstrong() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
