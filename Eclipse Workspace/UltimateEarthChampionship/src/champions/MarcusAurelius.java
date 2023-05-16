package champions;

import model.Champion;

/**
 * Represents the Marcus Aurelius champion.
 */
public class MarcusAurelius extends Champion {

	// Name
	public static String name = "Marcus Aurelius";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = -1;
	private static int offenseBoost = 0;
	private static int defenseBoost = 2;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Marcus Aurelius object with default attribute values.
	 */
	public MarcusAurelius() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
