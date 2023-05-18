package champions;

import model.Champion;

/**
 * Represents the Confucius champion.
 */
public class Confucius extends Champion {

	// Name
	public static final String name = "Confucius";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = 0;
	public static final int offenseBoost = -1;
	public static final int defenseBoost = 2;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Confucius object with default attribute values.
	 */
	public Confucius() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
