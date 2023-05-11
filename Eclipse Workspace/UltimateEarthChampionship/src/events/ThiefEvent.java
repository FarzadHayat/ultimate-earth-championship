package events;

import model.Team;

public class ThiefEvent extends RandomEvent {

	/**
	 * Percentage chance of this event happening every week
	 */
	private static int occurrenceChance = 80; 
	
	public ThiefEvent() {
		super(occurrenceChance);
	}
	
	@Override
	public RandomEventInfo runEvent(Team team) {
		// Effect Logic:
		
		team.addMoney(-10);
		
		// Generate GUI Info:
		
		String name = "A thief!";
		String description = "Mysterious individual cloaked in robes snuck into the team's quaters last night and made off with a significant sum";
		String effectString = "-10 money";
		
		// Return it
		
		RandomEventInfo out = new RandomEventInfo(name, description, effectString);
		return out;
	}
 
	
}
