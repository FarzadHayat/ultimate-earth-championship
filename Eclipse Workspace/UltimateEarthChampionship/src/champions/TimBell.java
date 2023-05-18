package champions;

import model.Champion;

/**
 * Represents the Tim Bell champion.
 */
public class TimBell extends Champion {

	// Name
	private static final String name = "Tim Bell";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = 0;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 0;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Tim Bell object with default attribute values.
	 */
	public TimBell() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
