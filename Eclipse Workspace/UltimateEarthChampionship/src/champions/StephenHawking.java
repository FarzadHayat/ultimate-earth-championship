package champions;

import model.Champion;

public class StephenHawking extends Champion{

	// Stat Boosts:
	private static int healthBoost = 1;
	private static int staminaBoost = -1;
	private static int offenseBoost = 0;
	private static int defenseBoost = 1;
	
	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;
	
	public StephenHawking() {
		super("Stephen Hawking", healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
