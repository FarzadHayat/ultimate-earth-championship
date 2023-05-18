package manager;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import display.DisplayType;
import display.GraphicalDisplay;
import model.Champion;
import story.OpeningCutscene;

/**
 * A subclass of GameManager that represents the GUI version of the game.
 */
public class GraphicalGameManager extends GameManager {

	/**
	 * Start the game by creating a new GraphicalDisplay and displaying the opening
	 * cutscene.
	 */
	@Override
	public void play() {
		displayStrategy = new GraphicalDisplay();
		
		// Play the starting cutscene
		setCutscene(new OpeningCutscene());
		displayStrategy.displayCutscene(getCutscene());
	}

	/**
	 * Returns to the stadium from champion setup.
	 */
	public void backFromChampionSetup() {
		setEnemyTeam(null);
		playerTeam.unselectChampions();
		displayStrategy.displayStadium();
	}

	/**
	 * Returns to champion setup from weapon setup.
	 */
	public void backFromWeaponSetup() {
		playerTeam.unselectWeapons();
		playerTeam.unassignChosenWeapons();
		displayStrategy.displayChampionSetup();
	}

	/**
	 * Repaints the team view to update its components.
	 */
	public void repaintTeam() {
		displayStrategy.displayTeam();
	}

	/**
	 * Repaints the shop view to update its components.
	 */
	public void repaintShop() {
		displayStrategy.displayShop();
	}

	/**
	 * Repaints the champion setup view to update its components.
	 */
	public void repaintChampionSetup() {
		displayStrategy.displayChampionSetup();
	}

	/**
	 * Repaints the weapon setup view to update its components.
	 */
	public void repaintWeaponSetup() {
		displayStrategy.displayWeaponSetup();
	}

	/**
	 * Repaints the cutscene view to update its components.
	 */
	public void repaintCutscene() {
		displayStrategy.displayCutscene(getCutscene());
	}

	/**
	 * Takes a bye and then calls finishedWeek()
	 */
	@Override
	public void takeBye()
	{
		// Create dialogue box
		ArrayList<Champion> playerChampions = super.getPlayerTeam().getChampions();
		
		ArrayList<Object> tempList = new ArrayList<Object>();
		
		for (Champion champ : playerChampions)
		{
			tempList.add(champ.getName());
		}
		
		Object[] options = tempList.toArray();
		
		int result = JOptionPane.showOptionDialog(null, "Choose a champion to train:", "Weekly training", JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		
		Champion champToTrain = playerChampions.get(result);
				
		super.trainChampion(champToTrain);
		
		super.takeBye();
	}
	
	/**
	 * The main method that starts the game. Set the display type to GUI and calls
	 * the start method.
	 *
	 * @param args the command line arguments (not used)
	 */
	public static void main(String[] args) {
		GameManager.setDisplayType(DisplayType.GUI);
		GameManager.start();
	}

}
