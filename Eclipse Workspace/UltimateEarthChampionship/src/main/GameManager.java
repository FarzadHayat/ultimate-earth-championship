package main;

import java.util.ArrayList;

import champion.Champion;
import weapons.Chainsaw;
import weapons.Pickaxe;
import weapons.Shield;
import weapons.Weapon;

public abstract class GameManager
{
	private static GameManager instance;
	
	private ArrayList<Champion> allChampions = new ArrayList<Champion>(); 
	private ArrayList<Weapon> allWeapons = new ArrayList<Weapon>();
	
	protected GameManager() {
		initialize();
		play();
	}
	
	public void initialize() {
		getAllWeapons().add(new Shield());
		getAllWeapons().add(new Pickaxe());
		getAllWeapons().add(new Chainsaw());
	}
	
	public abstract void play();

	public ArrayList<Champion> getAllChampions() {
		return allChampions;
	}

	public void setAllChampions(ArrayList<Champion> allChampions) {
		this.allChampions = allChampions;
	}

	public ArrayList<Weapon> getAllWeapons() {
		return allWeapons;
	}

	public void setAllWeapons(ArrayList<Weapon> allWeapons) {
		this.allWeapons = allWeapons;
	}
	
	public static GameManager getInstance() {
		return instance;
	}
	
	protected static void setInstance(GameManager gameManager) {
		instance = gameManager;
	}

}
