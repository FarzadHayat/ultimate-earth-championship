package champions;

import model.Champion;

/**
 * Represents the Bernard Montgomery champion.
 */
public class BernardMontgomery extends Champion {

	// Name
	public static final String name = "Bernard Montgomery";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = -1;
	public static final int offenseBoost = 1;
	public static final int defenseBoost = 2;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Bernard Montgomery object with default attribute values.
	 */
	public BernardMontgomery() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
