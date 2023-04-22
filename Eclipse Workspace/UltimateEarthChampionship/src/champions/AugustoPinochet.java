package champions;

import model.Champion;

public class AugustoPinochet extends Champion{

	public static String name = "Augusto Pinochet";
	
	// Stat Boosts:
	private static int healthBoost = -1;
	private static int staminaBoost = 0;
	private static int offenseBoost = 0;
	private static int defenseBoost = 2;
	
	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;
	
	public AugustoPinochet() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
