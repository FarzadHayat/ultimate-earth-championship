package champions;

import model.Champion;

/**
 * Represents the Ghengis Khan champion.
 */
public class GhengisKhan extends Champion {

	// Name
	public static final String name = "Ghengis Khan";

	// Stat Boosts
	public static final int healthBoost = 2;
	public static final int staminaBoost = -1;
	public static final int offenseBoost = 0;
	public static final int defenseBoost = 0;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Ghengis Khan object with default attribute values.
	 */
	public GhengisKhan() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
