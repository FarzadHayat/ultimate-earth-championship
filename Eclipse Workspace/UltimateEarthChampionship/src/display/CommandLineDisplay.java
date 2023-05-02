package display;

import java.util.ArrayList;
import java.util.Scanner;

import events.RandomEventInfo;
import exception.InputException;
import manager.GameManager;
import match.LiveMatch;
import model.Champion;
import model.Configuration;
import model.SetupManager;
import model.Shop;
import model.Team;
import model.Weapon;
import story.Cutscene;
import story.CutsceneSlide;

public class CommandLineDisplay implements DisplayStrategy {
	
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
	
	@Override
	public void displayCutscene(Cutscene cutscene) {
		while (true)
		{
			// Get the next cutscene slide
			CutsceneSlide slide = cutscene.nextSlide();
			
			// If a null has returned, the cutscene has finished
			if (slide == null)
			{
				break;
			}
			
			// else print the cutscene next and prompt for input before continuing
			System.out.println(slide.getText());
			
			promptForInput();
		}

		gameManager.finishedCutscene();
	}

	/**
	 * Displays all the text related to setting up the game,
	 )* Requires the player to input a team name,
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
				chosenChampions.add(chosenChamp);
			} 
			catch (InputException e) 
			{
				System.out.println(e.getMessage() + " \n");
			}
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
				break;
			}
			if (in.equals("n"))
			{
				// Restart
				displaySetup();
				return;
			}
		}
		gameManager.finishedSetup();
	}

	@Override
	public void displayTeam() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Displays the shop to the console.
	 * @param shop The shop to display.
	 */
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

	@Override
	public void displayStadium() {
		ArrayList<Team> teams = gameManager.getAITeams();
		
		CommandLineUtilities.printHeader("Stadium");
		
		CommandLineUtilities.printTeams(teams);
		CommandLineUtilities.printLine();
		
		CommandLineUtilities.printTeamOptions(teams);
		
		System.out.println(promptForInput());
	}

	@Override
	public void displayChampionSetup() {
		ArrayList<Champion> chosenChampions = new ArrayList<Champion>();  
		ArrayList<Champion> championsLeft = new ArrayList<Champion>();
		championsLeft.addAll(gameManager.getPlayerTeam().getChampions());
		while (chosenChampions.size() < Configuration.getInstance().NUM_CHOSEN_CHAMPIONS) {
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
		
		while(true)
		{
			System.out.println("Are you happy with this? [y/n]");
			System.out.println(" - Answering 'n' will restart the setup process");
			
			String in = promptForInput();
			
			if (in.equals("y"))
			{
				// Weapon setup complete, assign chosen champions to the player team
				gameManager.getPlayerTeam().setChosenChampions(chosenChampions);
				break;
			}
			if (in.equals("n"))
			{
				// Restart
				displayChampionSetup();
				return;
			}
		}
		gameManager.finishedChampionSetup();
	}
	

	@Override
	public void displayWeaponSetup() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void displayWeekResults(ArrayList<RandomEventInfo> randomEvents) {
		for (RandomEventInfo randomEvent : randomEvents) {
			System.out.println(String.format("%s: %s (%s)", randomEvent.name, randomEvent.description, randomEvent.effectString));
			promptForInput();
		}
		GameManager.getInstance().finishedWeek();
	}

	@Override
	public void displayGameResults() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayLiveMatch(LiveMatch match) {
		System.out.println("TODO: Livematch in command line interface");
		
	}
	
}
