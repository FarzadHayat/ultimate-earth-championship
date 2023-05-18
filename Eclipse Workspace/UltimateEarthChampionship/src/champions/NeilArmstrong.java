package champions;

import model.Champion;

/**
 * Represents the Neil Armstrong champion.
 */
public class NeilArmstrong extends Champion {

	// Name
	public static final String name = "Neil Armstrong";

	// Stat Boosts
	public static final int healthBoost = 2;
	public static final int staminaBoost = 1;
	public static final int offenseBoost = -1;
	public static final int defenseBoost = -1;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Neil Armstrong object with default attribute values.
	 */
	public NeilArmstrong() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
