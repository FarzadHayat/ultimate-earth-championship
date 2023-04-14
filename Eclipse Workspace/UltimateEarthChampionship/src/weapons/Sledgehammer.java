package weapons;

/**
 * Represents a Sledgehammer weapon.
 */
public class Sledgehammer extends Weapon {
    
    // Name
    private static final String name = "Sledgehammer";
    
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
     * Creates a new Sledgehammer object with default attribute values.
     */
    public Sledgehammer() {
        super(name, damageMultiplier, offenseBoost, defenseBoost, startingPrice, priceChangeWeekly, imageFileName);
    }
}