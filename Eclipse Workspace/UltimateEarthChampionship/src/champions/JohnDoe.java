package champions;

import model.Champion;

public class JohnDoe extends Champion {

	// Stat Boosts:
	private static int healthBoost = 0;
	private static int staminaBoost = 0;
	private static int offenseBoost = 0;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	public JohnDoe() {
		// THIS is a TESTING CHAMPION, not intended for gameplay
		super("John Doe", healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
