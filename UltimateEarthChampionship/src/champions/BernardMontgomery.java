package champions;

import model.Champion;

/**
 * Represents the Bernard Montgomery champion.
 */
public class BernardMontgomery extends Champion {

	// Name
	private static final String name = "Bernard Montgomery";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = -1;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 2;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Bernard Montgomery object with default attribute values.
	 */
	public BernardMontgomery() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
