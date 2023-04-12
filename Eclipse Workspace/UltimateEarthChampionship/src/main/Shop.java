package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import champion.Champion;
import champion.champions.AdamSmith;
import champion.champions.BernardMontgomery;
import champion.champions.CharlesDarwin;
import champion.champions.Confucius;
import champion.champions.GeorgeWashington;
import weapons.Chainsaw;
import weapons.GolfClub;
import weapons.Pickaxe;
import weapons.Shield;
import weapons.Sledgehammer;
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
    
    private GameManager gameManager;
    
    /**
     * Constructors
     */
    public void setGameManager() {
    	gameManager = GameManager.getInstance();
    }
    
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
		int index = random.nextInt(gameManager.getAllChampions().size());
		return gameManager.getAllChampions().get(index);
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
		int index = random.nextInt(gameManager.getAllWeapons().size());
		return gameManager.getAllWeapons().get(index);
	}

}
