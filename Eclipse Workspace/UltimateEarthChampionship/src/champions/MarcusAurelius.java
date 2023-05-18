package champions;

import model.Champion;

/**
 * Represents the Marcus Aurelius champion.
 */
public class MarcusAurelius extends Champion {

	// Name
	private static final String name = "Marcus Aurelius";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = -1;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 2;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Marcus Aurelius object with default attribute values.
	 */
	public MarcusAurelius() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
