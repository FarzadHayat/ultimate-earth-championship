package model;

import java.util.ArrayList;
import java.util.Random;

import events.ChampionJoins;
import events.ChampionQuits;
import events.DonationEvent;
import events.FreeWeaponEvent;
import events.RadiationPoisoningEvent;
import events.RampagingAnimalEvent;
import events.RandomEvent;
import events.RandomEventInfo;
import events.TVShowEvent;
import events.ThiefEvent;
import exception.GameFinishedException;
import manager.GameManager;

/**
 * Class which represents the general environment of the game, This class keeps
 * track of the current week, maximum week, and random events that may occur
 */
public class GameEnvironment {

	// Game manager
	private GameManager gameManager = GameManager.getInstance();

	// Config
	private Configuration config = Configuration.getInstance();

	/**
	 * The current week, starting from 1
	 */
	private int currentWeek;

	/**
	 * The maximum number of weeks, current week is greater than maxWeek, the game
	 * is over
	 */
	private int maxWeek;

	/**
	 * List of all random events in the game
	 */
	private ArrayList<RandomEvent> events;

	/**
	 * Gets the current week
	 *
	 * @return The current week
	 */
	public int getCurrentWeek() {
		return currentWeek;
	}

	/**
	 * Sets the current number of weeks
	 *
	 * @param weekNum The new current number of weeks
	 */
	public void setCurrentWeek(int weekNum) {
		currentWeek = weekNum;
	}

	/**
	 * Gets the maximum number of weeks in the game
	 *
	 * @return The max number of weeks
	 */
	public int getMaxWeeks() {
		return maxWeek;
	}

	/**
	 * Sets the max number of weeks
	 *
	 * @param weekNum The new max number of weeks
	 */
	public void setMaxWeeks(int weekNum) {
		maxWeek = weekNum;
	}

	/**
	 * Finds the number of weeks remaining, will return a 0 on the last week
	 *
	 * @return The number of weeks remaining
	 */
	public int getWeeksRemaining() {
		return maxWeek - currentWeek;
	}

	/**
	 * Constructor class
	 */
	public GameEnvironment() {
		currentWeek = 1;
		maxWeek = 99;
		events = new ArrayList<>();
		getAllEvents();
	}

	/**
	 * Gets all the events in the game
	 */
	private void getAllEvents() {
		events = new ArrayList<>();
		// For now: Just manually add them :(
		events.add(new DonationEvent());
		events.add(new TVShowEvent());
		events.add(new RadiationPoisoningEvent());
		events.add(new RampagingAnimalEvent());
		events.add(new ThiefEvent());
		events.add(new FreeWeaponEvent());

		// ChampionJoins is special, it's chance of occuring changes each week depending
		// upon num of team slots
		// events.add(new ChampionJoins());

		// ChampionQuits event is special, as its chance of occuring changes each week
		// depending upon how much damage each champion has taken
		// events.add(new ChampionQuits());
	}

	/**
	 * Goes through each event and checks for its chance of occurring, If so the
	 * event if run and if the event occurs on the player team, it is added to the
	 * returned list which can then be passed onto the GUI.
	 *
	 * @return A list of weekly events
	 */
	public ArrayList<RandomEventInfo> generateWeeklyEvents() {
		ArrayList<RandomEventInfo> weeklyEvents = new ArrayList<>();

		for (RandomEvent event : events) {
			if (chanceCheck(event.getChanceOfOccuring())) {
				// Event occurs:
				Team randomTeam = getRandomTeam();
				RandomEventInfo newEvent = event.runEvent(randomTeam);
				// We run the event to cause the logic to occur

				if (randomTeam.isPlayer()) {
					// If this event happens to the player team...
					// Note it down
					weeklyEvents.add(newEvent);
				}
			} else {
				// Event doesn't occur
			}
		}

		// Champion joins event:
		for (Team team : GameManager.getInstance().getTeams()) {
			// For each team
			int numEmptySlots = (9 - team.getChampions().size());

			float chanceOccuring = numEmptySlots * config.CHANCE_OF_CHAMPION_JOIN_PER_EMPTY_SLOT;

			if (chanceCheck(chanceOccuring)) {
				// Run event
				ChampionJoins championJoinsEvent = new ChampionJoins();
				RandomEventInfo newEvent = championJoinsEvent.runEvent(team);

				// If team it occurred to is on player team, notify them
				if (team.isPlayer()) {
					weeklyEvents.add(newEvent);
				}
			}
		}

		// Champion leaves event:
		for (Team team : GameManager.getInstance().getTeams()) {
			// If the team has more than 5 champions
			if (team.getChampions().size() > 4) {
				// For each champion in each team, find their chance of leaving
				for (Champion champ : team.getChampions()) {

					float chanceOccuring = champ.getDamageTakenThisWeek()
							* config.CHANCE_OF_CHAMPION_LEAVE_DAMAGE_FACTOR;

					if (chanceCheck(chanceOccuring)) {
						// Run event
						ChampionQuits championJoinsEvent = new ChampionQuits(champ);
						RandomEventInfo newEvent = championJoinsEvent.runEvent(team);

						// If it is a player team, add to weekly events
						if (team.isPlayer()) {
							weeklyEvents.add(newEvent);
						}

						// A max of 1 champion leave event should happen each week to be fair to teams
						break;
					}
				}
			}

		}

		return weeklyEvents;
	}

	/**
	 * Gets a random team
	 *
	 * @return a random team
	 */
	public Team getRandomTeam() {
		Random random = new Random();
		int teamInt = random.nextInt(GameManager.getInstance().getTeams().size());
		return GameManager.getInstance().getTeams().get(teamInt);
	}

	/**
	 * Returns true or false depending on the provided percentage chance
	 *
	 * @param chance the percent chance of true being returned
	 * @return True or False
	 */
	private boolean chanceCheck(float chance) {
		float random = (float) (Math.random() * 100);

		return (chance >= random);
	}

	/**
	 * Advances to the next week, Will throw an exception if the game is over
	 *
	 * @throws GameFinishedException Thrown if the game is over
	 */
	public void nextWeek() throws GameFinishedException {
		if (currentWeek == maxWeek) {
			throw new GameFinishedException();
		} else {
			currentWeek += 1;
			trainChampionsNotInUse();
			applyWeeklyPriceChange();
		}
	}

	/**
	 * Finds all champions not currently in a team and gives them an XP boost
	 * defined in the config class
	 */
	public void trainChampionsNotInUse() {
		ArrayList<Champion> champsNotInUse = new ArrayList<Champion>(gameManager.getAllChampions());

		// For each team
		for (Team team : gameManager.getTeams()) {
			// For each champion in the team
			for (Champion champ : team.getChampions()) {
				// Remove it from the champsNotInUse arraylist
				if (champsNotInUse.contains(champ)) {
					champsNotInUse.remove(champ);
				}
			}
		}

		// This leaves us with a list of champions not in anybody's team

		for (Champion champ : champsNotInUse) {
			champ.giveXP(config.UNUSED_CHAMPIONS_WEEKLY_XP_GAIN);
		}

	}

	/**
	 * Applies weekly price change to all champions and weapons across the teams
	 */
	public void applyWeeklyPriceChange() {
		for (Team team : gameManager.getTeams()) {
			// For each champion
			for (Champion champ : team.getChampions()) {
				champ.applyWeeklyPriceChange();
			}

			// For each weapon
			for (Weapon weapon : team.getWeapons()) {
				weapon.applyWeeklyPriceChange();
			}
		}
	}
}
