package champions;

import model.Champion;

/**
 * Represents the Mikolos Horthy champion.
 */
public class MikolosHorthy extends Champion {

	// Name
	public static String name = "Mikolos Horthy";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = 0;
	private static int offenseBoost = 0;
	private static int defenseBoost = 1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Mikolos Horthy object with default attribute values.
	 */
	public MikolosHorthy() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
