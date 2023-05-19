package champions;

import model.Champion;

/**
 * Represents the Shoko Asahara champion.
 */
public class ShokoAsahara extends Champion {

	// Name
	private static final String name = "Shoko Asahara";

	// Stat Boosts
	private static final int healthBoost = -2;
	private static final int staminaBoost = 1;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 1;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Shoko Asahara object with default attribute values.
	 */
	public ShokoAsahara() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
