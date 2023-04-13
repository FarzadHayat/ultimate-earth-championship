package main;

import java.util.ArrayList;
import java.util.Iterator;

import events.*;

public class GameEnvironment {
	
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
	 * @param gameDifficulty How difficult the game should be, on a scale from 0.1 to 2f
	 */
	public GameEnvironment(float gameDifficulty) {
		currentWeek = 0;
		maxWeek = 10;
		
		setDifficulty(gameDifficulty);
		
		getAllEvents();
	}
	
	/**
	 * Gets all the events in the game
	 */
	private void getAllEvents()
	{
		// For now: Just manually add them :(
		events.add(new Donation());
	}
	
	/**
	 * Sets the difficulty by float. Difficulty value must be 0.1 < difficulty < 2. Will directly modify the config singleton
	 *  to apply the difficulty.
	 * @param difficulty The difficulty of the game.
	 */
	private void setDifficulty(float difficulty) // Private because this should only be accessed in the constructor
	{
		this.difficulty = difficulty;
		
		// Check within range:
		if (difficulty < 0.1f || difficulty > 2f)
		{
			System.out.println("WARNING: Difficulty setting outside of expected range, aborting.");
			return;
		}
		
		//TODO: Fuck with the config using this difficulty setting!
	}

	/**
	 * Goes through each event and checks for its chance of occurring,
	 * If so the event if run and if the event occurs on the player team,
	 * it is added to the returned list.
	 * @return
	 */
	private ArrayList<RandomEventInfo> generateWeeklyEvents()
	{
		ArrayList<RandomEventInfo> weeklyEvents = new ArrayList<RandomEventInfo>();
		
		for(RandomEvent event : events)
		{
			if (chanceCheck(event.getChanceOfOccuring()))
			{
				// Event occurs:
				String randomTeam = "Pick a random team";
				RandomEventInfo newEvent = event.runEvent(randomTeam);
				
//				if (randomTeam.isPlayer)
//				{
//					// If this event happens to the player team...
//					// Note it down
//					weeklyEvents.add(newEvent);
//				}
			}
			else 
			{
				// Event doesn't occur
			}
		}
		
		return weeklyEvents;
	}
	
	/**
	 * Returns true or false depending on the provided percentage chance
	 * @param chance the percent chance of true being returned
	 * @return True or False
	 */
	private boolean chanceCheck(int chance)
	{
		int random = (int) (Math.random() * 100);
		
		return (chance >= random);
	}
	

}
