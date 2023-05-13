package champions;

import model.Champion;

public class MikolosHorthy extends Champion {

	public static String name = "Mikolos Horthy";

	// Stat Boosts:
	private static int healthBoost = 0;
	private static int staminaBoost = 0;
	private static int offenseBoost = 0;
	private static int defenseBoost = 1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	public MikolosHorthy() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
