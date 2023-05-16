package events;

import model.Team;

public class TVShowEvent extends RandomEvent {

	/**
	 * Percentage chance of this event happening every week
	 */
	private static int occurrenceChance = 80;

	public TVShowEvent() {
		super(occurrenceChance);
	}

	@Override
	public RandomEventInfo runEvent(Team team) {
		// Effect Logic:

		team.addMoney(25);

		// Generate GUI Info:

		String name = "A TV appearance";
		String description = "A guest appearance upon a popular alien tv show has brought an influx of donations to the team.";
		String effectString = "+25 money";

		// Return it

		RandomEventInfo out = new RandomEventInfo(name, description, effectString);
		return out;
	}

}
