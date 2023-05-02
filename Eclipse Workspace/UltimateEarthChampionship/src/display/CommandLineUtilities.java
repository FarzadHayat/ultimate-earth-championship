package display;

import java.util.ArrayList;
import model.Champion;
import model.Team;
import model.Weapon;
import views.PurchasableCard.CardType;

public abstract class CommandLineUtilities {

	public static final String FILLER = "=";
	public static final int LINE_WIDTH = 114;
	
	/**
	 * Prints a view to the console.
	 * @param title The title of the view.
	 * @param content The content of the view.
	 * @param options The options available in the view.
	 */
	public static void printView(String title, ArrayList<String> content, ArrayList<String> options) {
		printHeader(title);
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
	 * Prints a header to the console.
	 * @param title the title of the header.
	 */
	public static void printHeader(String title) {
		printLine();
		printTitle(title);
		printLine();
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

	public static void printOption(int i, String prefix, String text) {
		System.out.println(String.format("%s %s: %s", String.valueOf(i), prefix, text));
	}
	
	public static void printTeams(ArrayList<Team> teams) {
		for (int i = 0; i < teams.size(); i++) {
			if (i != 0) {
				CommandLineUtilities.printLine();
			}
			CommandLineUtilities.printTeam(teams.get(i));
		}
	}
	
	private static void printTeam(Team team) {
		System.out.println("TEAM NAME: " + team.getName());
		CommandLineTable.printChampions(team.getChampions());
	}

	public static void printChampionOptions(ArrayList<Champion> champions, CardType cardType) {
		
		for (int i = 0; i < champions.size(); i++) {
			printOption(i, getPrefix(cardType) + " CHAMPION", champions.get(i).getName());
		}
	}

	public static void printWeaponOptions(ArrayList<Weapon> weapons, CardType cardType) {
		for (int i = 0; i < weapons.size(); i++) {
			printOption(i, getPrefix(cardType) + " CHAMPION", weapons.get(i).getName());
		}
	}
	
	public static void printTeamOptions(ArrayList<Team> teams) {
		for (int i = 0; i < teams.size(); i++) {
			printOption(i, "FIGHT TEAM", teams.get(i).getName());
		}
	}
	
	private static String getPrefix(CardType cardType) {
		String prefix;
		switch (cardType) {
		case CAN_BUY: {
			prefix = "BUY";
		}
		
		case CAN_SELL: {
			prefix = "SELL";
		}
		default: {
			prefix = "";
		}
		}
		return prefix;
	}
	
}
