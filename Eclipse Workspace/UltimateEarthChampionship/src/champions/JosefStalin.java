package champions;

import model.Champion;

/**
 * Represents the Josef Stalin champion.
 */
public class JosefStalin extends Champion {

	// Name
	public static final String name = "Josef Stalin";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = -3;
	public static final int offenseBoost = 2;
	public static final int defenseBoost = 2;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Josef Stalin object with default attribute values.
	 */
	public JosefStalin() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
