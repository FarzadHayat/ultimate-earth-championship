package champions;

import model.Champion;

/**
 * Represents the John F Kennedy champion.
 */
public class JohnFKennedy extends Champion {

	// Name
	private static String name = "John F Kennedy";

	// Stat Boosts
	private static int healthBoost = -2;
	private static int staminaBoost = 2;
	private static int offenseBoost = 1;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new John F Kennedy object with default attribute values.
	 */
	public JohnFKennedy() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
