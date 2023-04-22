package champions;

import model.Champion;

public class MatthiasGalster extends Champion{

	public static String name = "Matthias Galster";
	
	// Stat Boosts:
	private static int healthBoost = 1;
	private static int staminaBoost = 0;
	private static int offenseBoost = 0;
	private static int defenseBoost = 0;
	
	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;
	
	public MatthiasGalster() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
