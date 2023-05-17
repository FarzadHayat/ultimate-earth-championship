package champions;

import model.Champion;

/**
 * Represents the Augustus Caesar champion.
 */
public class AugustusCaesar extends Champion {

	// Name
	private static String name = "Augustus Caesar";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = 0;
	private static int offenseBoost = 1;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Augustus Caesar object with default attribute values.
	 */
	public AugustusCaesar() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
