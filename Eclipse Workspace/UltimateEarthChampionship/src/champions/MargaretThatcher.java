package champions;

import model.Champion;

/**
 * Represents the Margaret Thatcher champion.
 */
public class MargaretThatcher extends Champion {

	// Name
	private static String name = "Margaret Thatcher";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = -1;
	private static int offenseBoost = 1;
	private static int defenseBoost = 1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Margaret Thatcher object with default attribute values.
	 */
	public MargaretThatcher() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
