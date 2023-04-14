package views;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import champion.champions.Confucius;
import main.Purchasable;

public class TeamView extends JPanel {

	private static final long serialVersionUID = -8010724197066539267L;

	/**
	 * Create the panel.
	 */
	public TeamView() {
		setName("Team");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		ArrayList<Purchasable> selectedChampions = new ArrayList<Purchasable>();
		selectedChampions.add(new Confucius());
		selectedChampions.add(new Confucius());
		selectedChampions.add(new Confucius());
		selectedChampions.add(new Confucius());
		selectedChampions.add(new Confucius());
		PurchasableListPanel championListPanel = new PurchasableListPanel(selectedChampions, CardType.CAN_SELL);
		add(championListPanel);
	}

}
