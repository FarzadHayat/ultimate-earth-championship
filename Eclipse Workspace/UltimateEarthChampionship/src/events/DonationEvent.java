package events;

import model.Team;

public class DonationEvent extends RandomEvent {

	/**
	 * Percentage chance of this event happening every week
	 */
	private static int occurrenceChance = 80; 
	
	public DonationEvent() {
		super(occurrenceChance);
	}
	
	@Override
	public RandomEventInfo runEvent(Team team) {
		// Effect Logic:
		
		team.addMoney(15);
		
		// Generate GUI Info:
		
		String name = "A donation";
		String description = "Mysterious wealthy donor from the planet Xyyrg III has sent our team a large donation.";
		String effectString = "+15 money";
		
		// Return it
		
		RandomEventInfo out = new RandomEventInfo(name, description, effectString);
		return out;
	}
 
	
}
