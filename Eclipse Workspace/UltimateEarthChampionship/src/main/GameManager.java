package main;

import java.util.ArrayList;

import weapons.Chainsaw;
import weapons.Pickaxe;
import weapons.Shield;
import weapons.Weapon;

public abstract class GameManager
{
	private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	
	public GameManager() {
		initialize();
		play();
	}
	
	public void initialize() {
		getWeapons().add(new Shield());
		getWeapons().add(new Pickaxe());
		getWeapons().add(new Chainsaw());
	}
	
	public abstract void play();

	public ArrayList<Weapon> getWeapons()
	{
		return weapons;
	}

	public void setWeapons(ArrayList<Weapon> weapons)
	{
		this.weapons = weapons;
	}
}
