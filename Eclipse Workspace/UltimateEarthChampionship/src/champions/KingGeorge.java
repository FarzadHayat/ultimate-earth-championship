package champions;

import model.Champion;

public class KingGeorge extends Champion {

	// Stat Boosts:
	private static int healthBoost = -1;
	private static int staminaBoost = -1;
	private static int offenseBoost = 0;
	private static int defenseBoost = 3;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	public KingGeorge() {
		super("King George V", healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
