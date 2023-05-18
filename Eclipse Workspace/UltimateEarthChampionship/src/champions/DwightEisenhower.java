package champions;

import model.Champion;

/**
 * Represents the Dwight Eisenhower champion.
 */
public class DwightEisenhower extends Champion {

	// Name
	public static final String name = "Dwight Eisenhower";

	// Stat Boosts
	public static final int healthBoost = 1;
	public static final int staminaBoost = 0;
	public static final int offenseBoost = 0;
	public static final int defenseBoost = 0;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Dwight Eisenhower object with default attribute values.
	 */
	public DwightEisenhower() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
