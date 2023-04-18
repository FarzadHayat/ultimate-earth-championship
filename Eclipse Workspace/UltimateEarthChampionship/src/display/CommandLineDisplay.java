package display;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import champion.Champion;
import main.GameEnvironment;
import main.GameManager;
import main.Match;
import main.Shop;
import main.Team;
import views.CardType;
import weapon.Weapon;

public class CommandLineDisplay implements DisplayStrategy {
	private static final String FILLER = "=";
	private static final int LINE_WIDTH = 114;
	
	private Scanner scanner;
	
	private GameManager gameManager = GameManager.getInstance();
	
	/**
	 * Creat a new CommandLineDisplay object.
	 */
	public CommandLineDisplay() {
		scanner = new Scanner(System.in);
	}
	
	/**
	 * Prints a view to the console.
	 * @param title The title of the view.
	 * @param content The content of the view.
	 * @param options The options available in the view.
	 */
	public static void printView(String title, ArrayList<String> content, ArrayList<String> options) {
		printLine();
		printTitle(title);
		printLine();
		printContent(content);
		printLine();
		printOptions(options);
	}
	
	/**
	 * Prints a line of fillers to the console.
	 */
	public static void printLine() {
		String text = "";
		for (int i = 0; i < LINE_WIDTH; i++) {
			text += FILLER;
		}
		System.out.println(text);
	}
	
	/**
	 * Prints a title to the console.
	 * @param title The title to print.
	 */
	public static void printTitle(String title) {
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
	
	/**
	 * Prints content to the console line by line.
	 * @param content The content to print.
	 */
	public static void printContent(ArrayList<String> content) {
		for (String line : content) {
			System.out.println(line);
		}
	}
	
	/**
	 * Prints options to the console line by line and numbered.
	 * @param options The options to print.
	 */
	public static void printOptions(ArrayList<String> options) {
		for (int i = 0; i < options.size(); i++) {
			System.out.println(String.valueOf(i+1) + " " + options.get(i));
		}
	}
	
	/**
	 * Prompts the user for input.
	 * @return The user's input.
	 */
	private String promptForInput() {
		System.out.print("> ");
		return scanner.nextLine();
	}
	
	/**
	 * Closes the scanner used to receive input from the user.
	 */
	public void closeScanner() {
		scanner.close();
	}
	
	/**
	 * Displays the shop to the console.
	 * @param shop The shop to display.
	 */
	public void displayShop() {
		Shop shop = gameManager.getShop();
		
		ArrayList<String> content = new ArrayList<>();
		
//		content.add(Champion.toStringHeader());
		ArrayList<String> championStrings = getChampionStrings(shop.getAvailableChampions());
		content.addAll(championStrings);
		
		content.addAll(getWeaponStrings(shop.getAvailableWeapons()));
		
		ArrayList<String> options = new ArrayList<String>();
		options.addAll(getChampionOptions(shop.getAvailableChampions(), CardType.CAN_BUY));
		options.addAll(getWeaponOptions(shop.getAvailableWeapons(), CardType.CAN_BUY));
		
		printView("Shop", content, options);
		System.out.println(promptForInput());
	}
	
	/**
	 * Returns an ArrayList of String representations of the given list of Champion objects.
	 * @param champions the ArrayList of Champion objects to convert to strings
	 * @return an ArrayList of String representations of the given list of Champion objects
	 */
	private static ArrayList<String> getChampionStrings(ArrayList<Champion> champions) {
		ArrayList<String> championStrings = champions.stream()
				.map(Champion::toString)
				.collect(Collectors.toCollection(ArrayList::new));
		return championStrings;
	}

	/**
	 * Returns an ArrayList of String options for the given list of Champion objects and CardType.
	 * @param champions the ArrayList of Champion objects to get options for
	 * @param type the CardType to use to generate the options
	 * @return an ArrayList of String options for the given list of Champion objects and CardType
	 */
	private static ArrayList<String> getChampionOptions(ArrayList<Champion> champions, CardType type) {
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

	/**
	 * Returns an ArrayList of String options for the given list of Weapon objects and CardType.
	 * @param weapons the ArrayList of Weapon objects to get options for
	 * @param type the CardType to use to generate the options
	 * @return an ArrayList of String options for the given list of Weapon objects and CardType
	 */
	private static ArrayList<String> getWeaponOptions(ArrayList<Weapon> weapons, CardType type) {
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
	
	/**
	 * Returns a string list representing the list of weapons.
	 * The first element in the list is a header for the weapon labels.
	 * The second element is a divider line.
	 * The rest of the elements are the weapons with just the stats for each.
	 */
	public static ArrayList<String> getWeaponStrings(ArrayList<Weapon> weaponList) {
		ArrayList<String> stringList = new ArrayList<String>();
		stringList.add(getWeaponHeaderString());
		stringList.add(getDividerString());
		for (Weapon weapon : weaponList) {
			stringList.add(getWeaponString(weapon));
		}
		return stringList;
	}
	
	/**
	 * Returns a line to divide the table header from the table body.
	 */
	public static String getDividerString() {
		return "       [--------------------------------------------------------------------------------------------------------]";
	}
	
	/**
	 * Returns a string representation of the given Weapon's stats without labels.
	 */
	public static String getWeaponString(Weapon weapon) {
		return String.format("       [ %-20s | %17s | %13s | %13s | %5s | %19s ]", weapon.getName(), weapon.getDamageMultiplier(),
				weapon.getOffenseBoost(), weapon.getDefenseBoost(), weapon.getPrice(), weapon.getPriceChangeWeekly());
	}
	
	/**
	 * Returns a header string for the Weapon class that specifies the format of the `displayWeapon` method's output.
	 */
	public static String getWeaponHeaderString() {
		return "Weapon [ Name                 | Damage multiplier | Offense boost | Defense boost | Price | Price change weekly ]";
	}

	@Override
	public void displayStory(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySetup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayHome(GameEnvironment gameEnvironment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayTeam(Team team) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMatches(ArrayList<Match> matches) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayLiveMatch(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMatchResults(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayWeekResults(GameEnvironment gameEnvironment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayGameResults(GameEnvironment gameEnvironment) {
		// TODO Auto-generated method stub
		
	}
	
}
