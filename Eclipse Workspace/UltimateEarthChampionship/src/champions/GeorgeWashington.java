package champions;

import model.Champion;

/**
 * Represents the George Washington champion.
 */
public class GeorgeWashington extends Champion {

	// Name
	private static final String name = "George Washington";

	// Stat Boosts
	private static final int healthBoost = 1;
	private static final int staminaBoost = -2;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 1;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new George Washington object with default attribute values.
	 */
	public GeorgeWashington() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
