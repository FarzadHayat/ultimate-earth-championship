package champions;

import model.Champion;

/**
 * Represents the Augustus Caesar champion.
 */
public class AugustusCaesar extends Champion {

	// Name
	private static final String name = "Augustus Caesar";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = 0;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 0;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Augustus Caesar object with default attribute values.
	 */
	public AugustusCaesar() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
