package views;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import main.GameManager;

public class TeamView extends JPanel {

	private static final long serialVersionUID = -8010724197066539267L;
	
	private GameManager gameManager = GameManager.getInstance();

	/**
	 * Create the panel.
	 */
	public TeamView() {
		setName("Team");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		ChampionsPanel chosenChampionsPanel = new ChampionsPanel(gameManager.getPlayerTeam().getChosenChampions(), CardType.CAN_SELL);
		add(chosenChampionsPanel);
		
		ChampionsPanel reserveChampionsPanel = new ChampionsPanel(gameManager.getPlayerTeam().getReserveChampions(), CardType.CAN_SELL);
		add(reserveChampionsPanel);
	}

}
