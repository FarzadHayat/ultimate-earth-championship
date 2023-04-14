package views;

import javax.swing.JPanel;

import main.GameManager;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class InfoPanel extends JPanel {

	private static final long serialVersionUID = -8835384563469205994L;
	
	private GameManager gameManager = GameManager.getInstance();
	
	/**
	 * Create the panel.
	 */
	public InfoPanel() {
		setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel moneyPanel = new JPanel();
		add(moneyPanel);
		moneyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel moneyLabel = new JLabel("Money: ");
		moneyPanel.add(moneyLabel);
		
		JLabel moneyValue = new JLabel(); //TODO: add player team's money here
		moneyPanel.add(moneyValue);
		
		JPanel scorePanel = new JPanel();
		add(scorePanel);
		scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel scoreLabel = new JLabel("Score: ");
		scorePanel.add(scoreLabel);
		
		JLabel scoreValue = new JLabel(); //TODO: add player team's score here
		scorePanel.add(scoreValue);
		
		JPanel weekPanel = new JPanel();
		add(weekPanel);
		weekPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel weekLabel = new JLabel("Week: ");
		weekPanel.add(weekLabel);
		
		String weekValueString = String.valueOf(gameManager.getGameEnvironment().getCurrentWeek()) + " out of " +
								String.valueOf(gameManager.getGameEnvironment().getCurrentWeek());
		JLabel weekValue = new JLabel(weekValueString);
		weekPanel.add(weekValue);
		
		JPanel difficultyPanel = new JPanel();
		add(difficultyPanel);
		difficultyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel difficultyLabel = new JLabel("Difficulty:");
		difficultyPanel.add(difficultyLabel);
		
		JLabel difficultyValue = new JLabel(String.valueOf(gameManager.getGameEnvironment().getDifficulty()));
		difficultyPanel.add(difficultyValue);
		
	}

}
