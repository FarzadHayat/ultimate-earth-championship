package events;

import model.Champion;
import model.Team;

public class RampagingAnimalEvent extends RandomEvent {

	/**
	 * Percentage chance of this event happening every week
	 */
	private static int occurrenceChance = 3; 
	
	public RampagingAnimalEvent() {
		super(occurrenceChance);
	}
	
	@Override
	public RandomEventInfo runEvent(Team team) {
		// Effect Logic:
		
		Champion champ = team.randomChampion();
		String c = champ.getName();
		
		champ.addStamina(-30);
		
		// Ensure stamina doesn't fall below 10
		if (champ.getStamina() < 10)
		{
			champ.setStamina(10);
		}
		
		// Generate GUI Info:
		
		String name = "A rampage through the dojo";
		String description = "A rampaging purple catbull from the planet Vargoth broke into the team's dojo and chased " + c + ". " +
		"Luckily nobody was hurt, but " + c + " was left severely exerted";
		String effectString = c + ": -30 Stamina";
		
		// Return it
		
		RandomEventInfo out = new RandomEventInfo(name, description, effectString);
		return out;
	}
 
	
}
