package champions;

import model.Champion;

/**
 * Represents the Nikita Krustchev champion.
 */
public class NikitaKrustchev extends Champion {

	// Name
	private static final String name = "Nikita Krustchev";

	// Stat Boosts
	private static final int healthBoost = 0;
	private static final int staminaBoost = 2;
	private static final int offenseBoost = -1;
	private static final int defenseBoost = 0;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Nikita Krustchev object with default attribute values.
	 */
	public NikitaKrustchev() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
