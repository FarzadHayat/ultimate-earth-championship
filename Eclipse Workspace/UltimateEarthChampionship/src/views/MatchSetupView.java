package views;

import java.util.ArrayList;

import javax.swing.JPanel;

import manager.GameManager;
import model.Champion;
import model.Purchasable;
import model.Team;

public class MatchSetupView extends JPanel {

	private static final long serialVersionUID = -1352915737619575543L;
	
	private GameManager gameManager = GameManager.getInstance();
	
	/**
	 * Create the panel.
	 */
	public MatchSetupView(Team enemyTeam) {		
		ArrayList<Purchasable> playerChampions = new ArrayList<Purchasable>();
		for (Champion champion : gameManager.getPlayerTeam().getChosenChampions()) {
			playerChampions.add(champion);
		}
		PurchasablesPanel chosenChampionsPanel = new PurchasablesPanel(playerChampions, CardType.MINIMAL);
		add(chosenChampionsPanel);
	}
	

}
