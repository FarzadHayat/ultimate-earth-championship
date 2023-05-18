package champions;

import model.Champion;

/**
 * Represents the King George V champion.
 */
public class KingGeorge extends Champion {

	// Name
	public static final String name = "King George V";

	// Stat Boosts
	public static final int healthBoost = -1;
	public static final int staminaBoost = -1;
	public static final int offenseBoost = 0;
	public static final int defenseBoost = 3;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new King George V object with default attribute values.
	 */
	public KingGeorge() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
