package champions;

import model.Champion;

/**
 * Represents the Nikita Krustchev champion.
 */
public class NikitaKrustchev extends Champion {

	// Name
	public static String name = "Nikita Krustchev";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = 2;
	private static int offenseBoost = -1;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Nikita Krustchev object with default attribute values.
	 */
	public NikitaKrustchev() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
