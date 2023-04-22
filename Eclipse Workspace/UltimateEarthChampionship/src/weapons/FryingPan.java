package weapons;

import model.Weapon;

/**
 * Represents a Frying Pan weapon.
 */
public class FryingPan extends Weapon {
	
	// Name
	private static final String name = "Frying Pan";
	
	// Stat Boosts
	private static final int damageMultiplier = 1;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 2;
	
	// Price
	private static final float startingPrice = 10f;
	private static final float priceChangeWeekly = 0.9f;
	
	// Image
	private static final String imageFileName = "";
	
	/**
     * Creates a new Frying Pan object with default attribute values.
     */
	public FryingPan() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, imageFileName, false);
	}
	
}
