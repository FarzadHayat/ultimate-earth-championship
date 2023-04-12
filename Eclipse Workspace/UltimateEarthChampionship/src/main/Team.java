package main;

import java.util.ArrayList;

import champion.Champion;
import weapons.Weapon;

public class Team {

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
	 * ArrayList of all weapons that this team owns
	 */
	private ArrayList<Weapon> weapons;
	
	/**
	 * The teams money
	 */
	private float money;
	
	/**
	 * The team's score
	 */
	private int score;
	
	
	
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
		ArrayList<Champion> chosen = new ArrayList<Champion>();
		
		// Get all chosen
		for (Champion champ : chosenChampions)
		{
			chosen.add(champ);
		}
		
		return chosen;
	}
	
	public ArrayList<Champion> getReserveChampions()
	{
		ArrayList<Champion> reserve = new ArrayList<Champion>();
		
		// Get all in reserve
		for (Champion champ : reserveChampions)
		{
			reserve.add(champ);
		}
		
		return reserve;
	}
	
	
	
	
	/**
	 * Constructor of team
	 * @param isPlayer is this team a player
	 * @param startingChampions ArrayList of the four starting champions
	 */
	public Team(boolean isPlayer, ArrayList<Champion> startingChampions)
	{
		this.isPlayer = isPlayer;
		
		// TODO: Setup config default money start value:
		this.money = 100f;
		
		if (startingChampions.size() != 4)
		{
			System.out.println("WARNING: Starting champions size is not 4!");
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
	 * Add a champion to the teams reserve
	 * @param newChampion the new champion to be added
	 */
	public void addChampion(Champion newChampion)
	{
		// TODO: Setup Config for max size
		if (reserveChampions.size() == 5)
		{
			System.out.println("WARNING: Reached team max champion limit");
		}
		else
		{
			reserveChampions.add(newChampion);
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
			System.out.println("WARNING: Champion not in team reserve!");
			return;
		}
		
		if (!chosenChampions.contains(championToReserve))
		{
			System.out.println("WARNING: Champion not in team roster!");
			return;
		}
		
		// Swap them
		int indexChosen = chosenChampions.indexOf(championToReserve);
		int indexRostered = chosenChampions.indexOf(championToRoster);
		
		chosenChampions.add(indexChosen, championToRoster);
		
		
		reserveChampions.add(indexRostered, championToReserve);
		
	}
	
}
