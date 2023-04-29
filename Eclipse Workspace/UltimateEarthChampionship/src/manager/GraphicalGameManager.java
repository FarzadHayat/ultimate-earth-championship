package manager;

import display.DisplayType;
import display.GraphicalDisplay;
import story.OpeningCutscene;
import views.CutsceneView;
import views.SetupView;
import views.TabbedView;
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

	private SetupView setupView;
	
	private CutsceneView openingCutsceneView;
	
	/**
	 * Starts the game by initializing the GraphicalDisplay.
	 */
	@Override
	public void play()
	{
		graphicalDisplay = new GraphicalDisplay();
		openingCutsceneView = new CutsceneView(new OpeningCutscene(), this);
		graphicalDisplay.displayView(openingCutsceneView);
	}
	
	private void setup()
	{
		setupView = new SetupView(this);
		
		graphicalDisplay.displayView(setupView);
	}
	
	/**
	 * Called by the setupView to confirm that the playerTeam has been setup
	 */
	public void finishedSetup()
	{
		tabbedView();
	}
	
	public void drawCutscene()
	{
		openingCutsceneView.reDrawPanel();
		graphicalDisplay.displayView(openingCutsceneView);
	}
	
	public void cutsceneFinished()
	{
		setup();
	}
	
	private void tabbedView()
	{
		TabbedView tabbedView = new TabbedView();
		graphicalDisplay.displayView(tabbedView);
	}
	
	public GraphicalDisplay getGraphicalDisplay() {
		return graphicalDisplay;
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
		// gameManager.visitLiveMatch(new LiveMatch(getPlayerTeam(), getEnemyTeam()));
		
		LiveMatch match = new LiveMatch(getPlayerTeam(), getEnemyTeam());
		
		visitLiveMatch(match);
	}
	
	@Override
	public void visitLiveMatch(LiveMatch match) {
		graphicalDisplay.displayLiveMatch(match);
		
	}

	
}
