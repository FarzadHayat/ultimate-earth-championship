package champions;

import model.Champion;

/**
 * Represents the Matthias Galster champion.
 */
public class MatthiasGalster extends Champion {

	// Name
	private static String name = "Matthias Galster";

	// Stat Boosts
	private static int healthBoost = 1;
	private static int staminaBoost = 0;
	private static int offenseBoost = 0;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Matthias Galster object with default attribute values.
	 */
	public MatthiasGalster() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
