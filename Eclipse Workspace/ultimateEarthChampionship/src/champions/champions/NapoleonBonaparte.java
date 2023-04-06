package champions.champions;

import champions.Champion;

public class NapoleonBonaparte extends Champion{

	// Stat Boosts:
	private static int healthBoost = -1;
	private static int staminaBoost = -1;
	private static int offenseBoost = 4;
	private static int defenseBoost = -1;
	
	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;
	
	public NapoleonBonaparte() {
		super("Napoleon Bonaparte", healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
