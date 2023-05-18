package champions;

import model.Champion;

/**
 * Represents the Augusto Pinochet champion.
 */
public class AugustoPinochet extends Champion {

	// Name
	private static final String name = "Augusto Pinochet";

	// Stat Boosts
	private static final int healthBoost = -1;
	private static final int staminaBoost = 0;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 2;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Augusto Pinochet object with default attribute values.
	 */
	public AugustoPinochet() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
