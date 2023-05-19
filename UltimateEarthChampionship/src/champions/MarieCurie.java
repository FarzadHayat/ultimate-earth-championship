package champions;

import model.Champion;

/**
 * Represents the Marie Curie champion.
 */
public class MarieCurie extends Champion {

	// Name
	private static final String name = "Marie Curie";

	// Stat Boosts
	private static final int healthBoost = 1;
	private static final int staminaBoost = 0;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 0;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Marie Curie object with default attribute values.
	 */
	public MarieCurie() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
