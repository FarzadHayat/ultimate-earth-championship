package events;

import java.util.Random;

import exception.FullTeamException;
import manager.GameManager;
import model.Champion;
import model.Shop;
import model.Team;

public class ChampionJoins extends RandomEvent {

	/**
	 * Percentage chance of this event happening every week
	 */
	private static int occurrenceChance = 0; 
	
	public ChampionJoins() {
		super(occurrenceChance);
	}

	@Override
	public RandomEventInfo runEvent(Team team) {
		// Effect Logic:

		Random random = new Random();
		random.nextInt(3);

		Shop shop = GameManager.getInstance().getShop();

		Champion joiningChampion = Shop.getRandomChampion(shop.getRemainingChampions());

		try {
			team.addChampion(joiningChampion);
		} catch (FullTeamException e) {
			// Unhandled currently
		}

		// Generate GUI Info:

		String name = joiningChampion.getName() + " joins!";
		String description = "Excited at the prospect of joining the team " + joiningChampion.getName()
				+ " has decided to join our team.";
		String effectString = joiningChampion.getName() + " joins the team";

		// Return it

		RandomEventInfo out = new RandomEventInfo(name, description, effectString);
		return out;
	}

}
