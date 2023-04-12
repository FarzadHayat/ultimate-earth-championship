package views;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import champion.Champion;
import main.Shop;
import weapons.Weapon;

public class CommandLineView
{
	private static final String FILLER = "=";
	private static final int LINE_WIDTH = 108;
	
	private Scanner scanner;
	
	public CommandLineView() {
		scanner = new Scanner(System.in);
	}
	
	private static void printView(String title, ArrayList<String> content, ArrayList<String> options) {
		printLine();
		printTitle(title);
		printLine();
		printContent(content);
		printLine();
		printOptions(options);
	}
	
	private static void printLine() {
		String text = "";
		for (int i = 0; i < LINE_WIDTH; i++) {
			text += FILLER;
		}
		System.out.println(text);
	}
	
	private static void printTitle(String title) {
		int numberOfFillers = Integer.max((int) ((LINE_WIDTH - title.length() - 2) / 2), 0);
		String text = "";
		for (int i = 0; i < numberOfFillers; i++) {
			text += FILLER;
		}
		if (title.length() % 2 == 0) {
			System.out.println(text + " " + title + " " + text);
		}
		else {
			System.out.println(text + " " + title + " " + FILLER + text);
		}
	}
	
	private static void printContent(ArrayList<String> content) {
		for (String line : content) {
			System.out.println(line);
		}
	}
	
	private static void printOptions(ArrayList<String> options) {
		for (int i = 0; i < options.size(); i++) {
			System.out.println(String.valueOf(i+1) + " " + options.get(i));
		}
	}
	
	private String promptForInput() {
		System.out.print("> ");
		return scanner.nextLine();
	}
	
	public void closeScanner() {
		scanner.close();
	}
	
	public void displayShop(Shop shop) {
		ArrayList<String> content = new ArrayList<>();
		
//		content.add(Champion.toStringHeader());
		ArrayList<String> championStrings = getChampionStrings(shop.getAvailableChampions());
		content.addAll(championStrings);
		
		content.add(Weapon.toStringHeader());
		content.add("       [---------------------------------------------------------------------------------------------------]");
		ArrayList<String> weaponStrings = getWeaponStrings(shop.getAvailableWeapons());
		content.addAll(weaponStrings); 
		
		ArrayList<String> options = new ArrayList<String>();
		options.addAll(getChampionOptions(shop.getAvailableChampions(), CardType.CAN_BUY));
		options.addAll(getWeaponOptions(shop.getAvailableWeapons(), CardType.CAN_BUY));
		
		printView("Shop", content, options);
		System.out.println(promptForInput());
	}
	
	private ArrayList<String> getChampionStrings(ArrayList<Champion> champions) {
		ArrayList<String> championStrings = champions.stream()
				.map(Champion::toString)
				.collect(Collectors.toCollection(ArrayList::new));
		return championStrings;
	}
	
	private ArrayList<String> getWeaponStrings(ArrayList<Weapon> weapons) {
		ArrayList<String> weaponStrings = weapons.stream()
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
