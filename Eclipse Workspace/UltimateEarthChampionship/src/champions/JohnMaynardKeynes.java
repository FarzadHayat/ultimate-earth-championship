package champions;

import model.Champion;

/**
 * Represents the John Maynard Keynes champion.
 */
public class JohnMaynardKeynes extends Champion {

	// Name
	private static String name = "John Maynard Keynes";

	// Stat Boosts
	private static int healthBoost = 1;
	private static int staminaBoost = 0;
	private static int offenseBoost = -1;
	private static int defenseBoost = 1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new John Maynard Keynes object with default attribute values.
	 */
	public JohnMaynardKeynes() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
