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
	public void displayCutscene(Cutscene cutscene);

	public void displaySetup();

	public void displayTeam();

	public void displayShop();

	public void displayStadium();

	public void displayChampionSetup();

	public void displayWeaponSetup();

	public void displayMatch(Match match);

	public void displayMatchResults(MatchResult matchResult);

	public void displayWeekResults(ArrayList<RandomEventInfo> randomEvents);

	public void displayGameResults(ArrayList<Team> teams);

	public void quit();
}
