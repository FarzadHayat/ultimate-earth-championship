package display;

import java.util.ArrayList;

import events.RandomEventInfo;
import match.Match;
import match.MatchResult;
import model.Team;
import story.Cutscene;

/**
 * The strategy design pattern for creating a contract between the GUI and CLI
 * display to display the same views for the game.
 */
public interface DisplayStrategy {
	/**
	 * Displays the cutscene
	 * @param cutscene The cutscene to be shown
	 */
	public void displayCutscene(Cutscene cutscene);

	/**
	 * Displays the setup view
	 */
	public void displaySetup();

	/**
	 * Displays the team view
	 */
	public void displayTeam();

	/**
	 * Displays the shop view
	 */
	public void displayShop();

	/**
	 * Displays the stadium view
	 */
	public void displayStadium();

	/**
	 * displays the champion setup view
	 */
	public void displayChampionSetup();

	/**
	 * Displays the weapon setup view
	 */
	public void displayWeaponSetup();

	/**
	 * Displays the match view
	 * @param match The match to be shown
	 */
	public void displayMatch(Match match);

	/**
	 * Displays the match result view
	 * @param matchResult The match result to be shown
	 */
	public void displayMatchResults(MatchResult matchResult);

	/**
	 * Displays the week results
	 * @param randomEvents The random events to be shown
	 */
	public void displayWeekResults(ArrayList<RandomEventInfo> randomEvents);

	/**
	 * Displays the game results view
	 * @param teams The teams to be shown
	 */
	public void displayGameResults(ArrayList<Team> teams);

	/**
	 * Quits the game
	 */
	public void quit();
}
