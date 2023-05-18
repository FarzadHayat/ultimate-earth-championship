package champions;

import model.Champion;

/**
 * Represents the Augustus Caesar champion.
 */
public class AugustusCaesar extends Champion {

	// Name
	public static final String name = "Augustus Caesar";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = 0;
	public static final int offenseBoost = 1;
	public static final int defenseBoost = 0;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Augustus Caesar object with default attribute values.
	 */
	public AugustusCaesar() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
