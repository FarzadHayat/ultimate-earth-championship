package weapons;

/**
 * Fists are the default weapon for all champions
 */
public class Fists extends Weapon {
	
	// Name
	private static final String name = "Fists";
	
	// Stat Boosts
	private static final int damageMultiplier = 1;
	private static final int offenseBoost = 0;
	private static final int defenseBoost = 0;
	
	// Price
	private static final float startingPrice = 0f;
	private static final float priceChangeWeekly = 1f;
	
	// Image
	private static final String imageFileName = "";
	
	/**
     * Creates a new Fist object with default attribute values.
     */
	public Fists() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, imageFileName, true);
	}
	
}
