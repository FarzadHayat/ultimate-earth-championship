package model;

import java.util.ArrayList;
import java.util.Random;

import events.ChampionJoins;
import events.ChampionQuits;
import events.DonationEvent;
import events.FreeWeaponEvent;
import events.RadiationPoisoningEvent;
import events.RampagingAnimalEvent;
import events.RandomEvent;
import events.RandomEventInfo;
import events.TVShowEvent;
import events.ThiefEvent;
import exception.GameFinishedException;
import manager.GameManager;

public class GameEnvironment {
	
	private Configuration config = Configuration.getInstance();
	
	/**
	 * The current week, starting from 1
	 */
	private int currentWeek;
	
	/**
	 * The maximum number of weeks, current week is greater than maxWeek, the game is over
	 */
	private int maxWeek;
	
	/**
	 * The difficulty of the game, should fall between the range of 0.1 and 2
	 */
	private float difficulty;
	
	/**
	 * List of all random events in the game
	 */
	private ArrayList<RandomEvent> events;
	
	public int getCurrentWeek()
	{
		return currentWeek;
	}
	
	public int getMaxWeeks()
	{
		return maxWeek;
	}
	
	public void setMaxWeeks(int weekNum)
	{
		maxWeek = weekNum;
	}
	
	/**
	 * Finds the number of weeks remaining, will return a 0 on the last week
	 * @return The number of weeks remaining
	 */
	public int getWeeksRemaining()
	{
		return maxWeek - currentWeek;
	}
	

	/**
	 * Constructor class
	 */
	public GameEnvironment() {
		currentWeek = 0;
		maxWeek = 99;
		events = new ArrayList<RandomEvent>();
		getAllEvents();
	}
	
	/**
	 * Gets all the events in the game
	 */
	private void getAllEvents()
	{
		events = new ArrayList<RandomEvent>();
		// For now: Just manually add them :(
		events.add(new DonationEvent());
		events.add(new TVShowEvent());
		events.add(new RadiationPoisoningEvent());
		events.add(new RampagingAnimalEvent());
		events.add(new ThiefEvent());
		events.add(new FreeWeaponEvent());
		
		// ChampionJoins is special, it's chance of occuring changes each week depending upon num of team slots
		//events.add(new ChampionJoins());
		
		// ChampionQuits event is special, as its chance of occuring changes each week depending upon how much damage each champion has taken
		//events.add(new ChampionQuits());
	}
	
	/**
	 * Sets the difficulty by float. Difficulty value must be 0.1 < difficulty < 2. Will directly modify the config singleton
	 *  to apply the difficulty.
	 * @param difficulty The difficulty of the game.
	 */
	public void setDifficulty(float difficulty) // Private because this should only be accessed in the constructor
	{
		this.difficulty = difficulty;
		
		// Check within range:
		if (difficulty < 0.5f || difficulty > 2f)
		{
			System.out.println("WARNING: Difficulty setting outside of expected range, aborting.");
			return;
		}
		
		//TODO: Fuck with the config using this difficulty setting!
	}
	
	/**
	 * Returns the value of difficulty
	 * @return difficulty the value of difficulty
	 */
	public float getDifficulty() {
		return difficulty;
	}

	/**
	 * Goes through each event and checks for its chance of occurring,
	 * If so the event if run and if the event occurs on the player team,
	 * it is added to the returned list which can then be passed onto the GUI.
	 * @return A list of weekly events
	 */
	public ArrayList<RandomEventInfo> generateWeeklyEvents()
	{
		ArrayList<RandomEventInfo> weeklyEvents = new ArrayList<RandomEventInfo>();
		
		for(RandomEvent event : events)
		{
			if (chanceCheck(event.getChanceOfOccuring()))
			{
				// Event occurs:
				Team randomTeam = getRandomTeam();
				RandomEventInfo newEvent = event.runEvent(randomTeam);
				// We run the event to cause the logic to occur
				
				if (randomTeam.isPlayerTeam())
				{
					// If this event happens to the player team...
					// Note it down
					weeklyEvents.add(newEvent);
				}
			}
			else 
			{
				// Event doesn't occur
			}
		}
		
		// Champion joins event:
		for (Team team : GameManager.getInstance().getTeams())
		{
			// For each team
			int numEmptySlots = (9 - team.getChampions().size());
			
			float chanceOccuring = numEmptySlots * config.CHANCE_OF_CHAMPION_JOIN_PER_EMPTY_SLOT;
			
			if (chanceCheck(chanceOccuring))
			{
				// Run event
				ChampionJoins championJoinsEvent = new ChampionJoins();
				RandomEventInfo newEvent = championJoinsEvent.runEvent(team);
				
				// If team it occurred to is on player team, notify them
				if (team.isPlayerTeam())
				{
					weeklyEvents.add(newEvent);
				}
			}
		}
		
		// Champion leaves event:
		for (Team team : GameManager.getInstance().getTeams())
		{
			// If the team has more than 5 champions
			if (team.getChampions().size() > 5)
			{
				// For each champion in each team, find their chance of leaving
				for (Champion champ : team.getChosenChampions())
				{
					// Just iterate over chosen champions, as they are the only ones who would have
					// taken damage.
					
					float chanceOccuring = champ.getDamageTakenThisWeek() * config.CHANCE_OF_CHAMPION_LEAVE_DAMAGE_FACTOR;
					if (chanceCheck(chanceOccuring))
					{
						// Run event
						ChampionQuits championJoinsEvent = new ChampionQuits(champ);
						RandomEventInfo newEvent = championJoinsEvent.runEvent(team);
						
						// If it is a player team, add to weekly events
						if (team.isPlayerTeam())
						{
							weeklyEvents.add(newEvent);
						}
						
						// A max of 1 champion leave event should happen each week to be fair to teams
						break;
					}
				}
			}
			
			
			
			
		}
		
		return weeklyEvents;
	}
	
	/**
	 * Gets a random team 
	 * @return a random team
	 */
	public Team getRandomTeam()
	{
		Random random = new Random();
		int teamInt = random.nextInt(GameManager.getInstance().getTeams().size());
		return GameManager.getInstance().getTeams().get(teamInt);
	}
	
	/**
	 * Returns true or false depending on the provided percentage chance
	 * @param chance the percent chance of true being returned
	 * @return True or False
	 */
	private boolean chanceCheck(float chance)
	{
		float random = (float) (Math.random() * 100);
		
		return (chance >= random);
	}
	
	public void nextWeek() throws GameFinishedException {
		if (currentWeek == maxWeek) {
			throw new GameFinishedException();
		}
		else {
			currentWeek += 1;
		}
	}

}
