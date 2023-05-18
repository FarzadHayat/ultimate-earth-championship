package champions;

import model.Champion;

/**
 * Represents the King George V champion.
 */
public class KingGeorge extends Champion {

	// Name
	private static final String name = "King George V";

	// Stat Boosts
	private static final int healthBoost = -1;
	private static final int staminaBoost = -1;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 3;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new King George V object with default attribute values.
	 */
	public KingGeorge() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
