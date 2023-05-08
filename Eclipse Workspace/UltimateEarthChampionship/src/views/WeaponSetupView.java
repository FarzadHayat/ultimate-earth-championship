package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import manager.GameManager;
import manager.GraphicalGameManager;
import model.Champion;
import model.Configuration;
import model.Weapon;

public class WeaponSetupView extends JPanel {

	private static final long serialVersionUID = -1352915737619575543L;
	
	private GraphicalGameManager gameManager = (GraphicalGameManager) GameManager.getInstance();
	private Configuration config = Configuration.getInstance();
	
	private JPanel chosenPanel;
	
	/**
	 * Create the panel.
	 */
	public WeaponSetupView() {
		setLayout(new BorderLayout());
		chosenPanel = new JPanel(new GridLayout(0, 2));
		add(chosenPanel, BorderLayout.EAST);
		
		addBackButton();
		addWeaponsPanel();
		addLanesPanel();
		addChosenChampionsPanel();
		addChosenWeaponsPanel();
		addNextButton();
	}

	private void addBackButton() {
		JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton backButton = new JButton("Go back to Champion Setup");
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.backFromWeaponSetup();
			}
		});
		backButtonPanel.add(backButton);
		add(backButtonPanel, BorderLayout.NORTH);
	}

	private void addWeaponsPanel() {
		JPanel weaponsPanel = new JPanel(new GridLayout(3, 3, 20, 20));
		ArrayList<Weapon> weapons = gameManager.getPlayerTeam().getWeapons(); 
		add(weaponsPanel, BorderLayout.WEST);
		for (int i = 0; i < config.NUM_CHAMPIONS; i++) {
			PurchasableCard card;
			if (weapons.size() > i) {
				Weapon weapon = weapons.get(i);
				card = new PurchasableCard(weapon);
				card.addStatsPanel();
				card.addRosterToggle();
				if (gameManager.getPlayerTeam().getChosenChampions().contains(weapon)) {
					card.selected();
				}
			} else {
				card = new PurchasableCard();
			}
			weaponsPanel.add(card);
		}
	}
	
	private void addLanesPanel() {
		JPanel lanesPanel = new JPanel(new GridLayout(0, 1, 20, 20));
		add(lanesPanel, BorderLayout.CENTER);
		for (int i = 0; i < config.NUM_CHOSEN_CHAMPIONS; i++) {
			JLabel label = new JLabel(String.valueOf(i+1), JLabel.RIGHT);
			label.setFont(Configuration.HEADER_FONT);
			lanesPanel.add(label);
		}
	}
	
	private void addChosenChampionsPanel() {
		JPanel chosenChampionsPanel = new JPanel(new GridLayout(0, 1, 20, 20));
		ArrayList<Champion> chosenChampions = gameManager.getPlayerTeam().getChosenChampions(); 
		chosenPanel.add(chosenChampionsPanel);
		for (int i = 0; i < config.NUM_CHOSEN_CHAMPIONS; i++) {
			PurchasableCard card;
			if (chosenChampions.size() > i) {
				Champion champion = chosenChampions.get(i);
				card = new PurchasableCard(champion);
				card.addStatsPanel();
				card.selected();
			} else {
				card = new PurchasableCard();
			}
			chosenChampionsPanel.add(card);
		}
	}
	
	private void addChosenWeaponsPanel() {
		JPanel chosenWeaponsPanel = new JPanel(new GridLayout(0, 1, 20, 20));
		ArrayList<Weapon> chosenWeapons = gameManager.getPlayerTeam().getChosenWeapons(); 
		chosenPanel.add(chosenWeaponsPanel);
		for (int i = 0; i < config.NUM_CHOSEN_CHAMPIONS; i++) {
			PurchasableCard card;
			if (chosenWeapons.size() > i) {
				Weapon weapon = chosenWeapons.get(i);
				card = new PurchasableCard(weapon);
				card.addStatsPanel();
				card.selected();
			} else {
				card = new PurchasableCard();
			}
			chosenWeaponsPanel.add(card);
		}
	}
		
	private void addNextButton() {
		JPanel nextButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton startButton = new JButton("Start match!");
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.finishedWeaponSetup();
			}
		});
		nextButtonPanel.add(startButton);
		add(nextButtonPanel, BorderLayout.SOUTH);
	}

}
