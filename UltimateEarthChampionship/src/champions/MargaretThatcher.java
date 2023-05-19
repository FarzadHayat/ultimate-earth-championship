package champions;

import model.Champion;

/**
 * Represents the Margaret Thatcher champion.
 */
public class MargaretThatcher extends Champion {

	// Name
	private static final String name = "Margaret Thatcher";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = -1;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 1;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Margaret Thatcher object with default attribute values.
	 */
	public MargaretThatcher() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
