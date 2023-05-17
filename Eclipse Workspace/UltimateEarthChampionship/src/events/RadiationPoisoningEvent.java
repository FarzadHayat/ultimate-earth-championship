package events;

import model.Champion;
import model.Team;

public class RadiationPoisoningEvent extends RandomEvent {

	/**
	 * Percentage chance of this event happening every week
	 */
	private static int occurrenceChance = 40;

	public RadiationPoisoningEvent() {
		super(occurrenceChance);
	}

	@Override
	public RandomEventInfo runEvent(Team team) {
		// Effect Logic:

		Champion champ = team.randomChampion();
		String c = champ.getName();

		champ.changeOffense(3);
		champ.changeDefense(2);
		champ.addMaxStamina(-25);
		
		// Ensure stamina doesn't fall below 10
		if (champ.getMaxStamina() < 10) {
			champ.addMaxStamina(10 - champ.getMaxStamina());
		}

		champ.setStamina(champ.getMaxStamina());
		
		// Generate GUI Info:

		String name = "Street Food";
		String description = "While perusing the alien legation, " + c
				+ " decided to try some glowing roasted nurgoth. While " + c + " has "
				+ "spent the last hour vomiting up radioactive blood, they appear to have become significantly stronger.";
		String effectString = c + ": -25 Stamina, +3 Offense, + 2 Defense";

		// Return it

		RandomEventInfo out = new RandomEventInfo(name, description, effectString);
		return out;
	}

}
