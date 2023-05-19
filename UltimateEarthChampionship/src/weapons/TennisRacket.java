package weapons;

import model.Weapon;

/**
 * Represents a Tennis Racket weapon.
 */
public class TennisRacket extends Weapon {

	// Name
	private static final String name = "Tennis Racket";

	// Stat Boosts
	private static final float damageMultiplier = 0.8f;
	private static final int offenseBoost = 3;
	private static final int defenseBoost = 3;

	// Price
	private static final float startingPrice = 25f;
	private static final float priceChangeWeekly = 0.9f;

	/**
	 * Creates a new Tennis Racket object with default attribute values.
	 */
	public TennisRacket() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}

}
