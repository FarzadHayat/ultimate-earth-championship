package model;

import javax.swing.ImageIcon;

import exception.FullTeamException;
import exception.IllegalPurchaseException;
import exception.InsufficientFundsException;
import manager.GameManager;
import weapons.Fists;

/**
 * Champion Class, responsible for data and functionality related to each
 * individual champion competing.
 */
public abstract class Champion implements Purchasable, Cloneable {

	private Configuration config = Configuration.getInstance();
	private GameManager manager = GameManager.getInstance();

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
	 * The factor that the price multiplies by each week
	 */
	private float priceChangeWeekly;

	/**
	 * The weapon currently assigned to the champion, fists by default
	 */
	private Weapon weapon;

	/**
	 * The image icon for the champion
	 */
	private ImageIcon image;

	/**
	 * The lane that the champion is position in, used during Live Matches
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

	/**
	 * Constructor for champion Class
	 *
	 * @param name              The name of the champion
	 * @param staminaBoost      Stat which modifies the maximum stamina of the
	 *                          champion
	 * @param regenBoost        Stat which modifies the regen of the champion
	 * @param offenseBoost      Modifies the offense stat of the champion
	 * @param defenseBoost      Modifies the defense stat of the champion
	 * @param price             The champions price
	 * @param priceChangeWeekly The amount at which the champion's price changes
	 *                          each week once purchased
	 */
	public Champion(String name, int staminaBoost, int regenBoost, int offenseBoost, int defenseBoost, float price,
			float priceChangeWeekly) {
		this.name = name;

		this.maxStamina = config.MAX_STAMINA_DEFAULT + (config.SKILL_STAMINA_INCREMENT * staminaBoost);
		this.stamina = this.maxStamina;

		this.regen = config.REGEN_DEFAULT + (config.SKILL_REGEN_INCREMENT * regenBoost);

		this.offense = config.SKILL_DEFAULT_OFFENSE + (1 * offenseBoost);
		this.defense = config.SKILL_DEFAULT_OFFENSE + (1 * defenseBoost);

		this.price = price * config.CHAMPION_PRICE_MODIFIER;
		this.priceChangeWeekly = priceChangeWeekly * config.CHAMPION_PRICE_WEEKLY_CHANGE_MODIFIER;

		this.weapon = new Fists(); // Created champions should start with the fists weapon

		String path = Configuration.CHAMPION_IMAGE_FOLDER_PATH + String.valueOf(getClass().getSimpleName()) + ".png";
		image = new ImageIcon(path);

		level = 1;
		currentXP = 0f;

		maxXP = config.XP_DEFAULT_MAX;
	}

	/**
	 * Gets name of champion
	 *
	 * @return the name of the champion
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the champion
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the stamina of the champion
	 *
	 * @return the stamina of the champion
	 */
	public float getStamina() {
		return stamina;
	}

	/**
	 * Sets the stamina of the champion to a specific amount
	 *
	 * @param newStamina the value stamina should be updated to
	 */
	public void setStamina(float newStamina) {
		stamina = newStamina;
	}

	/**
	 * Changes the stamina of the champion by a specific amount
	 *
	 * @param staminaChange the amount added to stamina, clamped by maxStamina
	 */
	public void addStamina(float staminaChange) {
		stamina += staminaChange;

		if (stamina > maxStamina) {
			stamina = maxStamina;
		}
	}

	/**
	 * Gets the maximum stamina of the champion
	 *
	 * @return the maximum stamina of the champion
	 */
	public float getMaxStamina() {
		return maxStamina;
	}

	/**
	 * Adds to the champions maximum stamina by a provided amount
	 *
	 * @param staminaChange amount to be added to the maximum stamina
	 */
	public void addMaxStamina(float staminaChange) {
		maxStamina += staminaChange;
	}

	/**
	 * Gets the champions regen
	 *
	 * @return the champions regen
	 */
	public float getRegen() {
		return regen;
	}

	/**
	 * Increases the champions regen
	 *
	 * @param amount The amount to increase champions regen
	 */
	public void changeRegen(float amount) {
		regen += amount;
	}

	/**
	 * Gets the offense stat
	 *
	 * @return The offense stat
	 */
	public int getOffense() {
		return offense + weapon.getOffenseBoost();
	}

	/**
	 * Changes the offense stat
	 *
	 * @param change amount to add to the offense stat
	 */
	public void changeOffense(int change) {
		offense += change;
	}

	/**
	 * Gets the defense stat
	 *
	 * @return the defense stat
	 */
	public int getDefense() {
		return defense + weapon.getDefenseBoost();
	}

	/**
	 * Adds to the defense stat
	 *
	 * @param change the amount to add to the defense stat
	 */
	public void changeDefense(int change) {
		defense += change;
	}

	/**
	 * Gets the champions current level
	 *
	 * @return the champions level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gives the champion a certain amount of XP, then checks if the champion has
	 * leveled up
	 *
	 * @param amount of XP to add
	 */
	public void giveXP(float amount) {
		currentXP += amount;
		checkForLevelUp();
	}

	/**
	 * Set the lane of this champion on the board.
	 *
	 * @param lane the new lane to set
	 */
	public void setLane(int lane) {
		this.lane = lane;
	}

	/**
	 * Get the lane that this champion on the grid.
	 *
	 * @return the value of lane
	 */
	public int getLane() {
		return lane;
	}

	/**
	 * Set the position of this champion in their lane.
	 *
	 * @param position the new position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Get the position of this champion in their lane.
	 *
	 * @return the position of this champion
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Return whether this champion is the flag carrier.
	 *
	 * @return true if this champion is the flag carrier
	 */
	public boolean isFlagCarrier() {
		return flagCarrier;
	}

	/**
	 * Set the value of flagCarrier
	 *
	 * @param flagCarrier the new value of flagCarrier
	 */
	public void setFlagCarrier(boolean flagCarrier) {
		this.flagCarrier = flagCarrier;
	}

	/**
	 * Checks to see if the champion has accumulated enough xp to level up
	 */
	private void checkForLevelUp() {
		if (currentXP > maxXP) {
			levelUp();
		}
	}

	/**
	 * Levels up the champion
	 */
	private void levelUp() {
		level++;
		currentXP -= maxXP;

		maxXP = maxXP * config.XP_INCREMENT_MODIFIER;

		manager.championLevelUp(this);
	}

	/**
	 * Level up the given stat by the default level up increase set in config.
	 *
	 * @param stat the stat to level up
	 */
	public void applyLevelUp(LevelUpStat stat) {
		switch (stat) {
		case STAMINA:
			addMaxStamina(config.LEVEL_UP_STAMINA_INCREASE);
			break;
		case REGEN:
			changeRegen(config.LEVEL_UP_REGEN_INCREASE);
			break;
		case OFFENSE:
			changeOffense(config.LEVEL_UP_OFFENSE_INCREASE);
			break;
		case DEFENSE:
			changeDefense(config.LEVEL_UP_DEFENSE_INCREASE);
			break;
		}

		// Check for level up again.
		// This is to prevent edge cases in which a champion gains enough XP to level up
		// multiple times.
		checkForLevelUp();
	}

	/**
	 * Get the value of price.
	 *
	 * @return the value of price
	 */
	@Override
	public float getPrice() {
		return price;
	}

	/**
	 * Set the value of price.
	 *
	 * @param price the new value of price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Get the value of priceChangeWeekly.
	 *
	 * @return the value of priceChangeWeekly
	 */
	public float getPriceChangeWeekly() {
		return priceChangeWeekly;
	}

	/**
	 * Set the value of priceChangeWeekly.
	 *
	 * @param newWeeklyPrice the new value of priceChangeWeekly to set
	 */
	public void setPriceChangeWeekly(float newWeeklyPrice) {
		priceChangeWeekly = newWeeklyPrice;
	}

	/**
	 * Multiply the price by the weekly price change factor.
	 */
	public void applyWeeklyPriceChange() {
		price = price * priceChangeWeekly;
	}

	/**
	 * Returns the damage dealt by the champion in an attack
	 *
	 * @return the damage dealt by the champion in an attack
	 */
	public float getDamage() {
		return (((getOffense() * config.OFFENSE_STAT_DAMAGE_MULTIPLER) + config.DAMAGE_BASE)
				* weapon.getDamageMultiplier());
	}

	/**
	 * Returns the champions weapon
	 *
	 * @return The champions weapon
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * Set the champion weapon to the given weapon
	 *
	 * @param weapon the new weapon to set
	 */
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	/**
	 * Removes the champions weapon, replacing it with the default weapon.
	 */
	public void removeWeapon() {
		weapon = new Fists();
	}

	/**
	 * Gives the champion a new weapon
	 *
	 * @param newWeapon The weapon given to this champion
	 */
	public void addWeapon(Weapon newWeapon) {
		weapon = newWeapon;
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
	 * Buy the champion by adding it to the given team and deducting the champion
	 * price.
	 *
	 * @param team the team that wants to buy the champion
	 * @throws InsufficientFundsException if the team cannot afford this champion
	 * @throws FullTeamException          if the team champion list is already full
	 * @throws IllegalPurchaseException   if the team has already purchased a
	 *                                    champion this week
	 */
	@Override
	public void buy(Team team) throws InsufficientFundsException, FullTeamException, IllegalPurchaseException {
		team.removeMoney(getPrice());
		try {
			if (team.isWeeklyChampionPurchased()) {
				team.addMoney(getPrice());
				throw new IllegalPurchaseException(team.getName() + " already purchased a champion this week!");
			}
			team.addChampion(this);
			team.setWeeklyChampionPurchased(true);
		} catch (FullTeamException e) {
			team.addMoney(getPrice());
			throw new FullTeamException(e.getMessage());
		}
	}

	/**
	 * Sell the champion by removing it from the given team and refunding the
	 * champion price.
	 *
	 * @param team the team that contains this champion
	 */
	@Override
	public void sell(Team team) {
		team.removeChampion(this);
		team.addMoney(getPrice());
	}

	/**
	 * Create a clone of the Champion with the same stats and a cloned version of
	 * the weapon.
	 *
	 * @return the clone of the champion
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

	/**
	 * Returns a string representation of the champion to display its stats.
	 *
	 * @return the string representation of the champion
	 */
	@Override
	public String toString() {
		return String.format(
				"Champion [name=%s, stamina=%s, maxStamina=%s, regen=%s, offense=%s, defense=%s, level=%s, currentXP=%s, maxXP=%s, price=%s, priceChangeWeekly=%s, weapon=%s]",
				name, stamina, maxStamina, regen, offense, defense, level, currentXP, maxXP, price, priceChangeWeekly,
				weapon);
	}

}
