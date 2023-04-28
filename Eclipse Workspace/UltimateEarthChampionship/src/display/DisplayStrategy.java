package display;

import story.Cutscene;

public interface DisplayStrategy {
	public void displayCutscene(Cutscene cutscene);
	public void displaySetup();
	public void displayTeam();
	public void displayShop();
	public void displayStadium();
	public void displayChampionSetup();
	public void displayWeaponSetup();
	public void displayLiveMatch();
	public void displayGameResults();
}
