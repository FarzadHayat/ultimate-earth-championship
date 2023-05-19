package champions;

import model.Champion;

/**
 * Represents the John Maynard Keynes champion.
 */
public class JohnMaynardKeynes extends Champion {

	// Name
	private static final String name = "John Maynard Keynes";

	// Stat Boosts
	private static final int healthBoost = 1;
	private static final int staminaBoost = 0;
	private static final int offenseBoost = -1;
	private static final int defenseBoost = 1;

	// Price
	private static final float startingPrice = 50f;
	private static final float priceChangeWeekly = 1.1f;

	/**
	 * Creates a new John Maynard Keynes object with default attribute values.
	 */
	public JohnMaynardKeynes() {
		super(name, healthBoost, staminaBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly);
	}
}
