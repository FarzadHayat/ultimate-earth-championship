package display;

import java.util.ArrayList;

import model.Champion;
import model.Team;
import model.Weapon;

/**
 * An abstract class that contains useful static methods for printing to the
 * terminal. This class should be used by the command line display to display
 * the game state in the CLI version.
 */
public abstract class CommandLineUtilities {

	/**
	 * The ASCII character to use as filler for the headers and lines
	 */
	public static final String FILLER = "=";

	/**
	 * The number of fillers to print for a full line.
	 */
	public static final int LINE_WIDTH = 114;

	/**
	 * Prints a view to the console.
	 *
	 * @param title   The title of the view.
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
		StringBuilder text = new StringBuilder();
		for (int i = 0; i < LINE_WIDTH; i++) {
			text.append(FILLER);
		}
		System.out.println(text.toString());
	}

	/**
	 * Prints a title to the console.
	 *
	 * @param title The title to print.
	 */
	public static void printTitle(String title) {
		int numberOfFillers = Integer.max((LINE_WIDTH - title.length() - 2) / 2, 0);
		String text = "";
		for (int i = 0; i < numberOfFillers; i++) {
			text += FILLER;
		}
		if (title.length() % 2 == 0) {
			System.out.println(text + " " + title + " " + text);
		} else {
			System.out.println(text + " " + title + " " + FILLER + text);
		}
	}

	/**
	 * Prints a header to the console.
	 *
	 * @param title the title of the header.
	 */
	public static void printHeader(String title) {
		printLine();
		printTitle(title);
		printLine();
	}

	/**
	 * Prints content to the console line by line.
	 *
	 * @param content The content to print.
	 */
	public static void printContent(ArrayList<String> content) {
		for (String line : content) {
			System.out.println(line);
		}
	}

	/**
	 * Prints options to the console line by line and numbered.
	 *
	 * @param options The options to print.
	 */
	public static void printOptions(ArrayList<String> options) {
		for (int i = 0; i < options.size(); i++) {
			System.out.println(String.valueOf(i + 1) + " " + options.get(i));
		}
	}

	/**
	 * Print an option line with the given index, prefix, and text.
	 *
	 * @param i      the number of the current option
	 * @param prefix the prefix to show for the option
	 * @param text   the main text of the option
	 */
	public static void printOption(int i, String prefix, String text) {
		System.out.println(String.format("%s %s: %s", String.valueOf(i), prefix, text));
	}

	/**
	 * Print all the teams in the given list. Each team is printed using the
	 * printTeam method.
	 *
	 * @param teams the teams to print
	 */
	public static void printTeams(ArrayList<Team> teams) {
		for (int i = 0; i < teams.size(); i++) {
			if (i != 0) {
				CommandLineUtilities.printLine();
			}
			CommandLineUtilities.printTeam(teams.get(i));
		}
	}

	/**
	 * Print the given team by printing the team name followed by a table of the
	 * team champions.
	 *
	 * @param team the team to print
	 */
	public static void printTeam(Team team) {
		System.out.println("TEAM NAME: " + team.getName());
		CommandLineTable.printChampions(team.getChampions());
	}

	/**
	 * Print options for the list of champions using the given prefix. Each champion
	 * is printed as the prefix followed by the champion's name. The prefix is
	 * usually set to either "BUY" or "SELL".
	 *
	 * @param prefix    the prefix to use
	 * @param champions the list of champions to print
	 */
	public static void printChampionOptions(String prefix, ArrayList<Champion> champions) {

		for (int i = 0; i < champions.size(); i++) {
			printOption(i + 1, prefix + " CHAMPION", champions.get(i).getName());
		}
	}

	/**
	 * Print options for the list of weapons using the given prefix. Each weapon is
	 * printed as the prefix followed by the weapon's name. The prefix is usually
	 * set to either "BUY" or "SELL".
	 *
	 * @param prefix  the prefix to use
	 * @param weapons the list of weapons to print
	 */
	public static void printWeaponOptions(String prefix, ArrayList<Weapon> weapons) {
		for (int i = 0; i < weapons.size(); i++) {
			printOption(i + 1, prefix + " WEAPON", weapons.get(i).getName());
		}
	}

	/**
	 * Print a list of options for the given teams. Each team is followed by the
	 * prefix "FIGHT TEAM" to show that the player can fight this team by selecting
	 * this option.
	 *
	 * @param teams the list of teams to print
	 */
	public static void printTeamOptions(ArrayList<Team> teams) {
		for (int i = 0; i < teams.size(); i++) {
			printOption(i + 1, "FIGHT TEAM", teams.get(i).getName());
		}
	}

}
