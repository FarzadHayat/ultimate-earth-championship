package champions;

import model.Champion;

/**
 * Represents the Mikolos Horthy champion.
 */
public class MikolosHorthy extends Champion {

	// Name
	private static final String name = "Mikolos Horthy";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = 0;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 1;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Mikolos Horthy object with default attribute values.
	 */
	public MikolosHorthy() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
