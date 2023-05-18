package champions;

import model.Champion;

/**
 * Represents the John Doe champion.
 */
public class JohnDoe extends Champion {

	// Name
	public static final String name = "John Doe";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = 0;
	public static final int offenseBoost = 0;
	public static final int defenseBoost = 0;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new John Doe object with default attribute values. This is a
	 * testing champion so it should not be included when instantiating all
	 * champions in the game manager.
	 */
	public JohnDoe() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
