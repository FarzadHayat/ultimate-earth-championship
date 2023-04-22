package champions;

import model.Champion;

public class JosefStalin extends Champion{

	// Stat Boosts:
	private static int healthBoost = 0;
	private static int staminaBoost = -3;
	private static int offenseBoost = 2;
	private static int defenseBoost = 2;
	
	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;
	
	public JosefStalin() {
		super("Josef Stalin", healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
