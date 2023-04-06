package champion.champions;

import champion.Champion;

public class JohnMKeynes extends Champion{

	// Stat Boosts:
	private static int healthBoost = 1;
	private static int staminaBoost = 0;
	private static int offenseBoost = -1;
	private static int defenseBoost = 1;
	
	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;
	
	public JohnMKeynes() {
		super("John Meynard Keynes", healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
