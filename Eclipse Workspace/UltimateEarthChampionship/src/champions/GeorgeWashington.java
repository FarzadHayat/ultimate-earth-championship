package champions;

import model.Champion;

/**
 * Represents the George Washington champion.
 */
public class GeorgeWashington extends Champion {

	// Name
	public static final String name = "George Washington";

	// Stat Boosts
	public static final int healthBoost = 1;
	public static final int staminaBoost = -2;
	public static final int offenseBoost = 1;
	public static final int defenseBoost = 1;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new George Washington object with default attribute values.
	 */
	public GeorgeWashington() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
