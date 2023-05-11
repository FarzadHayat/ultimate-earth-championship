package events;

import java.util.Random;

import exception.IncompleteTeamException;
import model.Champion;
import model.Team;

public class ChampionQuits extends RandomEvent {

	/**
	 * Percentage chance of this event happening every week
	 */
	private static int occurrenceChance = 15; 
	
	public ChampionQuits() {
		super(occurrenceChance);
	}
	
	@Override
	public RandomEventInfo runEvent(Team team) {
		// Effect Logic:
		
		Random random = new Random();
		int i = random.nextInt(4);
		
		Champion quittingChampion = team.getChampions().get(i);
		
		team.addMoney(50);
		
		team.removeChampion(quittingChampion);
		
		// Generate GUI Info:
		
		String name = quittingChampion.getName() + " Quits!";
		String description = "Fed up with being sliced and smacked by frying pans, katanas and whatnot, " + quittingChampion.getName() + " has decided to leave.";
		String effectString = quittingChampion.getName() + " leaves, +50 money";
		
		// Return it
		
		RandomEventInfo out = new RandomEventInfo(name, description, effectString);
		return out;
	}
 
	
}
