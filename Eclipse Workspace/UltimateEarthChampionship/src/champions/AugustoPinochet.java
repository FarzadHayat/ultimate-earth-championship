package champions;

import model.Champion;

/**
 * Represents the Augusto Pinochet champion.
 */
public class AugustoPinochet extends Champion {

	// Name
	public static final String name = "Augusto Pinochet";

	// Stat Boosts
	public static final int healthBoost = -1;
	public static final int staminaBoost = 0;
	public static final int offenseBoost = 0;
	public static final int defenseBoost = 2;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Augusto Pinochet object with default attribute values.
	 */
	public AugustoPinochet() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
