package champions;

import model.Champion;

/**
 * Represents the Ted Kaczynski champion.
 */
public class TedKaczynski extends Champion {

	// Name
	private static final String name = "Ted Kaczynski";

	// Stat Boosts
	private static final int healthBoost = -1;
	private static final int staminaBoost = 0;
	private static final int offenseBoost = 3;
	private static final int defenseBoost = -1;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Ted Kaczynski object with default attribute values.
	 */
	public TedKaczynski() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
