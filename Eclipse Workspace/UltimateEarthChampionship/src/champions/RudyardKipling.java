package champions;

import model.Champion;

/**
 * Represents the Rudyard Kipling champion.
 */
public class RudyardKipling extends Champion {

	// Name
	public static final String name = "Rudyard Kipling";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = 1;
	public static final int offenseBoost = 0;
	public static final int defenseBoost = 0;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Rudyard Kipling object with default attribute values.
	 */
	public RudyardKipling() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
