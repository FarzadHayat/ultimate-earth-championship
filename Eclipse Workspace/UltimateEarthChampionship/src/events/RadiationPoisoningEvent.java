package events;

import model.Champion;
import model.Team;

public class RadiationPoisoningEvent extends RandomEvent {

	/**
	 * Percentage chance of this event happening every week
	 */
	private static int occurrenceChance = 2; 
	
	public RadiationPoisoningEvent() {
		super(occurrenceChance);
	}
	
	@Override
	public RandomEventInfo runEvent(Team team) {
		// Effect Logic:
		
		Champion champ = team.getRandomChampion();
		String c = champ.getName();
		
		champ.changeOffense(3);
		champ.changeDefense(2);
		champ.addHealth(-40);
		
		// Ensure health doesn't fall below 10
		if (champ.getHealth() < 10)
		{
			champ.setHealth(10);
		}
		
		// Generate GUI Info:
		
		String name = "Street Food";
		String description = "While perusing the alien legation, " + c + " decided to try some glowing roasted nurgoth. While " + c + "has " +
		"spent the last hour vomiting up radioactive blood, they appear to have become significantly stronger.";
		String effectString = c + ": -40 health, +3 Offense, + 2 Defense";
		
		// Return it
		
		RandomEventInfo out = new RandomEventInfo(name, description, effectString);
		return out;
	}
 
	
}
