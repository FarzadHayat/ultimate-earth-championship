package events;

import model.Champion;
import model.Team;

public class RampagingAnimalEvent extends RandomEvent {

	/**
	 * Percentage chance of this event happening every week
	 */
	private static int occurrenceChance = 60; 
	
	public RampagingAnimalEvent() {
		super(occurrenceChance);
	}
	
	@Override
	public RandomEventInfo runEvent(Team team) {
		// Effect Logic:
		
		Champion champ = team.randomChampion();
		String c = champ.getName();
		
		champ.addMaxStamina(-15);
		champ.setStamina(champ.getMaxStamina());
		
		
		// Generate GUI Info:
		
		String name = "A rampage through the dojo";
		String description = "A rampaging purple catbull from the planet Vargoth broke into the team's dojo and chased " + c + ". " +
		"Luckily nobody was hurt, but " + c + " was left severely exerted";
		String effectString = c + ": -15 max Stamina";
		
		// Return it
		
		RandomEventInfo out = new RandomEventInfo(name, description, effectString);
		return out;
	}
 
	
}
