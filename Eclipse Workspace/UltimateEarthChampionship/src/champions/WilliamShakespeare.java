package champions;

import model.Champion;

/**
 * Represents the William Shakespeare champion.
 */
public class WilliamShakespeare extends Champion {

	// Name
	public static final String name = "William Shakespeare";

	// Stat Boosts
	public static final int healthBoost = 2;
	public static final int staminaBoost = 1;
	public static final int offenseBoost = -1;
	public static final int defenseBoost = -1;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new William Shakespeare object with default attribute values.
	 */
	public WilliamShakespeare() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
