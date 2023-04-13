package champion;

import main.Configuration;

/**
*
*@author Oliver Coates
*
*Champion Class, responsible for data and functionality related to each individual champion competing.
*
*/
public abstract class Champion {

	private Configuration config = Configuration.getInstance();
	
	// Name:
	
	/**
	 * Name of the champion
	 */
	private String name;
	
	/**
	 * Health of the champion
	 */
	private float health;
	
	/**
	 * The maximum health of the champion
	 */
	private float maxHealth;
	
	/**
	 * The champions stamina
	 */
	private float stamina;
	
	/**
	 * The champion's maximum stamina
	 */
	private float maxStamina;
	
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
	private boolean weapon;
	
	
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
	 * Gets the health of the champion
	 * @return the health of the champion
	 */
	public float getHealth()
	{
		return health;
	}
	
	/**
	 * Sets the health of the champion to a specific amount
	 * @param newHealth the value health should be updated to
	 */
	public void setHealth(float newHealth)
	{
		health = newHealth;
	}
	
	/**
	 * Changes the health of the champion by a specific amount
	 * @param healthChange the amount added to health, clamped by maxHealth
	 */
	public void addHealth(float healthChange)
	{
		health += healthChange;
		
		if (health > maxHealth)
		{
			health = maxHealth;
		}
	}
	
	/**
	 * Gets the maximum health of the champion
	 * @return the maximum health of the champion
	 */
	public float getMaxHealth()
	{
		return maxHealth;
	}
	
	/**
	 * Adds to the champions maximum health by a provided amount
	 * @param healthChange amount to be added to the maximum health
	 */
	public void addMaxHealth(float healthChange)
	{
		maxHealth += healthChange;
	}
	
	/**
	 * Gets the champions stamina
	 * @return the champions stamina
	 */
	public float getStamina()
	{
		return stamina;
	}
	
	/**
	 * Adds to the champions stamina
	 * @param amount to add to stamina, clamped by maxStamina
	 */
	public void addStamina(float staminaChange)
	{
		stamina += staminaChange;
		
		// Clamp to max Stamina
		if (stamina > maxStamina)
		{
			stamina = maxStamina;
		}
	}
	
	/**
	 * Gets the champions maximum stamina
	 * @return The champions maximum stamina
	 */
	public float getMaxStamina()
	{
		return maxStamina;
	}
	
	/**
	 * Changes the maximum stamina by a certain amount
	 * @param maxStaminaChange The amount to be added to the champions maximum stamina
	 */
	public void addMaxStamina(float maxStaminaChange)
	{
		maxStamina += maxStaminaChange;
	}
	
	/**
	 * Gets the offense stat
	 * @return The offense stat
	 */
	public int getOffense()
	{
		return offense;
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
		return defense;
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
	
	// CONSTRUCTOR:
	
	/**
	 * Constructor for champion Class
	 * @param name The name of the champion
	 * @param healthBoost Stat which modifies the maximum health of the champion
	 * @param maxStamina Stat which modifies the maximum stamina of the champion
	 * @param offense Modifies the offense stat of the champion
	 * @param defense Modifies the defense stat of the champion
	 * @param price The champions price
	 * @param priceChangeWeekly The amount at which the champion's price changes each week once purchased
	 */
	public Champion (String name, int healthBoost, int staminaBoost, int offenseBoost, int defenseBoost, float price, float priceChangeWeekly )
	{
		this.name = name;
		
		System.out.println("TODO: Setup Default stat values, and value increments as global variables and implement them into classes");
		
		// TODO: This should be:
		// this.maxHealth = MAX_HEALTH_DEFAULT * (HEALTH_STAT_INCREMENT * healthBoost);
		this.maxHealth = 100 + (10 * healthBoost);
		this.health = this.maxHealth;
		
		this.maxStamina = 30 + (5 * staminaBoost);
		this.stamina = this.maxStamina;
		
		this.offense = 1 + (1 * offenseBoost);
		this.defense = 1 + (1 * defenseBoost);
		
		this.price = price;
		this.priceChangeWeekly = priceChangeWeekly;
		
		this.weapon = false; // Created champions should start with the weapon status of null
		
		level = 1;
		currentXP = 0f;
		
		System.out.println("TODO: Add global maxXP modifier");
		maxXP = 100f;
	}
	
	// METHODS:
	
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
	
	/*
	 * TODO: Add weapon functionality
	 */
	public void getWeapon()
	{
		weapon = false;
		System.out.println("TODO: Weapon Functionality");
	}
	
	/**
	 * TODO: Add weapon functionality
	 */
	public void removeWeapon()
	{
		System.out.println("TODO: Weapon Functionality");
	}
	
	
	/**
	 * TODO: Add weapon functionality
	 */
	public void addWeapon()
	{
		System.out.println("TODO: Weapon Functionality");
	}

	// Constructor:
	/**
	 * Constructor for champion Class
	 * @param name The name of the champion
	 * @param healthBoost Stat which modifies the maximum health of the champion
	 * @param maxStamina Stat which modifies the maximum stamina of the champion
	 * @param offense Modifies the offense stat of the champion
	 * @param defense Modifies the defense stat of the champion
	 * @param price The champions price
	 * @param priceChangeWeekly The amount at which the champion's price changes each week once purchased
	 */
	public Champion (String name, int healthBoost, int staminaBoost, int offenseBoost, int defenseBoost, float price, float priceChangeWeekly )
	{
		this.name = name;
				
		this.maxHealth = config.MAX_HEALTH_DEFAULT + (config.SKILL_HEALTH_INCREMENT * healthBoost);
		this.health = this.maxHealth;
		
		this.maxStamina = config.MAX_STAMINA_DEFAULT + (config.SKILL_STAMINA_INCREMENT * staminaBoost);
		this.stamina = this.maxStamina;
		
		this.offense = config.SKILL_DEFAULT_OFFENSE + (1 * offenseBoost);
		this.defense = config.SKILL_DEFAULT_OFFENSE + (1 * defenseBoost);
		
		this.price = price * config.CHAMPION_PRICE_MODIFIER;
		this.priceChangeWeekly = priceChangeWeekly * config.CHAMPION_PRICE_WEEKLY_CHANGE_MODIFIER;
		
		this.weapon = false; // Created champions should start with the weapon status of null
		
		level = 1;
		currentXP = 0f;
		
		maxXP = config.XP_DEFAULT_MAX;
	}

}
