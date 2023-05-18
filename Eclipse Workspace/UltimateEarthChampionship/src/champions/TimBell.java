package champions;

import model.Champion;

/**
 * Represents the Tim Bell champion.
 */
public class TimBell extends Champion {

	// Name
	public static final String name = "Tim Bell";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = 0;
	public static final int offenseBoost = 1;
	public static final int defenseBoost = 0;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Tim Bell object with default attribute values.
	 */
	public TimBell() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
