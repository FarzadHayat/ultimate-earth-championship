package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import exception.FullTeamException;
import exception.IllegalPurchaseException;
import exception.IncompleteTeamException;
import exception.InsufficientFundsException;
import model.Champion;
import model.Weapon;

public class WeaponCard extends PurchasableCard {

	private static final long serialVersionUID = -4313085838343164683L;

	Weapon weapon;
	
	protected WeaponCard() {
		super();
	}
	
	/**
	 * Create the panel.
	 * @param weapon the weapon to display
	 */
	protected WeaponCard(Weapon weapon) {
		super(weapon);
		this.weapon = weapon;
	}
	
	/**
	 * Add a panel displaying the weapon stats to the center panel.
	 */
	@Override
	public void addStatsPanel() {
		JPanel statsPanel = new JPanel(new GridLayout(0, 1));
		statsPanel.setOpaque(false);
		mainPanel.add(statsPanel, BorderLayout.EAST);
    	statsPanel.add(new StatLabel("offense", String.valueOf(weapon.getOffenseBoost())));
    	statsPanel.add(new StatLabel("damage", String.valueOf(weapon.getDamageMultiplier())));
    	statsPanel.add(new StatLabel("defense", String.valueOf(weapon.getDefenseBoost())));
	}
}
