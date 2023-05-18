package champions;

import model.Champion;

/**
 * Represents the Nikita Krustchev champion.
 */
public class NikitaKrustchev extends Champion {

	// Name
	public static final String name = "Nikita Krustchev";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = 2;
	public static final int offenseBoost = -1;
	public static final int defenseBoost = 0;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Nikita Krustchev object with default attribute values.
	 */
	public NikitaKrustchev() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
