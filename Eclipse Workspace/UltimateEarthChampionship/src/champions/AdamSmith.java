package champions;

import model.Champion;

/**
 * Represents the Adam Smith champion.
 */
public class AdamSmith extends Champion {

	// Name
	public static final String name = "Adam Smith";

	// Stat Boosts
	public static final int healthBoost = -1;
	public static final int staminaBoost = 1;
	public static final int offenseBoost = 1;
	public static final int defenseBoost = 0;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Adam Smith object with default attribute values.
	 */
	public AdamSmith() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
