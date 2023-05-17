package events;

import model.Team;

/**
 * Random event superclass, This class represents a randomly occurring event which
 * could happen during a week's end.
 */
public abstract class RandomEvent {

	/**
	 * The percent chance of this event occurring, in integer terms (e.g 4 = 4%
	 * chance)
	 */
	private int chanceOfOccuring;

	/**
	 * Returns the chance of this event occurring (as a percentage)
	 * @return The chance of occurring (as a percentage)
	 */
	public int getChanceOfOccuring() {
		return chanceOfOccuring;
	}

	/**
	 * Constructor
	 * @param chance The chance of this event happening naturally each week
	 */
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
