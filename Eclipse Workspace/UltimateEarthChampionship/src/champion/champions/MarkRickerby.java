package champion.champions;

import champion.Champion;

public class MarkRickerby extends Champion{

	public static String name = "Mark Rickerby";
	
	// Stat Boosts:
	private static int healthBoost = 0;
	private static int staminaBoost = 0;
	private static int offenseBoost = 0;
	private static int defenseBoost = 1;
	
	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;
	
	public MarkRickerby() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
