package main;

import java.util.ArrayList;
import java.util.Random;

import champion.Champion;
import weapons.Weapon;

public class Shop {

	/**
	 * Fields
	 */
	
	// TODO: move numChampions to config class.
	private int numChampions = 4;
    private ArrayList<Champion> availableChampions;
    
    // TODO: move numWeapons to config class.
    private int numWeapons = 4;
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
        while (availableChampions.size() < numChampions) {
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
        while (availableWeapons.size() < numWeapons) {
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
