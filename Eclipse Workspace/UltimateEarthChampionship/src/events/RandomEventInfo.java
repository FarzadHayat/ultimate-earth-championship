package events;

/**
 * Random Event GUI Info This class is intended to act as a 'parcel' of
 * information to be instantiated at each random event and passed through to the
 * GUI It is done like this so that random events can be easily stored in a list
 * and presented to the user once at a time by the GUI.
 *
 * @author Oliver Coates
 *
 */
public class RandomEventInfo {

	/**
	 * The name of this event
	 */
	public String name;

	/**
	 * The description of the event
	 */
	public String description;

	/**
	 * The effects of the event, presented as a manually written string
	 */
	public String effectString;

	/**
	 * Constructor method
	 *
	 * @param name         Name of the event
	 * @param description  Description of the event
	 * @param effectString Description of this event's effects
	 */
	public RandomEventInfo(String name, String description, String effectString) {
		this.name = name;
		this.description = description;
		this.effectString = effectString;
	}
}
