package champions;

import model.Champion;

/**
 * Represents the Matthias Galster champion.
 */
public class MatthiasGalster extends Champion {

	// Name
	public static final String name = "Matthias Galster";

	// Stat Boosts
	public static final int healthBoost = 1;
	public static final int staminaBoost = 0;
	public static final int offenseBoost = 0;
	public static final int defenseBoost = 0;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Matthias Galster object with default attribute values.
	 */
	public MatthiasGalster() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
