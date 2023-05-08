package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

import model.Champion;

public class ChampionCard extends PurchasableCard {

	private static final long serialVersionUID = -4313085838343164683L;

	Champion champion;
	
	protected ChampionCard() {
		super();
	}
	
	/**
	 * Create the panel.
	 * @param champion the champion to display
	 */
	protected ChampionCard(Champion champion) {
		super(champion);
		this.champion = champion;
	}
	
	/**
	 * Add a panel displaying the champion stats to the center panel.
	 */
	@Override
	public void addStatsPanel() {
		JPanel statsPanel = new JPanel(new GridLayout(0, 1));
		statsPanel.setOpaque(false);
		mainPanel.add(statsPanel, BorderLayout.EAST);
    	statsPanel.add(new StatLabel("stamina", String.valueOf(champion.getStamina())));
    	statsPanel.add(new StatLabel("regen", String.valueOf(champion.getRegen())));
    	statsPanel.add(new StatLabel("offense", String.valueOf(champion.getOffense())));
    	statsPanel.add(new StatLabel("defense", String.valueOf(champion.getDefense())));
	}
	
}
