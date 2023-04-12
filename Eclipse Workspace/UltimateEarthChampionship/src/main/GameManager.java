package main;

import java.util.ArrayList;
import java.util.List;

import champion.Champion;
import champion.champions.*;
import weapons.*;

public abstract class GameManager
{
	private static GameManager instance;
	
	private Shop shop;
	
	private ArrayList<Champion> allChampions; 
	private ArrayList<Weapon> allWeapons;
	
	protected GameManager() {
		shop = new Shop();
		
		allChampions = new ArrayList<Champion>(
    			List.of(new AdamSmith(), new BernardMontgomery(), new CharlesDarwin(), new Confucius(), new GeorgeWashington())
    			);
    	allWeapons = new ArrayList<Weapon>(
    			List.of(new Chainsaw(), new GolfClub(), new Pickaxe(), new Sledgehammer(), new Shield())
    			);
    	
	}
	
	public abstract void play();

	public static GameManager getInstance() {
		if (instance == null) {
			switch (GameInitializer.TYPE) {
			case GUI: {
				instance = new GraphicalGameManager();
				break;
			}
			case CLI: {
				instance = new CommandLineGameManager();
				break;
			}
			}
		}
		return instance;
	}
	
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

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

}
