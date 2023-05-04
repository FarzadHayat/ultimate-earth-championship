package weapons;

import model.Weapon;

/**
 * Represents a Sledgehammer weapon.
 */
public class Sledgehammer extends Weapon {
    
    // Name
    private static final String name = "Sledgehammer";
    
    // Stat Boosts
    private static final float damageMultiplier = 3;
    private static final int offenseBoost = -2;
    private static final int defenseBoost = -1;
    
    // Price
    private static final float startingPrice = 100f;
    private static final float priceChangeWeekly = 0.9f;
    
    /**
     * Creates a new Sledgehammer object with default attribute values.
     */
    public Sledgehammer() {
        super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, false);
    }
}