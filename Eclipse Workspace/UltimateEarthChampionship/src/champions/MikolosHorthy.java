package champions;

import model.Champion;

/**
 * Represents the Mikolos Horthy champion.
 */
public class MikolosHorthy extends Champion {

	// Name
	public static final String name = "Mikolos Horthy";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = 0;
	public static final int offenseBoost = 0;
	public static final int defenseBoost = 1;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Mikolos Horthy object with default attribute values.
	 */
	public MikolosHorthy() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
