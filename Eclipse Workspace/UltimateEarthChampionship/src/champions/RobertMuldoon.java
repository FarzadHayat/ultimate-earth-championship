package champions;

import model.Champion;

/**
 * Represents the Robert Muldoon champion.
 */
public class RobertMuldoon extends Champion {

	// Name
	private static String name = "Robert Muldoon";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = 1;
	private static int offenseBoost = 0;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Robert Muldoon object with default attribute values.
	 */
	public RobertMuldoon() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
