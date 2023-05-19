package champions;

import model.Champion;

/**
 * Represents the Confucius champion.
 */
public class Confucius extends Champion {

	// Name
	private static final String name = "Confucius";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = 0;
	private static final int offenseBoost = -1;
	private static final int defenseBoost = 2;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Confucius object with default attribute values.
	 */
	public Confucius() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
