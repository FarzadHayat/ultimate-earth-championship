package champions;

import model.Champion;

/**
 * Represents the Napoleon Bonaparte champion.
 */
public class NapoleonBonaparte extends Champion {

	// Name
	public static final String name = "Napoleon Bonaparte";

	// Stat Boosts:
	public static final int healthBoost = -1;
	public static final int staminaBoost = -1;
	public static final int offenseBoost = 4;
	public static final int defenseBoost = -1;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Napoleon Bonaparte object with default attribute values.
	 */
	public NapoleonBonaparte() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
