package display;

import java.util.ArrayList;

import events.RandomEventInfo;
import match.LiveMatch;
import match.MatchResult;
import model.Team;
import story.Cutscene;

public interface DisplayStrategy {
	public void displayCutscene(Cutscene cutscene);
	public void displaySetup();
	public void displayTeam();
	public void displayShop();
	public void displayStadium();
	public void displayChampionSetup();
	public void displayWeaponSetup();
	public void displayLiveMatch(LiveMatch match);
	public void displayMatchResults(MatchResult matchResult);
	public void displayWeekResults(ArrayList<RandomEventInfo> randomEvents);
	public void displayGameResults(ArrayList<Team> teams);
	public void quit();
}
