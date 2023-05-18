package champions;

import model.Champion;

/**
 * Represents the Robert Muldoon champion.
 */
public class RobertMuldoon extends Champion {

	// Name
	public static final String name = "Robert Muldoon";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = 1;
	public static final int offenseBoost = 0;
	public static final int defenseBoost = 0;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Robert Muldoon object with default attribute values.
	 */
	public RobertMuldoon() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
