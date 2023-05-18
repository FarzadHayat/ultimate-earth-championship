package champions;

import model.Champion;

/**
 * Represents the John Maynard Keynes champion.
 */
public class JohnMaynardKeynes extends Champion {

	// Name
	public static final String name = "John Maynard Keynes";

	// Stat Boosts
	public static final int healthBoost = 1;
	public static final int staminaBoost = 0;
	public static final int offenseBoost = -1;
	public static final int defenseBoost = 1;

	// Price
	public static final float startingPrice = 50f;
	public static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new John Maynard Keynes object with default attribute values.
	 */
	public JohnMaynardKeynes() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
