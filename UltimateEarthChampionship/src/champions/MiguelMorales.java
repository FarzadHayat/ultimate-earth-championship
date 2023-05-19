package champions;

import model.Champion;

/**
 * Represents the Miguel Morales champion.
 */
public class MiguelMorales extends Champion {

	// Name
	private static final String name = "Miguel Morales";

	// Stat Boosts
	private static final int healthBoost = -1;
	private static final int staminaBoost = 1;
	private static final int offenseBoost = 3;
	private static final int defenseBoost = -2;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Miguel Morales object with default attribute values.
	 */
	public MiguelMorales() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
