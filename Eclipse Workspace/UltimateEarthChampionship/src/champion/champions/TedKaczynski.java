package champion.champions;

import champion.Champion;

public class TedKaczynski extends Champion{

	public static String name = "Ted Kaczynski";
	
	// Stat Boosts:
	private static int healthBoost = -1;
	private static int staminaBoost = 0;
	private static int offenseBoost = 3;
	private static int defenseBoost = -1;
	
	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;
	
	public TedKaczynski() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
