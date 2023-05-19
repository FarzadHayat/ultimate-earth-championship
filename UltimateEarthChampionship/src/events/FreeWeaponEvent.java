package events;

import java.util.ArrayList;
import java.util.Random;

import exception.FullTeamException;
import model.Team;
import model.Weapon;
import weapons.Chainsaw;
import weapons.Katana;
import weapons.Spear;
import weapons.Sword;

/**
 * Represents a random event in which the team receives a free weapon
 */
public class FreeWeaponEvent extends RandomEvent {

	/**
	 * Percentage chance of this event happening every week
	 */
	private static int occurrenceChance = 50;

	/**
	 * Constructor
	 */
	public FreeWeaponEvent() {
		super(occurrenceChance);
	}

	/**
	 * Runs the event,
	 * @return A random event info containing what has occurred.
	 */
	@Override
	public RandomEventInfo runEvent(Team team) {
		// Effect Logic:

		boolean teamFull = false;

		ArrayList<Weapon> weapons = new ArrayList<>();
		weapons.add(new Katana());
		weapons.add(new Chainsaw());
		weapons.add(new Spear());
		weapons.add(new Sword());

		Random random = new Random();
		int randInt = random.nextInt(4);

		Weapon giftedWeapon = weapons.get(randInt);

		try {
			team.addWeapon(giftedWeapon);
		} catch (FullTeamException e) {
			// Team is too full!
			team.addMoney(15);
			teamFull = true;
		}

		// Generate GUI Info:

		String name = "A free weapons";
		String description = "";
		String effectString = "";

		if (teamFull) {
			description = "One of the visiting senators from gregori-III was so enthralled by our team's performance they have presented us with a large sum of money";
			effectString = "+15 money";
		} else {
			description = "One of the visiting senators from gregori-III was so enthralled "
					+ "by our team's performance they have presented us with a " + giftedWeapon.getName()
					+ " free of charge";
			effectString = "+ " + giftedWeapon.getName();

		}

		// Return it
		RandomEventInfo out = new RandomEventInfo(name, description, effectString);
		return out;
	}

}
