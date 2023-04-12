package events;

import champion.Champion;

public class Donation extends RandomEvent {

	
	public Donation() {
		super(1); // <-- Percentage chance of occurrence
	}
	
	@Override
	public RandomEventGUIInfo runEvent(String team) {
		// Effect Logic:
		
		//team.addMoney(10);
		
		// Generate GUI Info:
		
		String name = "A TV appearance";
		String description = "A guest appearance upon a popular alien tv show has brought an influx of donations to the team.";
		String effectString = "+10 money";
		
		// Return it
		
		RandomEventGUIInfo out = new RandomEventGUIInfo(name, description, effectString);
		return out;
	}

	
}