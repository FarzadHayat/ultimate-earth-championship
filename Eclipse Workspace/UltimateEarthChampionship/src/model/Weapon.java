package model;

import javax.swing.ImageIcon;

import exception.FullTeamException;
import exception.InsufficientFundsException;

/**
 * Class Weapon contains all the general properties and functionality a weapon
 * should have. A weapon is normally used in the context of being assigned to a
 * champion.
 */
public abstract class Weapon implements Purchasable, Cloneable {

	private Configuration config = Configuration.getInstance();

	/**
	 * The name of the weapon
	 */
	private String name;

	// Stat Boosts
	private float damageMultiplier;
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
	 * True if this is the default weapon
	 */
	private boolean isDefaultWeapon;

	/**
	 * The image icon for the weapon
	 */
	private ImageIcon image;

	/**
	 * Creates a new Weapon object with the specified attributes.
	 *
	 * @param name              the name of the weapon
	 * @param damageMultiplier  the amount that damage dealt is multiplied by
	 * @param offenseBoost      the amount of offense boost the weapon provides
	 * @param defenseBoost      the amount of defense boost the weapon provides
	 * @param price             the base price of the weapon
	 * @param priceChangeWeekly the weekly price change of the weapon
	 */
	public Weapon(String name, float damageMultiplier, int offenseBoost, int defenseBoost, float price,
			float priceChangeWeekly, boolean isDefault) {
		setName(name);
		setDamageMultiplier(damageMultiplier);
		setOffenseBoost(offenseBoost);
		setdefenseBoost(defenseBoost);
		setDefaultWeapon(isDefault);
		setPrice(price * config.WEAPON_PRICE_MODIFIER);
		setPriceChangeWeekly(priceChangeWeekly * config.WEAPON_PRICE_WEEKLY_CHANGE_MODIFIER);

		String path = Configuration.WEAPON_IMAGE_FOLDER_PATH + String.valueOf(getClass().getSimpleName()) + ".jpg";
		setImage(new ImageIcon(getClass().getResource(path)));
	}

	/**
	 * Set the value of name
	 *
	 * @param newName the new value of name
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Get the value of name
	 *
	 * @return the value of name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Set the value of damageMultiplier
	 *
	 * @param newDamageMultiplier the new value of damageMultiplier
	 */
	public void setDamageMultiplier(float newDamageMultiplier) {
		damageMultiplier = newDamageMultiplier;
	}

	/**
	 * Get the value of damageMultiplier
	 *
	 * @return the value of damageMultiplier
	 */
	public float getDamageMultiplier() {
		return damageMultiplier;
	}

	/**
	 * Set the value of offenseBoost
	 *
	 * @param newOffenseBoost the new value of offenseBoost
	 */
	public void setOffenseBoost(int newOffenseBoost) {
		offenseBoost = newOffenseBoost;
	}

	/**
	 * Get the value of offenseBoost
	 *
	 * @return the value of offenseBoost
	 */
	public int getOffenseBoost() {
		return offenseBoost;
	}

	/**
	 * Set the value of defenseBoost
	 *
	 * @param newDefenseBoost the new value of defenseBoost
	 */
	public void setdefenseBoost(int newDefenseBoost) {
		defenseBoost = newDefenseBoost;
	}

	/**
	 * Get the value of defenseBoost
	 *
	 * @return the value of defenseBoost
	 */
	public int getDefenseBoost() {
		return defenseBoost;
	}

	/**
	 * Set the value of price
	 *
	 * @param newPrice the new value of price
	 */
	public void setPrice(float newPrice) {
		price = newPrice;
	}

	/**
	 * Get the value of price
	 *
	 * @return the value of price
	 */
	@Override
	public float getPrice() {
		return price;
	}

	/**
	 * Set the value of priceChangeWeekly
	 *
	 * @param newPriceChangeWeekly the new value of priceChangeWeekly
	 */
	public void setPriceChangeWeekly(float newPriceChangeWeekly) {
		priceChangeWeekly = newPriceChangeWeekly;
	}

	/**
	 * Get the value of priceChangeWeekly
	 *
	 * @return the value of priceChangeWeekly
	 */
	public float getPriceChangeWeekly() {
		return priceChangeWeekly;
	}

	/**
	 * Set the value of image
	 *
	 * @param newImage the new value of image
	 */
	public void setImage(ImageIcon newImage) {
		image = newImage;
	}

	/**
	 * Get the value of image
	 *
	 * @return the value of image
	 */
	@Override
	public ImageIcon getImage() {
		return image;
	}

	/**
	 * returns true if this is the default weapon that all champions should equip on
	 * creation.
	 *
	 * @return true if this is the default weapon for champions on creation
	 */
	public boolean isDefaultWeapon() {
		return isDefaultWeapon;
	}

	/**
	 * Set whether this weapon is marked as the default weapon
	 *
	 * @param isDefaultWeapon true if setting this as the default weapon
	 */
	public void setDefaultWeapon(boolean isDefaultWeapon) {
		this.isDefaultWeapon = isDefaultWeapon;
	}

	/**
	 * Buy the weapon by adding it to the given team and deducting the weapon price.
	 *
	 * @param team the team that wants to buy the weapon
	 * @throws InsufficientFundsException if the team cannot afford this weapon
	 * @throws FullTeamException          if the team weapon list is already full
	 */
	@Override
	public void buy(Team team) throws InsufficientFundsException, FullTeamException {
		team.removeMoney(getPrice());
		try {
			team.addWeapon(clone());
		} catch (FullTeamException e) {
			team.addMoney(getPrice());
			throw new FullTeamException(e.getMessage());
		}
	}

	/**
	 * Sell the weapon by removing it from the given team and refunding the weapon
	 * price.
	 *
	 * @param team the team that contains this weapon
	 */
	@Override
	public void sell(Team team) {
		team.removeWeapon(this);
		team.addMoney(getPrice());
	}

	/**
	 * Returns a string representation of the Weapon.
	 *
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
	 *
	 * @return the clone of the weapon
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
