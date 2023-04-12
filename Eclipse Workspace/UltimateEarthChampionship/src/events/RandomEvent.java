package events;

import champion.Champion;
import main.GameEnvironment;


public abstract class RandomEvent {

	/**
	 * The percent chance of this event occuring, in integer terms (e.g 4 = 4% chance)
	 */
	private int chanceOfOccuring;
	
	public int getChanceOfOccuring()
	{
		return chanceOfOccuring;
	}
	
	public RandomEvent(int chance) {
		chanceOfOccuring = chance;
	}
	
	public abstract RandomEventGUIInfo runEvent(String team);
	
}
