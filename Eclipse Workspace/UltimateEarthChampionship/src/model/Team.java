package model;

import java.util.ArrayList;
import java.util.Random;

import exception.FullTeamException;
import exception.IncompleteTeamException;
import exception.InsufficientFundsException;
import manager.GameManager;
import weapons.Fists;

public class Team {

	private Configuration config = Configuration.getInstance();
	
	/**
	 * the name of the team.
	 */
	private String name;
	
	/**
	 *  Is true if this team is the player team
	 */
	private boolean isPlayer;
	
	/**
	 * champions of the team
	 */
	private ArrayList<Champion> champions = new ArrayList<Champion>();
	
	/**
	 * chosen champions of the team
	 */
	private ArrayList<Champion> chosenChampions = new ArrayList<Champion>();
	
	/**
	 * chosen weapons of the team
	 */
	private ArrayList<Weapon> chosenWeapons = new ArrayList<Weapon>();
	
	/**
	 * weapons of the team
	 */
	private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	
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
	 * Agression determines how likely a team is to get their champions to attack rather than wait
	 */
	private int aggression;
	
	private boolean weeklyChampionPurchased = false;
	
	/**
	 * Constructor of team
	 * @param isPlayer is this team a player
	 * @param startingChampions ArrayList of the four starting champions
	 */
	public Team(boolean isPlayer, String name, ArrayList<Champion> startingChampions)
	{
		this.isPlayer = isPlayer;
		this.name = name;
		
		this.money = config.STARTING_MONEY;
		
		score = 0;
		
		if (startingChampions.size() != config.NUM_CHOSEN_CHAMPIONS)
		{
			System.out.println(String.format("EXCEPTION: Starting champions size is not %s!", config.NUM_CHOSEN_CHAMPIONS));
			//TODO: Throw an exception
			return;
		}
		champions = startingChampions;
		
		Random random = new Random();
		int aggression = random.nextInt(config.AGRESSION_RANDOM_UPPER_BOUND - config.AGRESSION_RANDOM_LOWER_BOUND) + config.AGRESSION_RANDOM_LOWER_BOUND;

		this.aggression = aggression + config.AI_AGRESSION_BOOST;
	}

	/**
	 * Gets the team name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the team name to the given name.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns whether this team is the player team
	 * @return true if this is the player team.
	 */
	public boolean isPlayerTeam()
	{
		return isPlayer;
	}
	
	public float getMoney()
	{
		return money;
	}
	
	/**
	 * Adds amount to total money
	 * @param amount the amount of money to add
	 */
	public void addMoney(float amount)
	{
		money += amount;
	}
	
	/**
	 * Removes amount from total money
	 * @param amount the amount of money to remove
	 * @throws InsufficientFundsException if team has less money than the given amount
	 */
	public void removeMoney(float amount) throws InsufficientFundsException {
		if (money < amount)
		{
			throw new InsufficientFundsException("You do not have enough money to perform this action!");
		}
		money -= amount;
	}
	
	/**
	 * Returns true if the team has enough money to buy a champion
	 * @param purchaseCost the cost of a hypothetical purchase
	 * @return whether the team can afford this hypothetical purchase
	 */
	public boolean hasMoney(float purchaseCost)
	{
		return (money >= purchaseCost);
	}

	public int getScore()
	{
		return score;
	}
	
	/**
	 * Adds an amount to score
	 * @param amount the amount to be added to score
	 */
	public void addScore(int amount)
	{
		score += amount;
	}	
	
	/**
	 * Returns the champions in the team
	 * @return the list of champions in the team
	 */
	public ArrayList<Champion> getChampions()
	{	
		return champions;
	}
	
	/**
	 * Add a champion to the team.
	 * @param newChampion the new champion to be added
	 * @throws FullTeamException if team champions list are already full
	 */
	public void addChampion(Champion newChampion) throws FullTeamException
	{
		if (champions.size() >= config.NUM_CHAMPIONS) {
			throw new FullTeamException("Reached team max champions limit!");
		}
		champions.add(newChampion);
	}

	/**
	 * Removes a champion from the team
	 * @param toRemove the champion to remove
	 * @throws IncompleteTeamException if the team is already at the minimum number of champions allowed
	 */
	public void removeChampion(Champion toRemove) throws IncompleteTeamException
	{
		if (champions.size() <= config.NUM_CHOSEN_CHAMPIONS) {
			throw new IncompleteTeamException("Must have at least " + config.NUM_CHOSEN_CHAMPIONS + " champions in your team!");
		}
		if (champions.contains(toRemove))
		{
			champions.remove(toRemove);
		}
		else {
			System.out.println("EXCEPTION: Champion to remove not in team!");
			//TODO: Throw an exception
			return;
		}
	}

	public ArrayList<Champion> getChosenChampions() {
		return chosenChampions;
	}

	public void setChosenChampions(ArrayList<Champion> chosenChampions) {
		this.chosenChampions = chosenChampions;
	}
	
	public ArrayList<Weapon> getChosenWeapons() {
		return chosenWeapons;
	}

	public void setChosenWeapons(ArrayList<Weapon> chosenWeapons) {
		this.chosenWeapons = chosenWeapons;
	}
	
	public int getAgression()
	{
		return aggression;
	}

	/**
	 * Gets a random champion from the team
	 * @return a random champion
	 */
	public Champion randomChampion()
	{
		Random random = new Random();
		int champInt = random.nextInt(getChampions().size());
		return getChampions().get(champInt);
	}

	/**
	 * Adds a champion to the chosen champions
	 * @param champion the champion to be added to chosenChampions
	 * @throws FullTeamException if the chosen champions list is already full
	 */
	public void addChosenChampion(Champion champion) throws FullTeamException
	{
		if (chosenChampions.size() >= config.NUM_CHOSEN_CHAMPIONS)
		{
			throw new FullTeamException("Reached team max chosen champions limit!");
		}
		
		chosenChampions.add(champion);
	}
	
	/**
	 * Remove a champion to chosen champions
	 * @param champion the champion to be removed from chosenChampions
	 */
	public void removeChosenChampion(Champion champion)
	{	
		chosenChampions.remove(champion);
	}
	
	/**
	 * Adds a weapon to the chosen weapons
	 * @param weapon the weapon to be added to chosenWeapons
	 * @throws FullTeamException if the chosen weapons list is already full
	 */
	public void addChosenWeapon(Weapon weapon) throws FullTeamException
	{
		if (chosenWeapons.size() >= config.NUM_CHOSEN_CHAMPIONS)
		{
			throw new FullTeamException("Reached team max chosen weapons limit!");
		}
		
		chosenWeapons.add(weapon);
	}
	
	/**
	 * Remove a weapon to chosen weapons
	 * @param weapon the weapon to be removed from chosenWeapons
	 */
	public void removeChosenWeapon(Weapon weapon)
	{	
		chosenWeapons.remove(weapon);
	}

	/**
	 * Gets weapons in the team
	 * @return list of weapons
	 */
	public ArrayList<Weapon> getWeapons()
	{
		return weapons;
	}

	/**
	 * Adds a weapon to weapons
	 * @throws FullTeamException if weapons list is already full
	 */
	public void addWeapon(Weapon weapon) throws FullTeamException {
		if (weapons.size() == config.NUM_WEAPONS) {
			throw new FullTeamException("Reached team max weapon limit!");
		}
		weapons.add(weapon);
	}

	/**
	 * Removes a weapon from the team
	 * @param toRemove the weapon to remove from the team
	 */
	public void removeWeapon(Weapon toRemove)
	{		
		for(Champion champion : getChampions())
		{
			if (champion.getWeapon() == toRemove)
			{
				champion.removeWeapon();
			}
		}
		if (weapons.contains(toRemove))
		{
			weapons.remove(toRemove);
		}
		else {
			System.out.println("EXCEPTION: Weapon to remove not in team!");
			//TODO: Throw an exception
			return;
		}
	}
	
	public boolean isWeeklyChampionPurchased() {
		return weeklyChampionPurchased;
	}

	public void setWeeklyChampionPurchased(boolean weeklyChampionPurchased) {
		this.weeklyChampionPurchased = weeklyChampionPurchased;
	}
		
	public void randomlySelectPurchasables() {
		randomlySelectChampions();
		randomlySelectWeapons();
		assignChosenWeapons();
	}
	
	private void randomlySelectChampions() {
		ArrayList<Champion> championsLeft = new ArrayList<Champion>(champions);
		while (chosenChampions.size() < config.NUM_CHOSEN_CHAMPIONS) {
			Random random = new Random();
			int index = random.nextInt(championsLeft.size());
			Champion randomChampion = championsLeft.remove(index);
			chosenChampions.add(randomChampion);
		}
	}
	
	private void randomlySelectWeapons() {
		int numWeaponsAvailable = Integer.min(weapons.size(), config.NUM_CHOSEN_CHAMPIONS);
		ArrayList<Weapon> weaponsLeft = new ArrayList<Weapon>(weapons);
		while (chosenWeapons.size() < numWeaponsAvailable) {
			Random random = new Random();
			int index = random.nextInt(weaponsLeft.size());
			Weapon randomWeapon = weaponsLeft.remove(index);
			chosenWeapons.add(randomWeapon);
		}
	}

	public void assignChosenWeapons() {
		for (int i = 0; i < chosenWeapons.size(); i++) {
			Champion champion = chosenChampions.get(i); 
			Weapon weapon = chosenWeapons.get(i);
			champion.setWeapon(weapon);
		}
	}
	
	public void unselectPurchasables() {
		unassignChosenWeapons();
		unselectChampions();
		unselectWeapons();
	}
	
	public void unselectChampions() {
		chosenChampions.removeAll(chosenChampions);
	}
	
	public void unselectWeapons() {
		chosenWeapons.removeAll(chosenWeapons);
	}
	
	public void unassignChosenWeapons() {
		for (Champion champion : chosenChampions) {
			champion.setWeapon(new Fists());
		}
	}
	
	public void rest() {
		setWeeklyChampionPurchased(false);
		for (Champion champion : champions) {
			champion.setStamina(champion.getMaxStamina());
		}
	}
	
}
