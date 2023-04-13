package weapons;

/**
 * Represents a Shield weapon.
 */
public class Shield extends Weapon {
	
	// Name
	private static final String name = "Shield";
	
	// Stat Boosts
	private static final int damageBoost = 1;
	private static final int offenseBoost = -1;
	private static final int defenseBoost = 3;
	
	// Price
	private static final float startingPrice = 10f;
	private static final float priceChangeWeekly = 0.9f;
	
	// Image
	private static final String imagePath = "shield.png";
	
	/**
     * Creates a new Shield object with default attribute values.
     */
	public Shield() {
		super(name, damageBoost, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, imagePath);
	}
	
}
