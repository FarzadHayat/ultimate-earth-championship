package model;

import java.util.ArrayList;
import java.util.Random;

import exception.FullTeamException;
import exception.IncompleteTeamException;
import exception.InsufficientFundsException;
import manager.GameManager;

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
	 * ArrayList, of size 4, of the champions this team has chosen
	 */
	private ArrayList<Champion> chosenChampions = new ArrayList<Champion>();
	
	/**
	 * ArrayList, of size 5, of the champions the team has in reserve
	 */
	private ArrayList<Champion> reserveChampions = new ArrayList<Champion>();
	
	/**
	 * ArrayList of all weapons that this team has in reserve, use getAllWeapons() to get all weapons
	 */
	private ArrayList<Weapon> reserveWeapons = new ArrayList<Weapon>();
	
	/**
	 * The teams money
	 */
	private float money;
	
	/**
	 * The team's score
	 */
	private int score;
	
	private GameManager gameManager = GameManager.getInstance();
	
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
	
	public ArrayList<Champion> getChosenChampions()
	{
		return chosenChampions;
	}
	
	public ArrayList<Champion> getReserveChampions()
	{
		return reserveChampions;
	}
	
	
	
	
	/**
	 * Constructor of team
	 * @param isPlayer is this team a player
	 * @param startingChampions ArrayList of the four starting champions
	 */
	public Team(boolean isPlayer, ArrayList<Champion> startingChampions)
	{
		this.isPlayer = isPlayer;
		
		this.money = config.STARTING_MONEY;
		
		score = 0;
		
		if (startingChampions.size() != 4)
		{
			System.out.println("EXCEPTION: Starting champions size is not 4!");
			//TODO: Throw an exception
			return;
		}
		chosenChampions = startingChampions;
		
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
	
	/**
	 * Returns all champions in the team
	 * @return all the champions in the team, as an arrayList;
	 */
	public ArrayList<Champion> getAllChampions()
	{
		ArrayList<Champion> allChampions = new ArrayList<Champion>();
		
		// Get all chosen
		for (Champion champ : chosenChampions)
		{
			allChampions.add(champ);
		}
		
		// Get all in reserve
		for (Champion champ : reserveChampions)
		{
			allChampions.add(champ);
		}
		
		return allChampions;
	}
	
	/**
	 * Add a champion to the team. Will add to the roster first, and then to the reserve if the roster is full
	 * @param newChampion the new champion to be added
	 * @throws FullTeamException if team champions lists are already full
	 */
	public void addChampion(Champion newChampion) throws FullTeamException
	{
		if (chosenChampions.size() < 4)
		{
			// Add to roster first
			addToRoster(newChampion);
		}
		else
		{
			// Add to reserve if roster is first
			addToReserve(newChampion);
		}
		
	}
		
	/**
	 * Adds a champion to roster
	 * @param toRoster Champion to be added to chosenChampion
	 * @throws FullTeamException if the chosen champions list is already full
	 */
	private void addToRoster(Champion toRoster) throws FullTeamException
	{
		if (chosenChampions.size() == config.NUM_CHOSEN_CHAMPIONS)
		{
			throw new FullTeamException("EXCEPTION: Reached team max champion limit");
		}
		
		chosenChampions.add(toRoster);
	}
	
	/**
	 * Adds a champion to Reserve
	 * @param toReserve Champion to be added to reserve
	 * @throws FullTeamException if the reserve champions list is already full
	 */
	private void addToReserve(Champion toReserve) throws FullTeamException
	{
		if (reserveChampions.size() == config.NUM_RESERVE_CHAMPIONS)
		{
			throw new FullTeamException("EXCEPTION: Reached team max champion limit");
		}
		reserveChampions.add(toReserve);
	}

	
	/**
	 * Removes a champion from the team, will check both rostered and reserve champions
	 * @param toRemove
	 * @throws IncompleteTeamException if the team is already at the minimum number of champions allowed
	 */
	public void removeChampion(Champion toRemove) throws IncompleteTeamException
	{
		if (chosenChampions.size() + reserveChampions.size() <= config.NUM_CHOSEN_CHAMPIONS) {
			throw new IncompleteTeamException("Must have at least " + config.NUM_CHOSEN_CHAMPIONS + " champions in your team!");
		}
		if (chosenChampions.contains(toRemove))
		{
			chosenChampions.remove(toRemove);
		}
		else if (reserveChampions.contains(toRemove))
		{
			reserveChampions.remove(toRemove);
		}
		else {
			System.out.println("EXCEPTION: Champion to remove not in team!");
			//TODO: Throw an exception
			return;
		}
	}
	
	/**
	 * Swaps a champion in reserve with a champion currently rostered
	 * @param championToRoster The champion, current in reserve, to be rostered
	 * @param championToReserve The champion, currently rostered, to be placed in reserve
	 */
	public void swapChampion(Champion championToRoster, Champion championToReserve)
	{
		if (!reserveChampions.contains(championToRoster))
		{
			System.out.println("EXCEPTION: Champion not in team reserve!");
			//TODO: Throw an exception
			return;
		}
		
		if (!chosenChampions.contains(championToReserve))
		{
			System.out.println("EXCEPTION: Champion not in team roster!");
			//TODO: Throw an exception
			return;
		}
		
		// Swap them
		int indexChosen = chosenChampions.indexOf(championToReserve);
		int indexRostered = reserveChampions.indexOf(championToRoster);
		
		chosenChampions.add(indexChosen, championToRoster);
		
		reserveChampions.add(indexRostered, championToReserve);
		
	}
	
	/**
	 * Returns all reserve weapons
	 * @return all weapons in reserve
	 */
	public ArrayList<Weapon> getReserveWeapons()
	{
		return reserveWeapons;
	}
	
	/**
	 * Returns all weapons currently assigned to a champion
	 * @return All weapons assigned to a champion
	 */
	public ArrayList<Weapon> getChampionsWeapons()
	{
		ArrayList<Weapon> out = new ArrayList<Weapon>();
		
		for(Champion champ : getAllChampions())
		{
			Weapon w = champ.getWeapon();
			
			// Check that it isn't a default weapon
			if (w.isDefault() == false)
			{
				out.add(w);
			}
		}
		
		return out;
	}
	
	/**
	 * Adds a weapon to reserve weapons
	 * @throws FullTeamException if reserve weapons list is already full
	 */
	public void addReserveWeapon(Weapon weapon) throws FullTeamException {
		if (reserveWeapons.size() == config.NUM_RESERVE_WEAPONS) {
			throw new FullTeamException("Reached team max reserve weapon limit!");
		}
		reserveWeapons.add(weapon);
	}
	
	/**
	 * Gets all weapons
	 * @return All weapons in the team, both in reserve and in champions
	 */
	public ArrayList<Weapon> getAllWeapons()
	{
		ArrayList<Weapon> out = getChampionsWeapons();
		out.addAll(reserveWeapons);
		return out;
	}
	
	/**
	 * Gets a random champion from the team
	 * @return a random champion
	 */
	public Champion getRandomChampion()
	{
		Random random = new Random();
		int champInt = random.nextInt(getAllChampions().size());
		return getAllChampions().get(champInt);
	}
		
	/**
	 * Removes a weapon from the team
	 * @param toRemove the weapon to remove from the team
	 */
	public void removeWeapon(Weapon toRemove)
	{		
		for(Champion champion : getAllChampions())
		{
			if (champion.getWeapon() == toRemove)
			{
				champion.removeWeapon();
			}
		}
		if (reserveWeapons.contains(toRemove))
		{
			reserveWeapons.remove(toRemove);
		}
		else {
			System.out.println("EXCEPTION: Weapon to remove not in team!");
			//TODO: Throw an exception
			return;
		}
	}
	

	/**
	 * Buys the purchasable for the team.
	 * 1. Removes the purchasable price from the team's money.
	 * 2. Adds the weapon to the team.
	 * 3. Removes the purchasable from the shop.
	 * @throws InsufficientFundsException if team cannot afford this purchasable
	 * @throws FullTeamException if team is already full
	 */
	public void buy(Purchasable purchasable) throws InsufficientFundsException, FullTeamException {
		removeMoney(purchasable.getPrice());
		try {
			if (purchasable.getClass().getSuperclass() == Champion.class) {
				addChampion((Champion) purchasable);
				gameManager.getShop().removeChampion((Champion) purchasable);
			}
			if (purchasable.getClass().getSuperclass() == Weapon.class) {
				addReserveWeapon((Weapon) purchasable);
				gameManager.getShop().removeWeapon((Weapon) purchasable);
			}
		}
		catch (FullTeamException e) {
			addMoney(purchasable.getPrice());
			throw new FullTeamException(e.getMessage());
		}
	}
	
	/**
	 * Sells the purchasable and refunds the price.
	 * 1. Removes the purchasable from the team
	 * 2. Adds the purchasable price to the team's money.
	 * @throws IncompleteTeamException if the team is already at the minimum number of champions allowed
	 */
	public void sell(Purchasable purchasable) throws IncompleteTeamException {
		if (purchasable.getClass().getSuperclass() == Champion.class) {
			removeChampion((Champion) purchasable);
		}
		if (purchasable.getClass().getSuperclass() == Weapon.class) {
			removeWeapon((Weapon) purchasable);
		}
		addMoney(purchasable.getPrice());
	}
	
}
