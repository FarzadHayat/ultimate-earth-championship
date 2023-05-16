package champions;

import model.Champion;

/**
 * Represents the Philippe Petain champion.
 */
public class PhilippePetain extends Champion {

	// Name
	private static String name = "Philippe Petain";

	// Stat Boosts
	private static int healthBoost = 2;
	private static int staminaBoost = -1;
	private static int offenseBoost = 0;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Philippe Petain object with default attribute values.
	 */
	public PhilippePetain() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
