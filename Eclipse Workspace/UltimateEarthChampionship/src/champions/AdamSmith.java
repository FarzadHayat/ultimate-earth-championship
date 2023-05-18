package champions;

import model.Champion;

/**
 * Represents the Adam Smith champion.
 */
public class AdamSmith extends Champion {

	// Name
	private static final String name = "Adam Smith";

	// Stat Boosts
	private static final int healthBoost = -1;
	private static final int staminaBoost = 1;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 0;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Adam Smith object with default attribute values.
	 */
	public AdamSmith() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
