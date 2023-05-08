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
	
	/**
	 * Add a buy button to the south of the card.
	 */
	@Override
	public void addBuyButton() {
	    JButton buyButton = new JButton("Buy for $" + champion.getPrice());
	    buyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message = String.format("Are you sure you want to buy %s for $%s?",
							champion.getName(), champion.getPrice());
					int answer = JOptionPane.showConfirmDialog(getParent(), message,
							"Buy: " + champion.getName(), JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						gameManager.getPlayerTeam().buy(champion);
						gameManager.repaintShop();
					}
				} catch (InsufficientFundsException | FullTeamException | IllegalPurchaseException e) {
					JOptionPane.showMessageDialog(getParent(), e.getMessage());
				}
			}
		});
	    mainPanel.add(buyButton, BorderLayout.SOUTH);
	}

	/**
	 * Add a sell button the champion to the south of the card.
	 */
	@Override
	public void addSellButton() {
	    JButton sellButton = new JButton("Sell for $" + champion.getPrice());
	    sellButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message = String.format("Are you sure you want to sell %s for $%s?",
							champion.getName(), champion.getPrice());
					int answer = JOptionPane.showConfirmDialog(getParent(), message,
							"Sell: " + champion.getName(), JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						gameManager.getPlayerTeam().sell(champion);
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
