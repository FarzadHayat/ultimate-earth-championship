package main;

import java.util.ArrayList;
import java.util.Random;

import champion.Champion;
import exception.InsufficientFundsException;
import exception.TeamFullException;
import weapon.Weapon;


/**
 * The Shop class represents a store where players can purchase items for use in the game.
 * The shop has a weekly catalog of available items that can be refreshed using the generateCatalogue method.
 * The available items include champions and weapons that are randomly chosen from the GameManager's list of all
 * champions and weapons.
 */
public class Shop {

	/**
	 * Fields
	 */
	
	private Configuration config = Configuration.getInstance();
	
    private ArrayList<Champion> availableChampions;
    
    private ArrayList<Weapon> availableWeapons;
        
    /**
     * Accessor methods
     */

    /**
	 * Get the value of availableChampions
	 * @return the value of availableChampions
	 */
	public ArrayList<Champion> getAvailableChampions() {
		return availableChampions;
	}

	/**
	 * Set the value of availableChampions
	 * @param availableChampions the new value of availableChampions
	 */
	public void setAvailableChampions(ArrayList<Champion> availableChampions) {
		this.availableChampions = availableChampions;
	}

	/**
	 * Get the value of availableWeapons
	 * @return the value of availableWeapons
	 */
	public ArrayList<Weapon> getAvailableWeapons() {
		return availableWeapons;
	}

	/**
	 * Set the value of availableWeapons
	 * @param availableWeapons the new value of availableWeapons
	 */
	public void setAvailableWeapons(ArrayList<Weapon> availableWeapons) {
		this.availableWeapons = availableWeapons;
	}
	
	/**
	 * Other methods
	 */
	
	/**
	 * Refresh the weekly catalogue by regenerating availableChampions and availableWeapons.
	 */
	public void generateCatalogue() {
		generateChampions();
		generateWeapons();
	}
	
	/**
	 * Refresh the available champions by populating with random but unique champions from allChampions.
	 */
	public void generateChampions() {
        availableChampions = new ArrayList<Champion>();
        while (availableChampions.size() < config.NUM_TEAMS) {
        	Champion champion = getRandomChampion();
        	if (!availableChampions.contains(champion)) {
        		availableChampions.add(champion);
        	}
        }
	}
	
	/**
	 * Return a randomly chosen champion from allChampions.
	 * @return the randomly chosen champion
	 */
	public Champion getRandomChampion() {
		Random random = new Random();
		int index = random.nextInt(GameManager.getInstance().getAllChampions().size());
		return GameManager.getInstance().getAllChampions().get(index);
	}
    
	/**
	 * Refresh the available weapons by populating with random but unique weapons from allWeapons.
	 */
	public void generateWeapons() {
		availableWeapons = new ArrayList<Weapon>();
        while (availableWeapons.size() < config.NUM_TEAMS) {
        	Weapon weapon = getRandomWeapon();
        	if (!availableWeapons.contains(weapon)) {
        		availableWeapons.add(weapon);
        	}
        }
	}
	
	/**
	 * Return a randomly chosen weapon from allWeapons.
	 * @return the randomly chosen weapon
	 */
	public Weapon getRandomWeapon() {
		Random random = new Random();
		int index = random.nextInt(GameManager.getInstance().getAllWeapons().size());
		return GameManager.getInstance().getAllWeapons().get(index);
	}
	
	/**
	 * Buys the weapon for the team.
	 * 1. Removes the weapon price from the team's money.
	 * 2. Adds the weapon to the team's reserve weapons
	 * 3. Removes the weapon from the shop.
	 * @throws InsufficientFundsException if team cannot afford to purchase the weapon
	 * @throws TeamFullException if team reserve weapons is already full
	 */
	public void buyWeapon(Weapon weapon, Team team) throws InsufficientFundsException, TeamFullException {
//		check the team has enough money
		if (!team.hasMoney(weapon.getPrice())) {
			throw new InsufficientFundsException("You do not have enough money to purchase this weapon!");
		}
//		charge the team for the weapon price
		team.addMoney(-weapon.getPrice());
//		try to add the weapon to the team reserve weapons
		try {
			team.addReserveWeapon(weapon);
		}
//		undo the money reduction if we encounter a team full exception
		catch (TeamFullException e) {
			team.addMoney(weapon.getPrice());
			throw new TeamFullException(e.getMessage());
		}
		availableWeapons.remove(weapon);
	}
	
	/**
	 * Buys the champion for the team.
	 * 1. Removes the champion price from the team's money.
	 * 2. Adds the champion to the team's reserve champions
	 * 3. Removes the champion from the shop.
	 * @throws InsufficientFundsException if team cannot afford to purchase the champion
	 * @throws TeamFullException if team chosen champions and reserve champions are both already full
	 */
	public void buyChampion(Champion champion, Team team) throws InsufficientFundsException, TeamFullException {
//		check the team has enough money
		if (!team.hasMoney(champion.getPrice())) {
			throw new InsufficientFundsException("You do not have enough money to purchase this champion!");
		}
//		charge the team for the champion price
		team.addMoney(-champion.getPrice());
//		try to add the weapon to the team champions
		try {
			team.addChampion(champion);
		}
//		undo the money reduction if we encounter a team full exception
		catch (TeamFullException e) {
			team.addMoney(champion.getPrice());
			throw new TeamFullException(e.getMessage());
		}
		availableChampions.remove(champion);
	}

}
