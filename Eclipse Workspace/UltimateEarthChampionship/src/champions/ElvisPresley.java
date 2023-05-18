package champions;

import model.Champion;

/**
 * Represents the Elvis Presley champion.
 */
public class ElvisPresley extends Champion {

	// Name
	public static final String name = "Elvis Presley";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = 0;
	public static final int offenseBoost = 1;
	public static final int defenseBoost = 0;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Elvis Presley object with default attribute values.
	 */
	public ElvisPresley() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
