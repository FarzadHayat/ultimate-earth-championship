package champion.champions;

import champion.Champion;

public class GeorgeWashington extends Champion{

	// Stat Boosts:
	private static int healthBoost = 1;
	private static int staminaBoost = -2;
	private static int offenseBoost = 1;
	private static int defenseBoost = 1;
	
	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;
	
	public GeorgeWashington() {
		super("George Washington", healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
