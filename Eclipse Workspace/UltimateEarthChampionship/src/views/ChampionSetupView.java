package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import champions.AdamSmith;
import manager.GameManager;
import model.Champion;
import model.Purchasable;
import model.Team;
import model.Configuration;
import views.PurchasableCard.CardType;

public class ChampionSetupView extends JPanel {

	private static final long serialVersionUID = -1352915737619575543L;
	
	private GameManager gameManager = GameManager.getInstance();
	
	/**
	 * Create the panel.
	 */
	public ChampionSetupView(Team enemyTeam) {
		setLayout(new BorderLayout());
		
		addBackButton();
		addChampionsPanel();
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
		JPanel championsPanel = new JPanel(new FlowLayout());
		add(championsPanel, BorderLayout.WEST);
		for (Champion champion : gameManager.getPlayerTeam().getChampions()) {
			DraggablePurchasableCard card = new DraggablePurchasableCard((Purchasable) champion, CardType.MINIMAL);
			championsPanel.add(card);
		}
	}
	
	private void addChosenChampionsPanel() {
		JPanel chosenChampionsPanel = new JPanel(new GridLayout(Configuration.getInstance().NUM_CHOSEN_CHAMPIONS, 1));
		add(chosenChampionsPanel, BorderLayout.EAST);
		for (Champion champion : gameManager.getPlayerTeam().getChosenChampions()) {
			DraggablePurchasableCard card = new DraggablePurchasableCard((Purchasable) champion, CardType.MINIMAL);
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
