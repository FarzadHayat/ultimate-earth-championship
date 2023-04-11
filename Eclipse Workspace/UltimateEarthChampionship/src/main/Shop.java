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
	
	// TODO: move numChampions and allChampions to a static data class or config class.
	private int numChampions = 5;
    private ArrayList<Champion> availableChampions;
    private ArrayList<Champion> allChampions;
    
    // TODO: move numWeapons and allWeapons to a static data class or config class.
    private int numWeapons = 5;
    private ArrayList<Weapon> availableWeapons;
    private ArrayList<Weapon> allWeapons;

    /**
     * Constructors
     */
    
    public Shop() {
    	allChampions = new ArrayList<Champion>(
    			List.of(new AdamSmith(), new BernardMontgomery(), new CharlesDarwin(), new Confucius(), new GeorgeWashington())
    			);
    	allWeapons = new ArrayList<Weapon>(
    			List.of(new Chainsaw(), new GolfClub(), new Pickaxe(), new Sledgehammer(), new Shield())
    			);
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
	 * Get the value of allChampions
	 * @return the value of allChampions
	 */
	public ArrayList<Champion> getAllChampions() {
		return allChampions;
	}

	/**
	 * Set the value of allChampions
	 * @param allChampions the new value of allChampions
	 */
	public void setAllChampions(ArrayList<Champion> allChampions) {
		this.allChampions = allChampions;
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
	 * Get the value of allWeapons
	 * @return the value of allWeapons
	 */
	public ArrayList<Weapon> getAllWeapons() {
		return allWeapons;
	}

	/**
	 * Set the value of allWeapons
	 * @param allWeapons the new value of allWeapons
	 */
	public void setAllWeapons(ArrayList<Weapon> allWeapons) {
		this.allWeapons = allWeapons;
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
		int index = random.nextInt(allChampions.size());
		return allChampions.get(index);
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
		int index = random.nextInt(allWeapons.size());
		return allWeapons.get(index);
	}

}
