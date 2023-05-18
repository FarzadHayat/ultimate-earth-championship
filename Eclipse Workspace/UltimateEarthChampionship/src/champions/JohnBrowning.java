package champions;

import model.Champion;

/**
 * Represents the John Browning champion.
 */
public class JohnBrowning extends Champion {

	// Name
	public static final String name = "John Browning";

	// Stat Boosts
	public static final int healthBoost = 1;
	public static final int staminaBoost = 0;
	public static final int offenseBoost = -1;
	public static final int defenseBoost = 1;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new John Browning object with default attribute values.
	 */
	public JohnBrowning() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
