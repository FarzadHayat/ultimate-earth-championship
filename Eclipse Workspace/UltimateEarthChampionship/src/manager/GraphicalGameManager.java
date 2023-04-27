package manager;

import display.DisplayType;
import display.GraphicalDisplay;
import model.Champion;
import model.GameEnvironment;
import model.Weapon;
import match.*;

/**
 * A subclass of GameManager that represents the graphical version of the game.
 */
public class GraphicalGameManager extends GameManager
{
	private GraphicalDisplay graphicalDisplay;

	/**
	 * Starts the game by initializing the GraphicalDisplay.
	 */
	@Override
	public void play()
	{
		graphicalDisplay = new GraphicalDisplay();
		visitShop();
	}
	
	/**
	 * The main method that initializes and starts the game.
	 * @param args the command line arguments (not used)
	 */
	public static void main(String[] args) {
		GameManager.setDisplayType(DisplayType.GUI);
		GameManager.start();
	}

	@Override
	public void visitStory(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSetup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitHome() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitTeam() {
		graphicalDisplay.displayTeam();
	}

	@Override
	public void visitShop() {
		graphicalDisplay.displayShop();
	}

	@Override
	public void visitStadium() {
		graphicalDisplay.displayStadium();
	}

	@Override
	public void visitChampionSetup() {
		graphicalDisplay.displayChampionSetup();
	}
	

	@Override
	public void visitWeaponSetup() {
		graphicalDisplay.displayWeaponSetup();
	}

	@Override
	public void visitLiveMatch(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitGameResults(GameEnvironment gameEnvironment) {
		// TODO Auto-generated method stub
		
	}
	
	public void finishedChampionSetup() {
		visitWeaponSetup();
	}
	
	public void finishedWeaponSetup() {
		for (int i = 0; i < getPlayerTeam().getChosenWeapons().size(); i++) {
			Champion champion = getPlayerTeam().getChosenChampions().get(i); 
			Weapon weapon = getPlayerTeam().getChosenWeapons().get(i);
			champion.setWeapon(weapon);
		}
//		gameManager.visitLiveMatch(new LiveMatch(getPlayerTeam(), getEnemyTeam()));
		// TODO: start live match. waiting for LiveMatch class to be created.
	}

}
