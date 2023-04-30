package model;

import javax.swing.ImageIcon;

/**
 * Class Weapon
 */
public abstract class Weapon implements Purchasable, Cloneable {
	
	/**
	 * Fields
	 */

	private Configuration config = Configuration.getInstance();
	
	/**
	 * The name of the weapon
	 */
    private String name;
    
    // Stat Boosts
    private int damageMultiplier;
    private int offenseBoost;
    private int defenseBoost;
    /**
     * The current price of the weapon
     */
    private float price;
    /**
     * The value to multiply the price by on a weekly basis
     */
    private float priceChangeWeekly;
    
    /**
     * Is this a default weapon?
     */
    private boolean isDefaultWeapon;
    
    // Image
    private ImageIcon image;
    
    /**
     * Constructors	private int numChampions = 4;
     */
    
    /**
     * Creates a new Weapon object with the specified attributes.
     * @param name the name of the weapon
     * @param damageMultiplier the amount that damage dealt is multiplied by
     * @param offenseBoost the amount of offense boost the weapon provides
     * @param defenseBoost the amount of defense boost the weapon provides
     * @param price the base price of the weapon
     * @param priceChangeWeekly the weekly price change of the weapon
     * @param imageFileName the name of the file containing the image of the weapon
     */
    public Weapon(String name, int damageBoost, int offenseBoost, int defenseBoost, float price, float priceChangeWeekly, String imageFileName, boolean isDefault) {
    	this.name = name;
    	this.damageMultiplier = damageBoost;
    	this.offenseBoost = offenseBoost;
    	this.defenseBoost = defenseBoost;
    	this.isDefaultWeapon = isDefault;
    	this.price = price * config.WEAPON_PRICE_MODIFIER;
    	this.priceChangeWeekly = priceChangeWeekly * config.WEAPON_PRICE_WEEKLY_CHANGE_MODIFIER;
    	if (imageFileName != "") {
        	try {
        		image = new ImageIcon(config.WEAPON_IMAGE_FOLDER_PATH + imageFileName);
        	}
        	catch (NullPointerException e) {
        		System.out.println(e.getMessage());
        	}
    	}
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
	 * Set the value of damageMultiplier
	 * @param newDamageBoost the new value of damageMultiplier
	 */
    public void setDamageMultiplier (int newDamageBoost) {
    	damageMultiplier = newDamageBoost;
    }

	/**
	 * Get the value of damageMultiplier
	 * @return the value of damageMultiplier
	 */
    public int getDamageMultiplier () {
    	return damageMultiplier;
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
    public void setPrice (float newPrice) {
    	price = newPrice;
    }

	/**
	 * Get the value of price
	 * @return the value of price
	 */
    public float getPrice () {
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
	 * Set the value of image
	 * @param newImage the new value of image
	 */	
	public void setImage(ImageIcon newImage) {
		image = newImage;
	}
	
	/**
	 * Get the value of image
	 * @return the value of image
	 */
	public ImageIcon getImage() {
		return image;
	}

	/**
	 * Other methods
	 */
	
	/**
	 * returns true if this is the default weapon that all champions should equip on creation.
	 * @return true if this is the default weapon for champions on creation
	 */
	public boolean isDefault()
	{
		return isDefaultWeapon;
	}
	
	/**
	 * Returns a string representation of the Weapon.
	 * @return a string containing the weapon attributes
	 */
	@Override
	public String toString() {
		return String.format(
				"Weapon [name=%s, damageMultiplier=%s, offenseBoost=%s, defenseBoost=%s, price=%s, priceChangeWeekly=%s]",
				name, damageMultiplier, offenseBoost, defenseBoost, price, priceChangeWeekly);
	}
	
	/**
	 * Create a clone of the Weapon with the same stats.
	 */
	@Override
	public Weapon clone() {
        try {
            return (Weapon) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
