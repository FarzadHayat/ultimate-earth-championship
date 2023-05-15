package champions;

import model.Champion;

/**
 * Represents the Queen Victoria champion.
 */
public class QueenVictoria extends Champion {

	// Name
	private static String name = "Queen Victoria";

	// Stat Boosts
	private static int healthBoost = 0;
	private static int staminaBoost = -1;
	private static int offenseBoost = 1;
	private static int defenseBoost = 1;

	// Price
	private static float startingPrice = 50f;
	private static float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Queen Victoria object with default attribute values.
	 */
	public QueenVictoria() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
