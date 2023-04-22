package weapons;

import model.Weapon;

/**
 * Represents a Pitchfork weapon.
 */
public class Pitchfork extends Weapon {
	
	// Name
	private static final String name = "Pitchfork";
	
	// Stat Boosts
	private static final int damageMultiplier = 1;
	private static final int offenseBoost = 1;
	private static final int defenseBoost = 1;
	
	// Price
	private static final float startingPrice = 10f;
	private static final float priceChangeWeekly = 0.9f;
	
	// Image
	private static final String imageFileName = "";
	
	/**
     * Creates a new Pitchfork object with default attribute values.
     */
	public Pitchfork() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, imageFileName, false);
	}
	
}
