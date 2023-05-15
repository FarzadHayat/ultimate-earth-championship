package champions;

import model.Champion;

/**
 * Represents the Dwight Eisenhower champion.
 */
public class DwightEisenhower extends Champion {

	// Name
	public static String name = "Dwight Eisenhower";

	// Stat Boosts
	private static int healthBoost = 1;
	private static int staminaBoost = 0;
	private static int offenseBoost = 0;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Dwight Eisenhower object with default attribute values.
	 */
	public DwightEisenhower() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
