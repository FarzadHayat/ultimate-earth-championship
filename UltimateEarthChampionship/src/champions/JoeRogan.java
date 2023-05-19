package champions;

import model.Champion;

/**
 * Represents the Joe Rogan champion.
 */
public class JoeRogan extends Champion {

	// Name
	private static final String name = "Joe Rogan";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = 0;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 0;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Joe Rogan object with default attribute values.
	 */
	public JoeRogan() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
