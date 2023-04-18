package views;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import champion.Champion;

public class ChampionsPanel extends JPanel {

	private static final long serialVersionUID = -943950787490768336L;

	/**
	 * Create the panel.
	 */
	public ChampionsPanel(ArrayList<Champion> champions, CardType type) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		for (Champion champion : champions) {
			JPanel championCard = new ChampionCard(champion, type);
			add(championCard);
		}
	}

}
