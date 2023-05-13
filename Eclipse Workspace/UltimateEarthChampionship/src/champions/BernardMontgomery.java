package champions;

import model.Champion;

public class BernardMontgomery extends Champion {

	// Stat Boosts:
	private static int healthBoost = 0;
	private static int staminaBoost = -1;
	private static int offenseBoost = 1;
	private static int defenseBoost = 2;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	public BernardMontgomery() {
		super("Bernard Montgomery", healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice,
				priceChangeWeekly);
	}
}
