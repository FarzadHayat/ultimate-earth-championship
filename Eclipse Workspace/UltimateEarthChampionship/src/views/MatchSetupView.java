package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import champions.AdamSmith;
import manager.GameManager;
import model.Champion;
import model.Purchasable;
import model.Team;
import match.DumbMatch;

public class MatchSetupView extends JPanel {

	private static final long serialVersionUID = -1352915737619575543L;
	
	private GameManager gameManager = GameManager.getInstance();
	
	/**
	 * Create the panel.
	 */
	public MatchSetupView(Team enemyTeam) {
		setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		JButton backButton = new JButton("Go back");
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.visitMatchSelection();
			}
		});
		northPanel.add(backButton);
		add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		for (Champion champion : gameManager.getPlayerTeam().getChosenChampions()) {
			DraggablePurchasableCard card = new DraggablePurchasableCard((Purchasable) champion, CardType.MINIMAL, enemyTeam);
			centerPanel.add(card);
		}
		
		JPanel southPanel = new JPanel();
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.visitLiveMatch(null); //TODO: create new LiveMatch and begin fight.
			}
		});
		southPanel.add(startButton);
		add(southPanel, BorderLayout.SOUTH);
	}

}
