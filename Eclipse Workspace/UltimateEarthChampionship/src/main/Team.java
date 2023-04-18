package main;

import java.util.ArrayList;

import champion.Champion;
import exception.TeamFullException;
import weapon.Weapon;

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
	private ArrayList<Champion> chosenChampions;
	
	/**
	 * ArrayList, of size 5, of the champions the team has in reserve
	 */
	private ArrayList<Champion> reserveChampions;
	
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
	 */
	public void addMoney(float amount)
	{
		money += amount;
		
		if (money < 0)
		{
			System.out.println("WARNING: Money should never fall below 0!");
		}
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
		
		reserveChampions = new ArrayList<Champion>();
		
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
	 */
	public void addChampion(Champion newChampion)
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
	
	//TODO: 2 functions needed: Add to Reserve and Add to roster (Should be called by addChampion)
	
	/**
	 * Adds a champion to roster
	 * @param toRoster Champion to be added to chosenChampion
	 */
	private void addToRoster(Champion toRoster)
	{
		if (chosenChampions.size() == config.NUM_CHOSEN_CHAMPIONS)
		{
			System.out.println("EXCEPTION: Reached team max champion limit");
			//TODO: Throw an exception
			return;
		}
		
		chosenChampions.add(toRoster);
	}
	
	/**
	 * Adds a champion to Reserve
	 * @param toReserve Champion to be added to reserve
	 */
	private void addToReserve(Champion toReserve)
	{
		// TODO: Setup Config for max size
		if (reserveChampions.size() == config.NUM_RESERVE_CHAMPIONS)
		{
			System.out.println("EXCEPTION: Reached team max champion limit");
			//TODO: Throw an exception
			return;
		}
	
		reserveChampions.add(toReserve);
	}

	
	/**
	 * Removes a champion from the team, will check both rostered and reserve champions
	 * @param toRemove
	 */
	public void removeChampion(Champion toRemove)
	{
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
	 * @throws TeamFullException if team reserve weapons is already full
	 */
	public void addReserveWeapon(Weapon weapon) throws TeamFullException {
		if (reserveWeapons.size() == config.NUM_RESERVE_WEAPONS) {
			throw new TeamFullException("Reached team max reserve weapon limit!");
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
	
}
