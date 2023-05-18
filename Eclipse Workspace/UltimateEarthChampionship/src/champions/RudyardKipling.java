package champions;

import model.Champion;

/**
 * Represents the Rudyard Kipling champion.
 */
public class RudyardKipling extends Champion {

	// Name
	private static final String name = "Rudyard Kipling";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = 1;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 0;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Rudyard Kipling object with default attribute values.
	 */
	public RudyardKipling() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
