package champions;

import model.Champion;

/**
 * Represents the Marcus Aurelius champion.
 */
public class MarcusAurelius extends Champion {

	// Name
	public static final String name = "Marcus Aurelius";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = -1;
	public static final int offenseBoost = 0;
	public static final int defenseBoost = 2;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Marcus Aurelius object with default attribute values.
	 */
	public MarcusAurelius() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
