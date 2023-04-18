package views;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import champion.Champion;
import champion.champions.Confucius;

public class TeamView extends JPanel {

	private static final long serialVersionUID = -8010724197066539267L;

	/**
	 * Create the panel.
	 */
	public TeamView() {
		setName("Team");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		ArrayList<Champion> selectedChampions = new ArrayList<Champion>();
		selectedChampions.add(new Confucius());
		selectedChampions.add(new Confucius());
		selectedChampions.add(new Confucius());
		selectedChampions.add(new Confucius());
		selectedChampions.add(new Confucius());
		ChampionsPanel championsPanel = new ChampionsPanel(selectedChampions, CardType.CAN_SELL);
		add(championsPanel);
	}

}
