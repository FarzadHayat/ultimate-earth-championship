package champions;

import model.Champion;

/**
 * Represents the John Doe champion.
 */
public class JohnDoe extends Champion {

	// Name
	private static final String name = "John Doe";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = 0;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 0;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new John Doe object with default attribute values. This is a
	 * testing champion so it should not be included when instantiating all
	 * champions in the game manager.
	 */
	public JohnDoe() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
