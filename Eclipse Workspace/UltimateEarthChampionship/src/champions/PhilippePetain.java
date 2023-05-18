package champions;

import model.Champion;

/**
 * Represents the Philippe Petain champion.
 */
public class PhilippePetain extends Champion {

	// Name
	public static final String name = "Philippe Petain";

	// Stat Boosts
	public static final int healthBoost = 2;
	public static final int staminaBoost = -1;
	public static final int offenseBoost = 0;
	public static final int defenseBoost = 0;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Philippe Petain object with default attribute values.
	 */
	public PhilippePetain() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
