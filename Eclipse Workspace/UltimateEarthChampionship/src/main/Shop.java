package main;

import java.util.ArrayList;
import java.util.Random;

import champion.Champion;
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

}
