package champions;

import model.Champion;

public class NikitaKrustchev extends Champion {

	public static String name = "Nikita Krustchev";

	// Stat Boosts:
	private static int healthBoost = 0;
	private static int staminaBoost = 2;
	private static int offenseBoost = -1;
	private static int defenseBoost = 0;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	public NikitaKrustchev() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
