package champions;

import model.Champion;

public class NeilArmstrong extends Champion {

	// Stat Boosts:
	private static int healthBoost = 2;
	private static int staminaBoost = 1;
	private static int offenseBoost = -1;
	private static int defenseBoost = -1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	public NeilArmstrong() {
		super("Neil Armstrong", healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice,
				priceChangeWeekly);
	}
}
