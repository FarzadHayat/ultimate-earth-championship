package champions;

import model.Champion;

/**
 * Represents the John F Kennedy champion.
 */
public class JohnFKennedy extends Champion {

	// Name
	public static final String name = "John F Kennedy";

	// Stat Boosts
	public static final int healthBoost = -2;
	public static final int staminaBoost = 2;
	public static final int offenseBoost = 1;
	public static final int defenseBoost = 0;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new John F Kennedy object with default attribute values.
	 */
	public JohnFKennedy() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
