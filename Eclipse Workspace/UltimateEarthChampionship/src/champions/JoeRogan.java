package champions;

import model.Champion;

/**
 * Represents the Joe Rogan champion.
 */
public class JoeRogan extends Champion {

	// Name
	public static final String name = "Joe Rogan";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = 0;
	public static final int offenseBoost = 1;
	public static final int defenseBoost = 0;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Joe Rogan object with default attribute values.
	 */
	public JoeRogan() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
