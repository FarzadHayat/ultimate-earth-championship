package champions;

import model.Champion;

/**
 * Represents the Napoleon Bonaparte champion.
 */
public class NapoleonBonaparte extends Champion {

	// Name
	private static String name = "Napoleon Bonaparte";

	// Stat Boosts:
	private static int healthBoost = -1;
	private static int staminaBoost = -1;
	private static int offenseBoost = 4;
	private static int defenseBoost = -1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Napoleon Bonaparte object with default attribute values.
	 */
	public NapoleonBonaparte() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
