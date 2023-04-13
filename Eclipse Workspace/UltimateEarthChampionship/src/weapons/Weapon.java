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
	
	// Name
    private String name;
    
    // Stat Boosts
    private int damageBoost;
    private int offenseBoost;
    private int defenseBoost;
    
    // Price
    private float price;
    private float priceChangeWeekly;
    
    // Image
    private static final String IMAGE_FOLDER_PATH = "src/images/";
    private ImageIcon image;
    
    /**
     * Constructors
     */
    
    /**
     * Creates a new Weapon object with the specified attributes.
     * @param name the name of the weapon
     * @param damageBoost the amount of damage boost the weapon provides
     * @param offenseBoost the amount of offense boost the weapon provides
     * @param defenseBoost the amount of defense boost the weapon provides
     * @param price the base price of the weapon
     * @param priceChangeWeekly the weekly price change of the weapon
     * @param imageFileName the name of the file containing the image of the weapon
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
	 * Returns a string representation of the current Weapon object.
	 * @return a string containing the current weapon's attribute values formatted to match the `toStringHeader` output
	 *         with each attribute separated by vertical bars (`|`)
	 *         and padded to a fixed width to align with the `toStringHeader` output
	 */
	public String toString() {
		String text = "       [ %-20s | %12s | %13s | %13s | %5s | %19s ]";
		return String.format(text, name, String.valueOf(damageBoost),
				String.valueOf(offenseBoost), String.valueOf(defenseBoost),
				String.valueOf(price), String.valueOf(priceChangeWeekly));
	}
	
	/**
	 * Returns a header string for the Weapon class that specifies the format of the `toString` method's output.
	 * @return a string containing the names of each attribute separated by vertical bars (`|`)
	 *         and padded to a fixed width to align with the `toString` method's output
	 */
	public static String toStringHeader() {
		return "Weapon [ Name                 | Damage boost | Offense boost | Defense boost | Price | Price change weekly ]";
	}

}
