package champions;

import model.Champion;

/**
 * Represents the Stephen Hawking champion.
 */
public class StephenHawking extends Champion {

	// Name
	private static final String name = "Stephen Hawking";

	// Stat Boosts
	private static final int healthBoost = 1;
	private static final int staminaBoost = -1;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 1;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Stephen Hawking object with default attribute values.
	 */
	public StephenHawking() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
