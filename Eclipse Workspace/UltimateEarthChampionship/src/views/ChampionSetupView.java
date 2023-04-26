package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import exception.FullTeamException;
import manager.GameManager;
import model.Champion;
import model.Purchasable;
import model.Team;
import model.Configuration;
import views.PurchasableCard.CardType;

public class ChampionSetupView extends JPanel {

	private static final long serialVersionUID = -1352915737619575543L;
	
	private GameManager gameManager = GameManager.getInstance();
	private Configuration config = Configuration.getInstance();
	
	/**
	 * Create the panel.
	 */
	public ChampionSetupView(Team enemyTeam) {
		setLayout(new BorderLayout());
		
		addBackButton();
		addChampionsPanel();
		addLanesPanel();
		addChosenChampionsPanel();
		addNextButton();
	}

	private void addBackButton() {
		JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton backButton = new JButton("Go back");
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.setEnemyTeam(null);
				gameManager.visitStadium();
			}
		});
		backButtonPanel.add(backButton);
		add(backButtonPanel, BorderLayout.NORTH);
	}

	private void addChampionsPanel() {
		JPanel championsPanel = new JPanel(new GridLayout(3, 3, 20, 20));
		ArrayList<Champion> champions = gameManager.getPlayerTeam().getChampions(); 
		add(championsPanel, BorderLayout.WEST);
		for (int i = 0; i < config.NUM_CHAMPIONS; i++) {
			Champion champion;
			if (champions.size() > i) {
				champion = champions.get(i);
			} else {
				champion = null;
			}
			PurchasableCard card = new PurchasableCard((Purchasable) champion, CardType.SELECTABLE);
			championsPanel.add(card);
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
		add(chosenChampionsPanel, BorderLayout.EAST);
		for (int i = 0; i < config.NUM_CHOSEN_CHAMPIONS; i++) {
			Champion champion;
			if (chosenChampions.size() > i) {
				champion = chosenChampions.get(i);
			} else {
				champion = null;
			}
			PurchasableCard card = new PurchasableCard((Purchasable) champion, CardType.SELECTABLE);
			chosenChampionsPanel.add(card);
		}
	}
		
	private void addNextButton() {
		JPanel nextButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.closeMatchSetup();
			}
		});
		nextButtonPanel.add(startButton);
		add(nextButtonPanel, BorderLayout.SOUTH);
	}

}
