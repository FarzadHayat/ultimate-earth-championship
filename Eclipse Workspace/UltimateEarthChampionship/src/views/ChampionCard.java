package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Champion;
import model.Configuration;
import model.Weapon;

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
		
    	String staminaString = String.valueOf(champion.getStamina());
    	String regenString = String.valueOf(champion.getRegen());
    	String offenseString = String.valueOf(champion.getOffense());
    	String defenseString = String.valueOf(champion.getDefense());
    	
		StatLabel staminaLabel = new StatLabel("stamina", staminaString);
		statsPanel.add(staminaLabel);
		StatLabel regenLabel = new StatLabel("regen", regenString);
		statsPanel.add(regenLabel);
		StatLabel offenseLabel = new StatLabel("offense", offenseString);
		statsPanel.add(offenseLabel);
		StatLabel defenseLabel = new StatLabel("defense", defenseString);
		statsPanel.add(defenseLabel);
		
		int offenseBoost = champion.getWeapon().getOffenseBoost();
		if (offenseBoost > 0) {
			offenseLabel.getValueLabel().setForeground(Configuration.GREEN);
		}
		if (offenseBoost < 0) {
			offenseLabel.getValueLabel().setForeground(Configuration.RED);
		}
		
		int defenseBoost = champion.getWeapon().getDefenseBoost();
		if (defenseBoost > 0) {
			defenseLabel.getValueLabel().setForeground(Configuration.GREEN);
		}
		if (defenseBoost < 0) {
			defenseLabel.getValueLabel().setForeground(Configuration.RED);
		}
	}
	
}
