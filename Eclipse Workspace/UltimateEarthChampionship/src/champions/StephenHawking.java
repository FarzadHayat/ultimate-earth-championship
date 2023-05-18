package champions;

import model.Champion;

/**
 * Represents the Stephen Hawking champion.
 */
public class StephenHawking extends Champion {

	// Name
	public static final String name = "Stephen Hawking";

	// Stat Boosts
	public static final int healthBoost = 1;
	public static final int staminaBoost = -1;
	public static final int offenseBoost = 0;
	public static final int defenseBoost = 1;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Stephen Hawking object with default attribute values.
	 */
	public StephenHawking() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
