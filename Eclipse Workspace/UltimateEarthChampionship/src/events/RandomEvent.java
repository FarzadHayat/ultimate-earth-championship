package events;

import model.Team;

public abstract class RandomEvent {

	/**
	 * The percent chance of this event occurring, in integer terms (e.g 4 = 4%
	 * chance)
	 */
	private int chanceOfOccuring;

	public int getChanceOfOccuring() {
		return chanceOfOccuring;
	}

	public RandomEvent(int chance) {
		chanceOfOccuring = chance;
	}

	/**
	 * Runs the random event
	 *
	 * @param team the team that the event is applied to
	 * @return The info related to the event to be shown on the UI
	 */
	public abstract RandomEventInfo runEvent(Team team);

}
