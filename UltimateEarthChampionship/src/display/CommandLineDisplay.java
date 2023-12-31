package display;

import java.util.ArrayList;
import java.util.Scanner;

import events.RandomEventInfo;
import exception.InputException;
import manager.GameManager;
import match.DumbMatch;
import match.Match;
import match.MatchResult;
import model.Champion;
import model.Configuration;
import model.SetupManager;
import model.Shop;
import model.Team;
import model.Weapon;
import story.Cutscene;
import story.CutsceneSlide;

/**
 * A concrete implementation of the DisplayStrategy interface for the CLI. The
 * command line display uses the System.out to display the game and reads user
 * input from System.in by providing options to selects from for the player.
 */
public class CommandLineDisplay implements DisplayStrategy {

	/**
	 * The scanner reads the user input.
	 */
	private Scanner scanner;

	private Configuration config = Configuration.getInstance();

	private GameManager gameManager = GameManager.getInstance();

	/**
	 * Create a new CommandLineDisplay object.
	 */
	public CommandLineDisplay() {
		scanner = new Scanner(System.in);
	}

	/**
	 * Prompts the user for input.
	 *
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
	 * Display the given cutscene in the terminal. The cutscene loops through the
	 * slides in order until the end is reached.
	 *
	 * @param cutscene the cutscene to display
	 */
	@Override
	public void displayCutscene(Cutscene cutscene) {
		while (true) {
			// Get the next cutscene slide
			CutsceneSlide slide = cutscene.nextSlide();

			// If a null has returned, the cutscene has finished
			if (slide == null) {
				break;
			}

			// else print the cutscene next and prompt for input before continuing
			System.out.println(slide.getText());

			promptForInput();
		}

		gameManager.finishedCutscene();
	}

	/**
	 * Displays all the text related to setting up the game, )* Requires the player
	 * to input a team name, weeks of championship, their starting team, and a
	 * difficulty.
	 */
	@Override
	public void displaySetup() {
		System.out.println("Welcome to Ultimate Earth Championship!\n");

		// Data requested:
		String teamName = null;
		int gameWeeks = 0;
		ArrayList<Champion> chosenChampions = new ArrayList<>();
		float difficulty = 0;

		// Prompt for team name
		while (teamName == null) {
			System.out.println("Name your team:");
			System.out.println(" - Must be between " + config.MIN_TEAM_NAME_CHARS + " and " + config.MAX_TEAM_NAME_CHARS
					+ " characters");
			System.out.println(" - Cannot contain special characters \n");

			try {
				teamName = SetupManager.PromptForTeamName(promptForInput());
			} catch (InputException e) {
				System.out.println(e.getMessage() + " \n");
			}
		}
		System.out.println("Team name of " + teamName + " accepted. \n");

		// Prompt for game weeks
		while (gameWeeks == 0) {
			System.out.println("How many weeks will the tournament be?");
			System.out.println(
					" - Must be between " + config.MIN_NUM_GAME_WEEKS + " and " + config.MAX_NUM_GAME_WEEKS + "\n");

			try {
				gameWeeks = SetupManager.PromptForNumWeeks(promptForInput());
			} catch (InputException e) {
				System.out.println(e.getMessage() + " \n");
			}
		}
		System.out.println(gameWeeks + " weeks have been chosen");

		// Get champions that they can choose from:
		ArrayList<Champion> availChampions = gameManager.getShop().getSetupChampions();

		// Prompt for starting champions
		while (chosenChampions.size() < 4) {
			System.out.println("Chosen champions:");
			for (Champion champ : chosenChampions) {
				System.out.println(champ.getName());
			}

			System.out.println("\nChoose a champion:");
			// Print each champion with a corresponding number and their stats:
			int i = 1;
			for (Champion champ : availChampions) {
				System.out.println("[" + i + "] " + champ.getName() + " | Stamina: " + champ.getMaxStamina()
						+ " | Regen: " + champ.getRegen() + " | Offense: " + champ.getOffense() + " | Defense: "
						+ champ.getDefense());
				i++;
			}
			System.out.println("\nSelect the champion you would like:");

			Champion chosenChamp = null;
			try {
				chosenChamp = SetupManager.ChooseChampionFrom(availChampions, promptForInput());
				chosenChampions.add(chosenChamp);
			} catch (InputException e) {
				System.out.println(e.getMessage() + " \n");
			}
		}

		System.out.println("\n Your Team:");
		for (Champion champ : chosenChampions) {
			System.out.println(" -> " + champ.getName());
		}
		System.out.println("\n");

		// Prompt for difficulty:
		while (difficulty == 0) {
			System.out.println("Choose a difficulty:");
			System.out.println("- Difficulty can range from anywhere between 0.5 (easiest) to 2 (hardest)");
			try {
				difficulty = SetupManager.PromptForDifficulty(promptForInput());
			} catch (InputException e) {
				System.out.println(e.getMessage() + " \n");
			}
		}
		System.out.println("\n You have chosen a difficulty of " + difficulty);

		System.out.println("\n \n To Review:");
		System.out.println("Team name: " + teamName);
		System.out.println("Weeks in season: " + gameWeeks);
		System.out.println("Your Team:");
		for (Champion champ : chosenChampions) {
			System.out.println(" -> " + champ.getName());
		}
		System.out.println("Game difficulty: " + difficulty + "\n");

		System.out.println("Are you happy with this? [y/n]");
		System.out.println(" - Answering 'n' will restart the setup process");

		while (true) {
			String in = promptForInput();

			if (in.equals("y")) {
				// Setup complete, pass on data to gameManager
				gameManager.setupPlayerTeam(teamName, gameWeeks, availChampions, difficulty);
				break;
			}
			if (in.equals("n")) {
				// Restart
				displaySetup();
				return;
			}
		}
		gameManager.finishedSetup();
	}

	/**
	 * Display the player team in the terminal.
	 */
	@Override
	public void displayTeam() {
		// TODO Auto-generated method stub

	}

	/**
	 * Display the shop in the terminal.
	 */
	@Override
	public void displayShop() {
		Shop shop = gameManager.getShop();

		CommandLineUtilities.printHeader("Shop");

		CommandLineTable.printChampions(shop.getAvailableChampions());
		CommandLineUtilities.printLine();
		CommandLineTable.printWeapons(shop.getAvailableWeapons());
		CommandLineUtilities.printLine();

		CommandLineUtilities.printChampionOptions("BUY", shop.getAvailableChampions());
		CommandLineUtilities.printWeaponOptions("BUY", shop.getAvailableWeapons());

		System.out.println(promptForInput());
	}

	/**
	 * Display the stadium in the terminal.
	 */
	@Override
	public void displayStadium() {
		ArrayList<Team> teams = gameManager.getAITeams();

		CommandLineUtilities.printHeader("Stadium");

		CommandLineUtilities.printTeams(teams);
		CommandLineUtilities.printLine();

		CommandLineUtilities.printTeamOptions(teams);

		System.out.println(promptForInput());
	}

	/**
	 * Display champion setup in the terminal.
	 */
	@Override
	public void displayChampionSetup() {
		ArrayList<Champion> chosenChampions = new ArrayList<>();
		ArrayList<Champion> championsLeft = new ArrayList<>();
		championsLeft.addAll(gameManager.getPlayerTeam().getChampions());
		while (chosenChampions.size() < Configuration.NUM_CHOSEN_CHAMPIONS) {
			CommandLineUtilities.printHeader("CHAMPION SETUP");
			CommandLineTable.printChampions(chosenChampions);
			CommandLineUtilities.printChampionOptions("SELECT", championsLeft);
			try {
				Champion champion = SetupManager.ChooseChampionFrom(championsLeft, promptForInput());
				chosenChampions.add(champion);
			} catch (InputException e) {
				System.out.println(e.getMessage() + " \n");
			}
		}

		CommandLineUtilities.printHeader("CHAMPION SETUP");
		CommandLineTable.printChampions(chosenChampions);

		while (true) {
			System.out.println("Are you happy with this? [y/n]");
			System.out.println(" - Answering 'n' will restart the champion setup process");

			String in = promptForInput();

			if (in.equals("y")) {
				// Weapon setup complete, assign chosen champions to the player team
				gameManager.getPlayerTeam().setChosenChampions(chosenChampions);
				break;
			}
			if (in.equals("n")) {
				// Restart
				displayChampionSetup();
				return;
			}
		}
		gameManager.finishedChampionSetup();
	}

	/**
	 * Display weapon setup in the terminal. Assumes the player team has already
	 * selected champions.
	 */
	@Override
	public void displayWeaponSetup() {
		ArrayList<Weapon> chosenWeapons = new ArrayList<>();
		ArrayList<Weapon> WeaponsLeft = new ArrayList<>();
		WeaponsLeft.addAll(gameManager.getPlayerTeam().getWeapons());
		while (chosenWeapons.size() < Configuration.NUM_CHOSEN_CHAMPIONS) {
			CommandLineUtilities.printHeader("WEAPON SETUP");
			CommandLineTable.printWeapons(chosenWeapons);
			CommandLineUtilities.printWeaponOptions("SELECT", WeaponsLeft);

			System.out.println("Are you happy with this? [y/n]");
			System.out.println(" - You can continue to select more weapons or continue with the current selection");
			System.out.println(" - Answering 'y' will start the match");
			System.out.println(" - Answering 'n' will restart the weapon setup process");

			String in = promptForInput();

			if (in.equals("y")) {
				// Weapon setup complete, assign chosen weapons to the player team
				gameManager.getPlayerTeam().setChosenWeapons(chosenWeapons);
				break;
			}
			if (in.equals("n")) {
				// Restart
				displayWeaponSetup();
				return;
			}
			try {
				Weapon weapon = SetupManager.ChooseWeaponFrom(WeaponsLeft, in);
				chosenWeapons.add(weapon);
			} catch (InputException e) {
				System.out.println(e.getMessage() + " \n");
			}
		}
		gameManager.finishedWeaponSetup();
	}

	/**
	 * Display the match in the terminal.
	 */
	@Override
	public void displayMatch(Match match) {
		displayMatchResults(((DumbMatch) match).getMatchResult());
	}

	/**
	 * Displays the match results in the terminal.
	 */
	@Override
	public void displayMatchResults(MatchResult matchResult) {
		StringBuilder text = new StringBuilder();
		if (matchResult.winningTeam == gameManager.getPlayerTeam()) {
			double winningMoneyRounded = Math.round(matchResult.winningTeamMoney * 100.0) / 100.0;
			double winningScoreRounded = Math.round(matchResult.winningTeamScore * 100.0) / 100.0;
			text.append("You have won the match! Money awarded: ").append(winningMoneyRounded)
					.append(" Score awarded: ").append(winningScoreRounded);
		} else {
			double losingMoneyRounded = Math.round(matchResult.losingTeamMoney * 100.0) / 100.0;
			double losingScoreRounded = Math.round(matchResult.losingTeamScore * 100.0) / 100.0;
			text.append("You have lost the match! Money awarded: ").append(losingMoneyRounded)
					.append(" Score awarded: ").append(losingScoreRounded);
		}
		System.out.println(text.toString());
	}

	/**
	 * Display the week results in the terminal.
	 */
	@Override
	public void displayWeekResults(ArrayList<RandomEventInfo> randomEvents) {
		for (RandomEventInfo randomEvent : randomEvents) {
			System.out.println(
					String.format("%s: %s (%s)", randomEvent.name, randomEvent.description, randomEvent.effectString));
			promptForInput();
		}
		GameManager.getInstance().finishedWeek();
	}

	/**
	 * Display the game results in the terminal.
	 */
	@Override
	public void displayGameResults(ArrayList<Team> teams) {
		// TODO Auto-generated method stub
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
	}

}
