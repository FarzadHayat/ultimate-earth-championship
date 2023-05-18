package champions;

import model.Champion;

/**
 * Represents the Queen Victoria champion.
 */
public class QueenVictoria extends Champion {

	// Name
	public static final String name = "Queen Victoria";

	// Stat Boosts
	public static final int healthBoost = 0;
	public static final int staminaBoost = -1;
	public static final int offenseBoost = 1;
	public static final int defenseBoost = 1;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new Queen Victoria object with default attribute values.
	 */
	public QueenVictoria() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
