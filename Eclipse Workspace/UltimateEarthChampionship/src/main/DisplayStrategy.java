package main;

import java.util.ArrayList;

public interface DisplayStrategy {
	public void displayStory(String text);
	public void displaySetup();
	public void displayHome(GameEnvironment gameEnvironment);
	public void displayTeam(Team team);
	public void displayShop(Shop shop);
	public void displayMatches(ArrayList<Match> matches);
	public void displayLiveMatch(Match match);
	public void displayMatchResults(Match match);
	public void displayWeekResults(GameEnvironment gameEnvironment);
	public void displayGameResults(GameEnvironment gameEnvironment);
}
