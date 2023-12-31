package champions;

import model.Champion;

/**
 * Represents the Philippe Petain champion.
 */
public class PhilippePetain extends Champion {

	// Name
	private static final String name = "Philippe Petain";

	// Stat Boosts
	private static final int healthBoost = 2;
	private static final int staminaBoost = -1;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 0;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Philippe Petain object with default attribute values.
	 */
	public PhilippePetain() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
