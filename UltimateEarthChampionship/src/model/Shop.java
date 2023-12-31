package model;

import java.util.ArrayList;
import java.util.Random;

import manager.GameManager;

/**
 * The Shop class represents a store where players can purchase items for use in
 * the game. The shop has a weekly catalog of available items that can be
 * refreshed using the generateCatalogue method. The available items include
 * champions and weapons that are randomly chosen from the GameManager's list of
 * all champions and weapons.
 */
public class Shop {

	/**
	 * Fields
	 */

	private Configuration config = Configuration.getInstance();

	private ArrayList<Champion> availableChampions;

	private ArrayList<Weapon> availableWeapons;

	private GameManager gameManager = GameManager.getInstance();

	/**
	 * Accessor methods
	 */

	/**
	 * Get the value of availableChampions
	 *
	 * @return the value of availableChampions
	 */
	public ArrayList<Champion> getAvailableChampions() {
		return availableChampions;
	}

	/**
	 * Set the value of availableChampions
	 *
	 * @param availableChampions the new value of availableChampions
	 */
	public void setAvailableChampions(ArrayList<Champion> availableChampions) {
		this.availableChampions = availableChampions;
	}

	/**
	 * Get the value of availableWeapons
	 *
	 * @return the value of availableWeapons
	 */
	public ArrayList<Weapon> getAvailableWeapons() {
		return availableWeapons;
	}

	/**
	 * Set the value of availableWeapons
	 *
	 * @param availableWeapons the new value of availableWeapons
	 */
	public void setAvailableWeapons(ArrayList<Weapon> availableWeapons) {
		this.availableWeapons = availableWeapons;
	}

	/**
	 * Other methods
	 */

	/**
	 * Refresh the weekly catalogue by generating new champions and champions.
	 */
	public void generateCatalogue() {
		generateChampions();
		generateWeapons();
	}

	/**
	 * Refresh the available champions in the shop. The champions are randomly
	 * selected but they must not already be in shop or any teams. If there is not
	 * enough unique champions to populate the shop, then populate with any
	 * champion.
	 */
	public void generateChampions() {
		ArrayList<Champion> championsLeft = getRemainingChampions();

		availableChampions = new ArrayList<>();
		if (championsLeft.size() < config.NUM_TEAMS) {
			championsLeft = new ArrayList<>(gameManager.getAllChampions());
			System.out.println(
					"WARNING: Not enough champions available to create a unique shop catalogue! creating duplicate champions...");
		}
		for (int i = 0; i < config.NUM_TEAMS; i++) {
			Champion randomChampion = getRandomChampion(championsLeft);
			availableChampions.add(randomChampion);
			championsLeft.remove(randomChampion);
		}
	}

	/**
	 * Gets all remaining unique champions in the game
	 *
	 * @return The remaining unique champions in the game
	 */
	public ArrayList<Champion> getRemainingChampions() {
		ArrayList<Champion> championsLeft = new ArrayList<>(gameManager.getAllChampions());
		for (Team team : gameManager.getTeams()) {
			for (Champion champion : team.getChampions()) {
				if (championsLeft.contains(champion)) {
					championsLeft.remove(champion);
				}
			}
		}

		return championsLeft;
	}

	/**
	 * Return a randomly chosen champion from the given list.
	 *
	 * @param champions the list of champions to pick from
	 * @return the randomly chosen champion
	 */
	public static Champion getRandomChampion(ArrayList<Champion> champions) {
		Random random = new Random();
		int index = random.nextInt(champions.size());
		return champions.get(index); // .clone();
	}

	/**
	 * Returns a list of 8 unique champions for the player to choose from to form
	 * their team at startup
	 *
	 * @return a list of 8 unique champions for the player to choose from
	 */
	public ArrayList<Champion> getSetupChampions() {
		ArrayList<Champion> champs = new ArrayList<>();

		while (champs.size() < Configuration.NUM_SETUP_CHAMPIONS) {
			Champion candidate = getRandomChampion(gameManager.getAllChampions());

			// Ensure the chosen candidate is unique
			if (!champs.contains(candidate)) {
				champs.add(candidate);
			}
		}

		return champs;
	}

	/**
	 * Refresh the available weapons by populating with random weapons.
	 */
	public void generateWeapons() {
		ArrayList<Weapon> weaponsLeft = new ArrayList<>(gameManager.getAllWeapons());
		availableWeapons = new ArrayList<>();
		for (int i = 0; i < config.NUM_TEAMS; i++) {
			Weapon randomWeapon = getRandomWeapon(weaponsLeft);
			availableWeapons.add(randomWeapon);
			weaponsLeft.remove(randomWeapon);
		}
	}

	/**
	 * Return a randomly chosen weapon from all weapons.
	 *
	 * @param weapons the list of weapons to pick from
	 * @return the randomly chosen weapon
	 */
	public Weapon getRandomWeapon(ArrayList<Weapon> weapons) {
		Random random = new Random();
		int index = random.nextInt(weapons.size());
		return weapons.get(index).clone();
	}

	/**
	 * Rmove the given champion from available champions.
	 *
	 * @param champion the champion to remove
	 */
	public void removeChampion(Champion champion) {
		availableChampions.remove(champion);
	}

	/**
	 * Remove the given weapon from available weapons.
	 *
	 * @param weapon the weapon to remove
	 */
	public void removeWeapon(Weapon weapon) {
		availableWeapons.remove(weapon);
	}

}
