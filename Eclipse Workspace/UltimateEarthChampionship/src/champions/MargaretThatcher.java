package champions;

import model.Champion;

/**
 * Represents the Margaret Thatcher champion.
 */
public class MargaretThatcher extends Champion {

	// Name
	public static final String name = "Margaret Thatcher";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = -1;
	public static final int offenseBoost = 1;
	public static final int defenseBoost = 1;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Margaret Thatcher object with default attribute values.
	 */
	public MargaretThatcher() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
