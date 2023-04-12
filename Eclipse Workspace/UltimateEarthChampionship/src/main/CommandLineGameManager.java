package main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import champion.Champion;
import views.CardType;
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
		
		ArrayList<String> options = new ArrayList<String>();
		options.addAll(getChampionOptions(getShop().getAvailableChampions(), CardType.CAN_BUY));
		options.addAll(getWeaponOptions(getShop().getAvailableWeapons(), CardType.CAN_BUY));
		
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
	
	private ArrayList<String> getChampionOptions(ArrayList<Champion> champions, CardType type) {
		ArrayList<String> names = new ArrayList<String>();
		for (Champion champion : champions) {
			String text;
			switch (type) {
			case CAN_BUY: {
				text = "BUY: ";
				break;
			}
			case CAN_SELL: {
				text = "SELL: ";
				break;
			}
			default: {
				text = "";
				break;
			}
			}
			text += champion.getName();
			names.add(text);
		}
		return names;
	}
	
	private ArrayList<String> getWeaponOptions(ArrayList<Weapon> weapons, CardType type) {
		ArrayList<String> names = new ArrayList<String>();
		for (Weapon weapon : weapons) {
			String text;
			switch (type) {
			case CAN_BUY: {
				text = "BUY: ";
				break;
			}
			case CAN_SELL: {
				text = "SELL: ";
				break;
			}
			default: {
				text = "";
				break;
			}
			}
			text += weapon.getName();
			names.add(text);
		}
		return names;
	}

}
