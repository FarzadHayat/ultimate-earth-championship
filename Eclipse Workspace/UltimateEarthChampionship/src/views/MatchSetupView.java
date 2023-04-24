package views;

import java.awt.FlowLayout;

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
		setLayout(new FlowLayout());
		
		for (Champion champion : gameManager.getPlayerTeam().getChosenChampions()) {
			DraggablePurchasableCard card = new DraggablePurchasableCard((Purchasable) champion, CardType.MINIMAL, enemyTeam);
			add(card);
		}
	}

}
