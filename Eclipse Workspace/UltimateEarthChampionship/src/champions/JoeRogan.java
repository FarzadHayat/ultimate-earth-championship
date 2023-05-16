package champions;

import model.Champion;

/**
 * Represents the Joe Rogan champion.
 */
public class JoeRogan extends Champion {

	// Name
	public static String name = "Joe Rogan";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = 0;
	private static int offenseBoost = 1;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Joe Rogan object with default attribute values.
	 */
	public JoeRogan() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
