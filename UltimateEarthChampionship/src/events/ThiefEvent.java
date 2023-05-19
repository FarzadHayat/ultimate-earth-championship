package events;

import model.Team;

/**
 * Represents a random event in which the team is stolen from
 */
public class ThiefEvent extends RandomEvent {

	/**
	 * Percentage chance of this event happening every week
	 */
	private static int occurrenceChance = 70;

	/**
	 * Constructor
	 */
	public ThiefEvent() {
		super(occurrenceChance);
	}
	
	/**
	 * Runs the event,
	 * @return A random event info containing what has occurred.
	 */
	@Override
	public RandomEventInfo runEvent(Team team) {
		// Effect Logic:

		team.addMoney(-25);

		// Generate GUI Info:

		String name = "A thief!";
		String description = "Mysterious individual cloaked in robes snuck into the team's quaters last night and made off with a significant sum";
		String effectString = "-25 money";

		// Return it

		RandomEventInfo out = new RandomEventInfo(name, description, effectString);
		return out;
	}

}
