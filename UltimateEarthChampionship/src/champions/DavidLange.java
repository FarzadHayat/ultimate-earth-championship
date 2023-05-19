package champions;

import model.Champion;

/**
 * Represents the David Lange champion.
 */
public class DavidLange extends Champion {

	// Name
	private static final String name = "David Lange";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = 1;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 0;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new David Lange object with default attribute values.
	 */
	public DavidLange() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
