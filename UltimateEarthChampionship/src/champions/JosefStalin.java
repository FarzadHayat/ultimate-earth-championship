package champions;

import model.Champion;

/**
 * Represents the Josef Stalin champion.
 */
public class JosefStalin extends Champion {

	// Name
	private static final String name = "Josef Stalin";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = -3;
	private static final int offenseBoost = 2;
	private static final int defenseBoost = 2;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Josef Stalin object with default attribute values.
	 */
	public JosefStalin() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
