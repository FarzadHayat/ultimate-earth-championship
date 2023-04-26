package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import manager.GameManager;
import model.Champion;
import model.Purchasable;
import model.Team;
import views.PurchasableCard.CardType;

public class ChampionSetupView extends JPanel {

	private static final long serialVersionUID = -1352915737619575543L;
	
	private GameManager gameManager = GameManager.getInstance();
	
	/**
	 * Create the panel.
	 */
	public ChampionSetupView(Team enemyTeam) {
		setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton backButton = new JButton("Go back");
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.setEnemyTeam(null);
				gameManager.visitStadium();
			}
		});
		northPanel.add(backButton);
		add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		for (Champion champion : gameManager.getPlayerTeam().getChampions()) {
			DraggablePurchasableCard card = new DraggablePurchasableCard((Purchasable) champion, CardType.MINIMAL);
			centerPanel.add(card);
		}
		
		JPanel southPanel = new JPanel();
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.closeMatchSetup();
			}
		});
		southPanel.add(startButton);
		add(southPanel, BorderLayout.SOUTH);
	}

}
