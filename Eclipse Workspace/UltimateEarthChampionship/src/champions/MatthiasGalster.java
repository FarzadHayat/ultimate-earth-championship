package champions;

import model.Champion;

/**
 * Represents the Matthias Galster champion.
 */
public class MatthiasGalster extends Champion {

	// Name
	private static final String name = "Matthias Galster";

	// Stat Boosts
	private static final int healthBoost = 1;
	private static final int staminaBoost = 0;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 0;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Matthias Galster object with default attribute values.
	 */
	public MatthiasGalster() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
