package weapons;

import javax.swing.ImageIcon;

import main.Purchasable;

/**
 * Class Weapon
 */
public abstract class Weapon implements Purchasable {

	/**
	 * Fields
	 */

	/**
	 * The folder location where the weapon images are stored
	 */
	private static final String IMAGE_FOLDER_PATH = "src/images/";
	
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
    private float price;
    /**
     * The value to multiply the price by on a weekly basis
     */
    private float priceChangeWeekly;
    /**
     * The image of the weapon to display
     */
    private ImageIcon image;
    
    /**
     * Constructors
     */
    
    public Weapon(String name, int damageBoost, int offenseBoost, int defenseBoost, float price, float priceChangeWeekly, String imageFileName) {
    	this.name = name;
    	this.damageBoost = damageBoost;
    	this.offenseBoost = offenseBoost;
    	this.defenseBoost = defenseBoost;
    	this.price = price;
    	this.priceChangeWeekly = priceChangeWeekly;
    	try {
    		this.image = new ImageIcon(IMAGE_FOLDER_PATH + imageFileName);
    	}
    	catch (NullPointerException e) {
    		System.out.println(e.getMessage());
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
	 * Return a string representation of the weapon
	 */
	@Override
	public String toString() {
		String text = "Weapon [Name: %s | Damage boost: %s | Offense boost: %s "
				+ "| Defense boost: %s | Price: %s | Price change weekly: %s]";
		return String.format(text, name, String.valueOf(damageBoost),
				String.valueOf(offenseBoost), String.valueOf(defenseBoost),
				String.valueOf(price), String.valueOf(priceChangeWeekly));
	}

}
