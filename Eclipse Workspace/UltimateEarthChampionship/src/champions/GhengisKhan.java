package champions;

import model.Champion;

/**
 * Represents the Ghengis Khan champion.
 */
public class GhengisKhan extends Champion {

	// Name
	private static String name = "Ghengis Khan";

	// Stat Boosts
	private static int healthBoost = 2;
	private static int staminaBoost = -1;
	private static int offenseBoost = 0;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Ghengis Khan object with default attribute values.
	 */
	public GhengisKhan() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
