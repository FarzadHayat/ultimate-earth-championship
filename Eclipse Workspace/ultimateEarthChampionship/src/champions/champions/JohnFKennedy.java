package champions.champions;

import champions.Champion;

public class JohnFKennedy extends Champion{

	// Stat Boosts:
	private static int healthBoost = -2;
	private static int staminaBoost = 2;
	private static int offenseBoost = 1;
	private static int defenseBoost = 0;
	
	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;
	
	public JohnFKennedy() {
		super("John F Kennedy", healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
