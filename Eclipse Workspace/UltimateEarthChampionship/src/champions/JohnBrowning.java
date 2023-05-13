package champions;

import model.Champion;

public class JohnBrowning extends Champion {

	public static String name = "John Browning";

	// Stat Boosts:
	private static int healthBoost = 1;
	private static int staminaBoost = 0;
	private static int offenseBoost = -1;
	private static int defenseBoost = 1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	public JohnBrowning() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
