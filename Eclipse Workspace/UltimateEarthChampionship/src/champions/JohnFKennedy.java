package champions;

import model.Champion;

/**
 * Represents the John F Kennedy champion.
 */
public class JohnFKennedy extends Champion {

	// Name
	private static final String name = "John F Kennedy";

	// Stat Boosts
	private static final int healthBoost = -2;
	private static final int staminaBoost = 2;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 0;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new John F Kennedy object with default attribute values.
	 */
	public JohnFKennedy() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
