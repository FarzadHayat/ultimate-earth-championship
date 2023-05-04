package weapons;

import model.Weapon;

/**
 * Represents a Pickaxe weapon.
 */
public class Pickaxe extends Weapon {
	
	// Name
	private static final String name = "Pickaxe";
	
	// Stat Boosts
	private static final float damageMultiplier = 1f;
	private static final int offenseBoost = 2;
	private static final int defenseBoost = 1;
	
	// Price
	private static final float startingPrice = 25f;
	private static final float priceChangeWeekly = 0.9f;
	
	/**
     * Creates a new Pickaxe object with default attribute values.
     */
	public Pickaxe() {
		super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
	}
	
}
