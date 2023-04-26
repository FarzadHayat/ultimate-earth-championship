package display;

import model.GameEnvironment;
import model.Match;
import model.Team;

public interface DisplayStrategy {
	public void displayStory(String text);
	public void displaySetup();
	public void displayHome();
	public void displayTeam();
	public void displayShop();
	public void displayStadium();
	public void displayChampionSetup(Team team);
	public void displayLiveMatch(Match match);
	public void displayGameResults(GameEnvironment gameEnvironment);
}
