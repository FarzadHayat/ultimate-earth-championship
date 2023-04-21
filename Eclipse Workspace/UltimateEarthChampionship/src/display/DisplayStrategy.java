package display;

import main.GameEnvironment;
import main.Match;

public interface DisplayStrategy {
	public void displayStory(String text);
	public void displaySetup();
	public void displayHome();
	public void displayTeam();
	public void displayShop();
	public void displayMatches();
	public void displayLiveMatch(Match match);
	public void displayMatchResults(Match match);
	public void displayWeekResults(GameEnvironment gameEnvironment);
	public void displayGameResults(GameEnvironment gameEnvironment);
}
