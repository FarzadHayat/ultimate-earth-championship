package champions;

import model.Champion;

public class AdamSmith extends Champion{

	public static String name = "Adam Smith";
	
	// Stat Boosts:
	private static int healthBoost = -1;
	private static int staminaBoost = 1;
	private static int offenseBoost = 1;
	private static int defenseBoost = 0;
	
	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;
	
	public AdamSmith() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
