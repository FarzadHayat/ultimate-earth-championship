package manager;

import display.DisplayType;
import display.GraphicalDisplay;
import story.OpeningCutscene;

/**
 * A subclass of GameManager that represents the graphical version of the game.
 */
public class GraphicalGameManager extends GameManager
{
	/**
	 * Starts the game by initializing the GraphicalDisplay.
	 */
	@Override
	public void play()
	{
		displayStrategy = new GraphicalDisplay();
		setCutscene(new OpeningCutscene());
		displayStrategy.displayCutscene(getCutscene());
	}
	
	public void backFromChampionSetup() {
		setEnemyTeam(null);
		playerTeam.unselectChampions();
		displayStrategy.displayStadium();
	}
	
	public void backFromWeaponSetup() {
		playerTeam.unselectWeapons();
		displayStrategy.displayChampionSetup();
	}
	
	//TODO: Fix this mess
	public void repaintTeam() {
		displayStrategy.displayTeam();
	}

	public void repaintShop() {
		displayStrategy.displayShop();
	}
	
	public void repaintChampionSetup() {
		displayStrategy.displayChampionSetup();
	}
	
	public void repaintWeaponSetup() {
		displayStrategy.displayWeaponSetup();
	}
	
	public void repaintCutscene() {
		displayStrategy.displayCutscene(getCutscene());
	}
	
	/**
	 * The main method that initializes and starts the game.
	 * @param args the command line arguments (not used)
	 */
	public static void main(String[] args) {
		GameManager.setDisplayType(DisplayType.GUI);
		GameManager.start();
	}
	

}
