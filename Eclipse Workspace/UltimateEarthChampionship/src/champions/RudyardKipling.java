package champions;

import model.Champion;

public class RudyardKipling extends Champion {

	public static String name = "Rudyard Kipling";

	// Stat Boosts:
	private static int healthBoost = 0;
	private static int staminaBoost = 1;
	private static int offenseBoost = 0;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	public RudyardKipling() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
