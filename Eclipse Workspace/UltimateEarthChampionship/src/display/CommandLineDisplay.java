package display;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import exception.InputException;
import manager.GameManager;
import model.Champion;
import model.GameEnvironment;
import match.*;
import model.Shop;
import model.Team;
import model.Weapon;
import story.Cutscene;
import story.CutsceneSlide;
import model.SetupManager;
import views.PurchasableCard.CardType;

public class CommandLineDisplay implements DisplayStrategy {
	private static final String FILLER = "=";
	private static final int LINE_WIDTH = 114;
	
	private Scanner scanner;
	
	private model.Configuration config = model.Configuration.getInstance();
	
	private GameManager gameManager = GameManager.getInstance();
	
	/**
	 * Create a new CommandLineDisplay object.
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
	 * @param cardType the CardType to use to generate the options
	 * @return an ArrayList of String options for the given list of Champion objects and CardType
	 */
	private static ArrayList<String> getChampionOptions(ArrayList<Champion> champions, CardType cardType) {
		ArrayList<String> names = new ArrayList<String>();
		for (Champion champion : champions) {
			String text;
			switch (cardType) {
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
	 * @param cardType the CardType to use to generate the options
	 * @return an ArrayList of String options for the given list of Weapon objects and CardType
	 */
	private static ArrayList<String> getWeaponOptions(ArrayList<Weapon> weapons, CardType cardType) {
		ArrayList<String> names = new ArrayList<String>();
		for (Weapon weapon : weapons) {
			String text;
			switch (cardType) {
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

	/**
	 * Displays all the text related to setting up the game,
	 * Requires the player to input a team name,
	 * Weeks of championship,
	 * Choose their starting team,
	 * Choose a difficulty
	 */
	@Override
	public void displaySetup() {
		System.out.println("Welcome to Ultimate Earth Championship!\n");
		
		// Data requested:
		String teamName = null;
		int gameWeeks = 0;
		ArrayList<Champion> chosenChampions = new ArrayList<Champion>();
		float difficulty = 0;
		
		// Prompt for team name
		while (teamName == null)
		{
			System.out.println("Name your team:");
			System.out.println(" - Must be between " + config.MIN_TEAM_NAME_CHARS + " and "+config.MAX_TEAM_NAME_CHARS+" characters");
			System.out.println(" - Cannot contain special characters \n");
			
			try 
			{
				teamName = SetupManager.PromptForTeamName(promptForInput());
			} 
			catch (InputException e) 
			{
				System.out.println(e.getMessage() + " \n");
			}
		}
		System.out.println("Team name of " + teamName + " accepted. \n");
		
		// Prompt for game weeks
		while (gameWeeks == 0)
		{
			System.out.println("How many weeks will the tournament be?");
			System.out.println(" - Must be between " + config.MIN_NUM_GAME_WEEKS + " and " + config.MAX_NUM_GAME_WEEKS + "\n");
			
			try 
			{
				gameWeeks = SetupManager.PromptForNumWeeks(promptForInput());
			} 
			catch (InputException e) 
			{
				System.out.println(e.getMessage() + " \n");
			}
		}
		System.out.println(gameWeeks + " weeks have been chosen");
		
		// Get champions that they can choose from:
		ArrayList<Champion> availChampions = gameManager.getShop().getStartingChampions();
		
		// Prompt for starting champions
		while (chosenChampions.size() < 4)
		{
			System.out.println("Chosen champions:");
			for (Champion champ : chosenChampions)
			{
				System.out.println(champ.getName());
			}
			
			System.out.println("\nChoose a champion:");
			// Print each champion with a corresponding number and their stats:
			int i = 1;
			for (Champion champ : availChampions)
			{
				System.out.println("["+i+"] " + champ.getName() + 
						" | Health: " + champ.getMaxHealth() +
						" | Stamina: " + champ.getStamina() +
						" | Offense: " + champ.getOffense() + 
						" | Defense: " + champ.getDefense());
				i++;
			}
			System.out.println("\nSelect the champion you would like:");
			
			Champion chosenChamp = null;
			try 
			{
				chosenChamp = SetupManager.ChooseChampionFrom(availChampions, promptForInput());
			} 
			catch (InputException e) 
			{
				System.out.println(e.getMessage() + " \n");
			}
			
			chosenChampions.add(chosenChamp);
		}
		
		System.out.println("\n Your Team:");
		for (Champion champ : chosenChampions)
		{
			System.out.println(" -> " + champ.getName());
		}
		System.out.println("\n");
		
		// Prompt for difficulty:
		while (difficulty == 0)
		{
			System.out.println("Choose a difficulty:");
			System.out.println("- Difficulty can range from anywhere between 0.5 (easiest) to 2 (hardest)");
			try 
			{
				difficulty = SetupManager.PromptForDifficulty(promptForInput());
			} 
			catch (InputException e) 
			{
				System.out.println(e.getMessage() + " \n");
			}
		}
		System.out.println("\n You have chosen a difficulty of " + difficulty);
		
		System.out.println("\n \n To Review:");
		System.out.println("Team name: " + teamName);
		System.out.println("Weeks in season: " + gameWeeks);
		System.out.println("Your Team:");
		for (Champion champ : chosenChampions)
		{
			System.out.println(" -> " + champ.getName());
		}
		System.out.println("Game difficulty: " + difficulty + "\n");
		
		System.out.println("Are you happy with this? [y/n]");
		System.out.println(" - Answering 'n' will restart the setup process");
		
		while(true)
		{
			String in = promptForInput();
			
			if (in.equals("y"))
			{
				// Setup complete, pass on data to gameManager
				gameManager.setupPlayerTeam(teamName, gameWeeks, availChampions, difficulty);
				return;
			}
			if (in.equals("n"))
			{
				// Restart
				displaySetup();
				return;
			}
		}
		
		
	}

	@Override
	public void displayHome() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayTeam() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayStadium() {
		ArrayList<Team> teams = gameManager.getTeams();
		
		ArrayList<String> content = new ArrayList<String>();
		
		for (Team team : teams) {
			content.add(team.getName());
			for (Champion champion : team.getChampions()) {
				content.add(String.format("- %s (%s)", champion.getName(), champion.getWeapon().getName()));
			}
		}
		
		ArrayList<String> options = new ArrayList<String>();
		for (Team team : teams) {
			options.add("FIGHT: " + team.getName());
		}
		
		printView("Matches", content, options);
		System.out.println(promptForInput());
	}

	@Override
	public void displayChampionSetup() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void displayWeaponSetup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayLiveMatch(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayGameResults(GameEnvironment gameEnvironment) {
		// TODO Auto-generated method stub
		
	}
	
	public void playCutscene(Cutscene c)
	{
		while (true)
		{
			// Get the next cutscene slide
			CutsceneSlide slide = c.nextSlide();
			
			// If a null has returned, the cutscene has finished
			if (slide == null)
			{
				break;
			}
			
			// else print the cutscene next and prompt for input before continuing
			System.out.println(slide.getText());
			
			promptForInput();
		}
	}
	
}
