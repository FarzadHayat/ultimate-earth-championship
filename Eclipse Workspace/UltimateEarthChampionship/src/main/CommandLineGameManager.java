package main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import champion.Champion;
import views.CommandLineView;
import weapons.Weapon;

public class CommandLineGameManager extends GameManager
{

	private CommandLineView commandLineView;
	
	@Override
	public void play()
	{
		commandLineView = new CommandLineView();
		getShop().generateCatalogue();
		displayShop();
	}
	
	public void displayShop() {
		ArrayList<String> content = new ArrayList<>();
		
//		content.add(Champion.toStringHeader());
		ArrayList<String> championStrings = getChampionStrings(getShop().getAvailableChampions());
		content.addAll(championStrings);
		
		content.add(Weapon.toStringHeader());
		content.add("       [---------------------------------------------------------------------------------------------------]");
		ArrayList<String> weaponStrings = getWeaponStrings(getShop().getAvailableWeapons());
		content.addAll(weaponStrings); 
		
		ArrayList<String> options = new ArrayList<String>(List.of("One", "Two", "Three"));
		
		commandLineView.printView("My title", content, options);
	}
	
	private ArrayList<String> getChampionStrings(ArrayList<Champion> champions) {
		ArrayList<String> championStrings = getShop().getAvailableChampions().stream()
				.map(Champion::toString)
				.collect(Collectors.toCollection(ArrayList::new));
		return championStrings;
	}
	
	private ArrayList<String> getWeaponStrings(ArrayList<Weapon> weapons) {
		ArrayList<String> weaponStrings = getShop().getAvailableWeapons().stream()
				.map(Weapon::toString)
				.collect(Collectors.toCollection(ArrayList::new));
		return weaponStrings;
	}

}
