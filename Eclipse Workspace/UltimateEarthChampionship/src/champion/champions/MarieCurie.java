package champion.champions;

import champion.Champion;

public class MarieCurie extends Champion{

	public static String name = "Marie Curie";
	
	// Stat Boosts:
	private static int healthBoost = 1;
	private static int staminaBoost = 0;
	private static int offenseBoost = 0;
	private static int defenseBoost = 0;
	
	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;
	
	public MarieCurie() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
