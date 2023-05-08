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
	
	/**
	 * Add a buy button to the south of the card.
	 */
	@Override
	public void addBuyButton() {
	    JButton buyButton = new JButton("Buy for $" + weapon.getPrice());
	    buyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message = String.format("Are you sure you want to buy %s for $%s?",
							weapon.getName(), weapon.getPrice());
					int answer = JOptionPane.showConfirmDialog(getParent(), message,
							"Buy: " + weapon.getName(), JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						gameManager.getPlayerTeam().buy(weapon);
						gameManager.repaintShop();
					}
				} catch (InsufficientFundsException | FullTeamException e) {
					JOptionPane.showMessageDialog(getParent(), e.getMessage());
				}
			}
		});
	    mainPanel.add(buyButton, BorderLayout.SOUTH);
	}

	/**
	 * Add a sell button the weapon to the south of the card.
	 */
	@Override
	public void addSellButton() {
	    JButton sellButton = new JButton("Sell for $" + weapon.getPrice());
	    sellButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message = String.format("Are you sure you want to sell %s for $%s?",
							weapon.getName(), weapon.getPrice());
					int answer = JOptionPane.showConfirmDialog(getParent(), message,
							"Sell: " + weapon.getName(), JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						gameManager.getPlayerTeam().sell(weapon);
						gameManager.repaintTeam();
					}
				} catch (IncompleteTeamException e) {
					JOptionPane.showMessageDialog(getParent(), e.getMessage());
				}
			}
		});
	    mainPanel.add(sellButton, BorderLayout.SOUTH);
	}
}
