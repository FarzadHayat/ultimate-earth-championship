package champions;

import model.Champion;

/**
 * Represents the Marie Curie champion.
 */
public class MarieCurie extends Champion {

	// Name
	private static String name = "Marie Curie";

	// Stat Boosts
	private static int healthBoost = 1;
	private static int staminaBoost = 0;
	private static int offenseBoost = 0;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Marie Curie object with default attribute values.
	 */
	public MarieCurie() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
