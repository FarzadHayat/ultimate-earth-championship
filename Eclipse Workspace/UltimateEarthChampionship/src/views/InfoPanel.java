package views;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import manager.GameManager;

public class InfoPanel extends JPanel {

	private static final long serialVersionUID = -8835384563469205994L;
	
	private GameManager gameManager = GameManager.getInstance();
	
	/**
	 * Create the panel.
	 */
	public InfoPanel() {
		setLayout(new GridLayout(1, 3, 0, 0));
		
		add(new StatLabel("money.png", String.valueOf(gameManager.getPlayerTeam().getMoney())));
		add(new StatLabel("score.png", String.valueOf(gameManager.getPlayerTeam().getScore())));
		addWeekLabel();
		addDifficultyLabel();
		
	}

	/**
	 * Add a current week out of total weeks label to the info panel.
	 */
	private void addWeekLabel() {
		JPanel weekPanel = new JPanel();
		add(weekPanel);
		weekPanel.setLayout(new GridLayout(1, 2));
		
		JLabel weekLabel = new JLabel("Week: ");
		weekPanel.add(weekLabel);
		
		String weekValueString = String.valueOf(gameManager.getGameEnvironment().getCurrentWeek()) + " out of " +
								String.valueOf(gameManager.getGameEnvironment().getCurrentWeek());
		JLabel weekValue = new JLabel(weekValueString);
		weekPanel.add(weekValue);
	}
	

	/**
	 * Add a difficulty label to the info panel.
	 */
	private void addDifficultyLabel() {
		JPanel difficultyPanel = new JPanel();
		add(difficultyPanel);
		difficultyPanel.setLayout(new GridLayout(1, 2));
		
		JLabel difficultyLabel = new JLabel("Difficulty:");
		difficultyPanel.add(difficultyLabel);
		
		JLabel difficultyValue = new JLabel(String.valueOf(gameManager.getGameEnvironment().getDifficulty()));
		difficultyPanel.add(difficultyValue);
	}

}
