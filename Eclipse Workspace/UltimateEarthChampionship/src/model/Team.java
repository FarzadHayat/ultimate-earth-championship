package model;

import java.util.ArrayList;
import java.util.Random;

import exception.FullTeamException;
import exception.InsufficientFundsException;
import weapons.Fists;

/**
 * Represents a team in the game. A team has a name, money, score, list of
 * champion, and list of weapons. If the team is an AI team, it will also have
 * an aggression level. Each team can only purchase one champion per week. The
 * chosen champions and chosen weapons lists are to be used during match setup.
 */
public class Team {

	private Configuration config = Configuration.getInstance();

	/**
	 * the name of the team.
	 */
	private String name;

	/**
	 * True if this is the player team
	 */
	private boolean isPlayer;

	/**
	 * champions of the team
	 */
	private ArrayList<Champion> champions = new ArrayList<>();

	/**
	 * chosen champions of the team
	 */
	private ArrayList<Champion> chosenChampions = new ArrayList<>();

	/**
	 * weapons of the team
	 */
	private ArrayList<Weapon> weapons = new ArrayList<>();

	/**
	 * chosen weapons of the team
	 */
	private ArrayList<Weapon> chosenWeapons = new ArrayList<>();

	/**
	 * The teams money
	 */
	private float money;

	/**
	 * The team's score
	 */
	private int score;

	/**
	 * The team's aggression, on a scale of -50 (very timid) to 50 (very aggressive)
	 * Aggression determines how likely a team is to get their champions to attack
	 * rather than wait
	 */
	private int aggression;

	/**
	 * True if the team has purchased a champion this week.
	 */
	private boolean weeklyChampionPurchased = false;

	/**
	 * Constructor of team. Initialises the starting values of the team.
	 *
	 * @param isPlayer          is this team a player
	 * @param startingChampions list of the four starting champions
	 */
	public Team(boolean isPlayer, String name, ArrayList<Champion> startingChampions) {
		this.isPlayer = isPlayer;
		this.name = name;

		this.money = config.STARTING_MONEY;

		score = 0;

		if (startingChampions.size() != Configuration.NUM_CHOSEN_CHAMPIONS && Configuration.DEBUG) {
			System.out.println(
					String.format("WARNING: Starting champions size is not %s!", Configuration.NUM_CHOSEN_CHAMPIONS));
		}
		champions = startingChampions;

		Random random = new Random();
		int aggression = random.nextInt(config.AGRESSION_RANDOM_UPPER_BOUND - config.AGRESSION_RANDOM_LOWER_BOUND)
				+ config.AGRESSION_RANDOM_LOWER_BOUND;

		this.aggression = aggression + config.AI_AGRESSION_BOOST;
	}

	/**
	 * Gets the team name
	 *
	 * @return the team name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the team name to the given name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns whether this team is the player team.
	 *
	 * @return true if this is the player team
	 */
	public boolean isPlayerTeam() {
		return isPlayer;
	}

	/**
	 * gets the current money of the team.
	 *
	 * @return the money value
	 */
	public float getMoney() {
		return money;
	}

	/**
	 * Adds amount to total money
	 *
	 * @param amount the amount of money to add
	 */
	public void addMoney(float amount) {
		money += amount;
	}

	/**
	 * Removes amount from total money
	 *
	 * @param amount the amount of money to remove
	 * @throws InsufficientFundsException if team has less money than this amount
	 */
	public void removeMoney(float amount) throws InsufficientFundsException {
		if (money < amount) {
			throw new InsufficientFundsException("You do not have enough money to perform this action!");
		}
		money -= amount;
	}

	/**
	 * Returns whether the team has enough money to afford a purchase of this value.
	 *
	 * @param purchaseCost the cost of a hypothetical purchase
	 * @return true if the team has at least this much money
	 */
	public boolean hasMoney(float purchaseCost) {
		return (money >= purchaseCost);
	}

	/**
	 * Get the current score of the team.
	 *
	 * @return the score value
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Adds this amount to score
	 *
	 * @param amount the amount to be added to the score
	 */
	public void addScore(int amount) {
		score += amount;
	}

	/**
	 * Returns the champions in the team.
	 *
	 * @return the list of champions
	 */
	public ArrayList<Champion> getChampions() {
		return champions;
	}

	/**
	 * Add the given champion to champions.
	 *
	 * @param champion the new champion to be added
	 * @throws FullTeamException if the champions list are already full
	 */
	public void addChampion(Champion champion) throws FullTeamException {
		if (champions.size() >= Configuration.NUM_CHAMPIONS) {
			throw new FullTeamException("Reached team max champions limit!");
		}
		champions.add(champion);
	}

	/**
	 * Remove the given champion from the team.
	 *
	 * @param champion the champion to remove
	 */
	public void removeChampion(Champion champion) {
		champions.remove(champion);
	}

	/**
	 * Get the chosen champions of the team.
	 *
	 * @return the list of chosen champions
	 */
	public ArrayList<Champion> getChosenChampions() {
		return chosenChampions;
	}

	/**
	 * Set the chosen champions.
	 *
	 * @param chosenChampions the list of champions to set
	 */
	public void setChosenChampions(ArrayList<Champion> chosenChampions) {
		this.chosenChampions = chosenChampions;
	}

	/**
	 * Get the chosen weapons of the team.
	 *
	 * @return the list of chosen weapons
	 */
	public ArrayList<Weapon> getChosenWeapons() {
		return chosenWeapons;
	}

	/**
	 * Set the chosen weapons.
	 *
	 * @param chosenWeapons the list of weapons to set
	 */
	public void setChosenWeapons(ArrayList<Weapon> chosenWeapons) {
		this.chosenWeapons = chosenWeapons;
	}

	/**
	 * Get the aggression value of the team.
	 *
	 * @return the aggression value
	 */
	public int getAgression() {
		return aggression;
	}

	/**
	 * Gets a random champion from the team.
	 *
	 * @return the random champion
	 */
	public Champion randomChampion() {
		Random random = new Random();
		int champInt = random.nextInt(getChampions().size());
		return getChampions().get(champInt);
	}

	/**
	 * Adds a champion to the chosen champions.
	 *
	 * @param champion the champion to be added to chosen champions
	 * @throws FullTeamException if chosen champions is already full
	 */
	public void addChosenChampion(Champion champion) throws FullTeamException {
		if (chosenChampions.size() >= Configuration.NUM_CHOSEN_CHAMPIONS) {
			throw new FullTeamException("Reached team max chosen champions limit!");
		}

		chosenChampions.add(champion);
	}

	/**
	 * Remove a champion from chosen champions if it exists.
	 *
	 * @param champion the champion to be removed from chosen champions
	 */
	public void removeChosenChampion(Champion champion) {
		chosenChampions.remove(champion);
	}

	/**
	 * Adds a weapon to the chosen weapons.
	 *
	 * @param weapon the weapon to be added to chosen weapons
	 * @throws FullTeamException if the chosen weapons is already full
	 */
	public void addChosenWeapon(Weapon weapon) throws FullTeamException {
		if (chosenWeapons.size() >= Configuration.NUM_CHOSEN_CHAMPIONS) {
			throw new FullTeamException("Reached team max chosen weapons limit!");
		}

		chosenWeapons.add(weapon);
	}

	/**
	 * Remove the given weapon from chosen weapons if it exists.
	 *
	 * @param weapon the weapon to be removed
	 */
	public void removeChosenWeapon(Weapon weapon) {
		chosenWeapons.remove(weapon);
	}

	/**
	 * Gets weapons in the team's inventory
	 *
	 * @return the list of weapons
	 */
	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}

	/**
	 * Adds the given weapon to the weapon inventory.
	 *
	 * @throws FullTeamException if the weapon inventory is already full
	 */
	public void addWeapon(Weapon weapon) throws FullTeamException {
		if (weapons.size() == Configuration.NUM_WEAPONS) {
			throw new FullTeamException("Reached team max weapon limit!");
		}
		weapons.add(weapon);
	}

	/**
	 * Removes the given weapon if it exists in the team.
	 *
	 * @param weapon the weapon to remove from the weapons inventory
	 */
	public void removeWeapon(Weapon weapon) {
		weapons.remove(weapon);
	}

	/**
	 * Gets the weekly champion purchased value
	 *
	 * @return true if the team has purchased a champion this week
	 */
	public boolean isWeeklyChampionPurchased() {
		return weeklyChampionPurchased;
	}

	/**
	 * set the weekly champion purchased value to the given value.
	 *
	 * @param weeklyChampionPurchased the weekly champion purchased value to set.
	 */
	public void setWeeklyChampionPurchased(boolean weeklyChampionPurchased) {
		this.weeklyChampionPurchased = weeklyChampionPurchased;
	}

	/**
	 * Randomly selects champions and weapons.
	 */
	public void randomlySelectPurchasables() {
		randomlySelectChampions();
		randomlySelectWeapons();
		assignChosenWeapons();
	}

	/**
	 * Randomly selects champions from the team's champions. Assumes the team has
	 * enough champions to start a match.
	 */
	private void randomlySelectChampions() {
		ArrayList<Champion> championsLeft = new ArrayList<>(champions);
		while (chosenChampions.size() < Configuration.NUM_CHOSEN_CHAMPIONS) {
			Random random = new Random();
			int index = random.nextInt(championsLeft.size());
			Champion randomChampion = championsLeft.remove(index);
			chosenChampions.add(randomChampion);
		}
	}

	/**
	 * Randomly selects as many weapons as possible from the team's weapons up to
	 * the team size limit.
	 */
	private void randomlySelectWeapons() {
		int numWeaponsAvailable = Integer.min(weapons.size(), Configuration.NUM_CHOSEN_CHAMPIONS);
		ArrayList<Weapon> weaponsLeft = new ArrayList<>(weapons);
		while (chosenWeapons.size() < numWeaponsAvailable) {
			Random random = new Random();
			int index = random.nextInt(weaponsLeft.size());
			Weapon randomWeapon = weaponsLeft.remove(index);
			chosenWeapons.add(randomWeapon);
		}
	}

	/**
	 * Random assign the chosen weapons to chosen champions. If there is not enough
	 * chosen weapons, some champion won't get assigned a weapon and will keep the
	 * default weapon.
	 */
	public void assignChosenWeapons() {
		for (int i = 0; i < chosenWeapons.size(); i++) {
			Champion champion = chosenChampions.get(i);
			Weapon weapon = chosenWeapons.get(i);
			champion.setWeapon(weapon);
		}
	}

	/**
	 * Reset chosen champion and weapons.
	 */
	public void unselectPurchasables() {
		unassignChosenWeapons();
		unselectChampions();
		unselectWeapons();
	}

	/**
	 * Remove all champions from the chosen champions.
	 */
	public void unselectChampions() {
		chosenChampions.removeAll(chosenChampions);
	}

	/**
	 * Remove all weapons from the chosen weapons.
	 */
	public void unselectWeapons() {
		chosenWeapons.removeAll(chosenWeapons);
	}

	/**
	 * Reset chosen champions back to the default weapon.
	 */
	public void unassignChosenWeapons() {
		for (Champion champion : chosenChampions) {
			champion.setWeapon(new Fists());
		}
	}

	/**
	 * Reset weekly champion purchased back to false and rest up all champions back
	 * to pull stamina.
	 */
	public void rest() {
		setWeeklyChampionPurchased(false);
		for (Champion champion : champions) {
			champion.rest();
		}
	}

}
