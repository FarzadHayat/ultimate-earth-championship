package champions;

import model.Champion;

/**
 * Represents the Ted Kaczynski champion.
 */
public class TedKaczynski extends Champion {

	// Name
	public static final String name = "Ted Kaczynski";

	// Stat Boosts
	public static final int healthBoost = -1;
	public static final int staminaBoost = 0;
	public static final int offenseBoost = 3;
	public static final int defenseBoost = -1;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Ted Kaczynski object with default attribute values.
	 */
	public TedKaczynski() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
