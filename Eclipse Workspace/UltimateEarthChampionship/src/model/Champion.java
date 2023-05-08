package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import exception.FullTeamException;
import exception.IllegalPurchaseException;
import exception.IncompleteTeamException;
import exception.InsufficientFundsException;
import weapons.Fists;

/**
*
*@author Oliver Coates
*
*Champion Class, responsible for data and functionality related to each individual champion competing.
*
*/
public abstract class Champion implements Purchasable, Cloneable {

	private Configuration config = Configuration.getInstance();
	
	/**
	 * Name of the champion
	 */
	private String name;
	
	/**
	 * Stamina of the champion
	 */
	private float stamina;
	
	/**
	 * The maximum stamina of the champion
	 */
	private float maxStamina;
	
	/**
	 * The champions regen
	 */
	private float regen;
	
	/**
	 * The Offense stat
	 */
	private int offense;
	
	/**
	 * The Defense stat
	 */
	private int defense;
	
	/**
	 * The champions level
	 */
	private int level;
	
	/*
	 * The champions current XP
	 */
	private float currentXP;
	/*
	 * The XP required for the champion to progress to the next level
	 */
	private float maxXP;
	
	/**
	 * The buy/sell price of the champion
	 */
	private float price;
	
	/**
	 * The amount that the price changes each week
	 */
	private float priceChangeWeekly;
	
	/**
	 * The weapon currently assigned to the champion, fists if null
	 */
	private Weapon weapon;
	
	/**
	 * The image icon for the champion
	 */
	private ImageIcon image;

	/**
	 * The lane that the champion is position in, used during LiveMatches
	 */
	private int lane;
	
	/**
	 * The position that the champion is in, used during Live Matches
	 */
	private int position;
	
	/**
	 * Whether this champion is carrying the flag for a liveMatch
	 */
	private boolean flagCarrier;
	
	// Constructor:
	/**
	 * Constructor for champion Class
	 * @param name The name of the champion
	 * @param staminaBoost Stat which modifies the maximum stamina of the champion
	 * @param regenBoost Stat which modifies the regen of the champion
	 * @param offense Modifies the offense stat of the champion
	 * @param defense Modifies the defense stat of the champion
	 * @param price The champions price
	 * @param priceChangeWeekly The amount at which the champion's price changes each week once purchased
	 */
	public Champion (String name, int staminaBoost, int regenBoost, int offenseBoost, int defenseBoost, float price, float priceChangeWeekly )
	{
		this.name = name;
				
		this.maxStamina = config.MAX_STAMINA_DEFAULT + (config.SKILL_STAMINA_INCREMENT * staminaBoost);
		this.stamina = this.maxStamina;
		
		this.regen = config.MAX_REGEN_DEFAULT + (config.SKILL_REGEN_INCREMENT * regenBoost);
		
		this.offense = config.SKILL_DEFAULT_OFFENSE + (1 * offenseBoost);
		this.defense = config.SKILL_DEFAULT_OFFENSE + (1 * defenseBoost);
		
		this.price = price * config.CHAMPION_PRICE_MODIFIER;
		this.priceChangeWeekly = priceChangeWeekly * config.CHAMPION_PRICE_WEEKLY_CHANGE_MODIFIER;
		
		this.weapon = new Fists(); // Created champions should start with the fists weapon
		
		try {
			String path = Configuration.CHAMPION_IMAGE_FOLDER_PATH 
					+ String.valueOf(getClass().getSimpleName()) + ".png";
            image = new ImageIcon(ImageIO.read(new File(path)));

        } catch (IOException e) {
        	if (Configuration.DEBUG) {
        		System.out.println("Could not find image file for " + getClass().getSimpleName() + " object!");
        	}
        }
		
		level = 1;
		currentXP = 0f;
		
		maxXP = config.XP_DEFAULT_MAX;
	}

	
	
	// GETTERS/SETTERS:
	
	/**
	 * Gets name of champion
	 * @return the name of the champion
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Sets the name of the champion
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	/**
	 * Gets the stamina of the champion
	 * @return the stamina of the champion
	 */
	public float getStamina()
	{
		return stamina;
	}
	
	/**
	 * Sets the stamina of the champion to a specific amount
	 * @param newStamina the value stamina should be updated to
	 */
	public void setStamina(float newStamina)
	{
		stamina = newStamina;
	}
	
	/**
	 * Changes the stamina of the champion by a specific amount
	 * @param staminaChange the amount added to stamina, clamped by maxStamina
	 */
	public void addStamina(float staminaChange)
	{
		stamina += staminaChange;
		
		if (stamina > maxStamina)
		{
			stamina = maxStamina;
		}
	}
	
	/**
	 * Gets the maximum stamina of the champion
	 * @return the maximum stamina of the champion
	 */
	public float getMaxStamina()
	{
		return maxStamina;
	}
	
	/**
	 * Adds to the champions maximum stamina by a provided amount
	 * @param staminaChange amount to be added to the maximum stamina
	 */
	public void addMaxStamina(float staminaChange)
	{
		maxStamina += staminaChange;
	}
	
	/**
	 * Gets the champions regen
	 * @return the champions regen
	 */
	public float getRegen()
	{
		return regen;
	}
	
	/**
	 * Gets the offense stat
	 * @return The offense stat
	 */
	public int getOffense()
	{
		return offense + weapon.getOffenseBoost();
	}
	
	/**
	 * Changes the offense stat
	 * @param change amount to add to the offense stat
	 */
	public void changeOffense(int change)
	{
		offense += change;
	}
	
	/**
	 * Gets the defense stat
	 * @return the defense stat
	 */
	public int getDefense()
	{
		return defense + weapon.getDefenseBoost();
	}
	
	/**
	 * Adds to the defense stat
	 * @param change the amount to add to the defense stat
	 */
	public void changeDefense(int change)
	{
		defense += change;
	}
	
	/**
	 * Gets the champions current level
	 * @return the champions level
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * Gives the champion a certain amount of XP, then checks if the champion has leveled up
	 * @param amount XP to add
	 */
	public void giveXP(float amount)
	{
		currentXP += amount;
		checkForLevelUp();
	}
	
	public void setLane(int lane)
	{
		this.lane = lane; 
	}
	
	public int getLane()
	{
		return lane;
	}
	
	public void setPosition(int position)
	{
		this.position = position;
	}
	
	public int getPosition()
	{
		return position;
	}
	
	public boolean isFlagCarrier()
	{
		return flagCarrier;
	}
	
	public void setFlagCarrier(boolean flagCarrier)
	{
		this.flagCarrier = flagCarrier;
	}
	
	
	
	
	/**
	 * Checks to see if currentXP > maxXP, if so, the champion levels up
	 */
	private void checkForLevelUp()
	

	{
		if (currentXP > maxXP)
		{
			levelUp();
		}
	}
	
	/**
	 * Levels up the champion
	 */
	private void levelUp()
	{
		level++;
		currentXP -= maxXP;
		
		maxXP = maxXP * config.XP_INCREMENT_MODIFIER;
		
		System.out.println("TODO: Finish Level up Function");
		
		// Check for level up again.
		// This is to prevent edge cases in which a champion gains enough XP to level up multiple times.
		checkForLevelUp(); 
	}
	
	//TODO: Move the following functions to purchasable interface:
	public float getPrice()
	{
		return price;
	}
	public void changePrice(float newPrice)
	{
		price = newPrice;
	}
	public float getPriceChangeWeekly()
	{
		return priceChangeWeekly;
	}	
	public void setPriceChangeWeekly(float newWeeklyPrice)
	{
		priceChangeWeekly = newWeeklyPrice;
	}
	public void applyWeeklyPriceChange()
	{
		price = price * priceChangeWeekly;
	}
	// ---------
	
	/**
	 * Returns the damage dealt by the champion in an attack
	 * @return the damage dealt by the champion in an attack
	 */
	public float getDamage()
	{
		return (((getOffense() * config.OFFENSE_STAT_DAMAGE_MULTIPLER )
				+ config.DAMAGE_BASE)
				* weapon.getDamageMultiplier());
	}
	
	/**
	 * Returns the champions weapon
	 * @return The champions weapon
	 */
	public Weapon getWeapon()
	{
		return weapon;
	}
	
	/**
	 * Set the champion weapon to the given weapon
	 * @return weapon the new weapon to set
	 */
	public void setWeapon(Weapon weapon)
	{
		this.weapon = weapon;
	}
	
	/**
	 * Removes the champions weapon, replacing it with their fists
	 */
	public void removeWeapon()
	{
		weapon = new Fists();
	}
	
	/**
	 * Gives the champion a new weapon
	 * @param newWeapon The weapon given to this champion
	 */
	public void addWeapon(Weapon newWeapon)
	{
		weapon = newWeapon;
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

	public void buy(Team team) throws InsufficientFundsException, FullTeamException, IllegalPurchaseException {
		team.removeMoney(getPrice());
		try {
			if (team.isWeeklyChampionPurchased()) {
				throw new IllegalPurchaseException(team.getName() + " already purchased a champion this week!");
			}
			team.addChampion(this);
			team.setWeeklyChampionPurchased(true);
		}
		catch (FullTeamException e) {
			team.addMoney(getPrice());
			throw new FullTeamException(e.getMessage());
		}
		catch(IllegalPurchaseException e) {
			team.addMoney(getPrice());
			throw new IllegalPurchaseException(e.getMessage());
		}
	}
	
	public void sell(Team team) throws IncompleteTeamException {
		team.removeChampion(this);
		team.addMoney(getPrice());
	}
	
	/**
	 * Create a clone of the Champion with the same stats.
	 */
	@Override
	public Champion clone() {
        try {
        	Champion champion = (Champion) super.clone();
        	champion.setWeapon(champion.getWeapon().clone());
            return champion;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	@Override
	public String toString() {
		return String.format(
				"Champion [name=%s, stamina=%s, maxStamina=%s, regen=%s, offense=%s, defense=%s, level=%s, currentXP=%s, maxXP=%s, price=%s, priceChangeWeekly=%s, weapon=%s]",
				name, stamina, maxStamina, regen, offense, defense, level, currentXP, maxXP, price,
				priceChangeWeekly, weapon);
	}
	
}
