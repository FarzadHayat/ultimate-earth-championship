package weapons;

import model.Weapon;

/**
 * Represents a Tennis Racket weapon.
 */
public class TennisRacket extends Weapon {
	
	// Name
	private static final String name = "Tennis Racket";
	
	// Stat Boosts
	private static final int damageMultiplier = 1;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 1;
	
	// Price
	private static final float startingPrice = 10f;
	private static final float priceChangeWeekly = 0.9f;
	
	/**
     * Creates a new Tennis Racket object with default attribute values.
     */
	public TennisRacket() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}
	
}
