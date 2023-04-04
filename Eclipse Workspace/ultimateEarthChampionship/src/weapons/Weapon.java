package weapons;

/**
 * Class Weapon
 */
public abstract class Weapon {

	/**
	 * Fields
	 */

	/**
	 * The name of the weapon
	 */
    private String name;
    /**
     * The amount of damage bonus the weapon gives
     */
    private int damageBoost;
    /**
     * The amount of offense bonus the weapon gives
     */
    private int offenseBoost;
    /**
     * The amount of defense bonus the weapon gives
     */
    private int defenseBoost;
    /**
     * The current price of the weapon
     */
    private int price;
    /**
     * The value to multiply the price by on a weekly basis
     */
    private float priceChangeWeekly;
    
    /**
     * Constructors
     */
    
    public Weapon(String name, int damageBoost, int offenseBoost, int defenseBoost, int price, float priceChangeWeekly) {
    	this.name = name;
    	this.damageBoost = damageBoost;
    	this.offenseBoost = offenseBoost;
    	this.defenseBoost = defenseBoost;
    	this.price = price;
    	this.priceChangeWeekly = priceChangeWeekly;
    }
    
	/**
	 * Methods
	 */

	/**
	 * Accessor methods
	 */

	/**
	 * Set the value of name
	 * @param newName the new value of name
	 */
    public void setName (String newName) {
    	name = newName;
    }

	/**
	 * Get the value of name
	 * @return the value of name
	 */
    public String getName () {
    	return name;
    }

	/**
	 * Set the value of damageBoost
	 * @param newDamageBoost the new value of damageBoost
	 */
    public void setDamageBoost (int newDamageBoost) {
    	damageBoost = newDamageBoost;
    }

	/**
	 * Get the value of damageBoost
	 * @return the value of damageBoost
	 */
    public int getDamageBoost () {
    	return damageBoost;
    }

	/**
	 * Set the value of offenseBoost
	 * @param newOffenseBoost the new value of offenseBoost
	 */
    public void setOffenseBoost (int newOffenseBoost) {
    	offenseBoost = newOffenseBoost;
    }

	/**
	 * Get the value of offenseBoost
	 * @return the value of offenseBoost
	 */
    public int getOffenseBoost () {
    	return offenseBoost;
    }

	/**
	 * Set the value of defenseBoost
	 * @param newDefenseBoost the new value of defenseBoost
	 */
    public void setdefenseBoost (int newDefenseBoost) {
    	defenseBoost = newDefenseBoost;
    }

	/**
	 * Get the value of defenseBoost
	 * @return the value of defenseBoost
	 */
    public int getDefenseBoost () {
    	return defenseBoost;
    }

	/**
	 * Set the value of price
	 * @param newPrice the new value of price
	 */
    public void setPrice (int newPrice) {
    	price = newPrice;
    }

	/**
	 * Get the value of price
	 * @return the value of price
	 */
    public int getPrice () {
    	return price;
    }

	/**
	 * Set the value of priceChangeWeekly
	 * @param newPriceChangeWeekly the new value of priceChangeWeekly
	 */
    public void setPriceChangeWeekly (float newPriceChangeWeekly) {
    	priceChangeWeekly = newPriceChangeWeekly;
    }

	/**
	 * Get the value of priceChangeWeekly
	 * @return the value of priceChangeWeekly
	 */
    public float getPriceChangeWeekly () {
    	return priceChangeWeekly;
    }

	/**
	 * Other methods
	 */

}
