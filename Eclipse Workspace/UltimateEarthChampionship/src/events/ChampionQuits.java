package events;

import model.Champion;
import model.Team;

/**
 * Represents a random event in which a random champion quits the team
 */
public class ChampionQuits extends RandomEvent {

	/**
	 * Percentage chance of this event happening every week
	 */
	private static int occurrenceChance = 0; 
	
	private Champion championQuitting;
	
	/**
	 * Constructor
	 * @param championQuitting the Champion who quits
	 */
	public ChampionQuits(Champion championQuitting) {
		super(occurrenceChance);
		this.championQuitting = championQuitting;
	}

	/**
	 * Runs the event,
	 * @param team the team which this event affects
	 * @return A random event info containing what has occurred.
	 */
	@Override
	public RandomEventInfo runEvent(Team team) {
		// Effect Logic:
		
		team.addMoney(50);
		
		team.removeChampion(championQuitting);
		
		// Generate GUI Info:
		
		String name = championQuitting.getName() + " Quits!";
		String description = "Fed up with being sliced and smacked by frying pans, katanas and whatnot, " + championQuitting.getName() + " has decided to leave.";
		String effectString = championQuitting.getName() + " leaves, +50 money";
		
		// Return it

		RandomEventInfo out = new RandomEventInfo(name, description, effectString);
		return out;
	}

}
